<template>
  <div class="login-form">
    <!--element版-->
    <el-form ref="form" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="username" placeholder="请输入用户名" type="text"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phonenumber">
        <div class="form-group">
          <div class="input-container">
            <el-input
              id="phonenumber"
              v-model="phonenumber"
              placeholder="请输入手机号"
              type="text"
            ></el-input>
            <el-button
              :disabled="isButtonDisabled"
              class="button1"
              type="primary"
              @click="sendVerificationCode"
            >
              {{ isButtonDisabled ? `${countdown}秒后重发` : '发送验证码' }}
            </el-button>
          </div>
        </div>
      </el-form-item>
      <el-form-item label="验证码" prop="verificationCode">
        <el-input v-model="verificationCode" placeholder="请输入验证码" type="text"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="button2" type="primary" @click="next">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import router from '@/router'
import instance from '@/utils/axios'
import { ElMessage,ElFormItem,ElButton } from 'element-plus'
// 定义响应式数据
const username = ref('')
const phonenumber = ref('')
const verificationCode = ref('')
const isButtonDisabled = ref(false)
const countdown = ref(60)
let timer: ReturnType<typeof setInterval> | undefined

//发送验证码事件
const sendVerificationCode = async () => {
  if (isButtonDisabled.value) return
  const phoneNumberPattern = /^1[3-9]\d{9}$/
  if (!phoneNumberPattern.test(phonenumber.value)) {
    ElMessage('请输入正确的手机号')
    return
  }

  const response = await instance.post(
    'user/auth/messageSend',
    {
      phoneNumber: phonenumber.value
    },
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
  if (response.data.code == 0) ElMessage.success(`${response.data.message}`)
  else {
    ElMessage.error(`${response.data.message}`)
    return
  }
  // 禁用按钮并开始倒计时
  isButtonDisabled.value = true
  countdown.value = 30
  timer = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--
    } else {
      clearInterval(timer)
      isButtonDisabled.value = false
    }
  }, 1000)
}

//验证完手机号的下一步触发事件
const next = async () => {
  if (username.value == '') {
    ElMessage.error('请输入用户名')
    return
  }
  if (username.value.length < 2) {
    ElMessage.error('用户名不得小于2位！')
    return
  }
  if (verificationCode.value == '') {
    ElMessage('请输入验证码')
    return
  }
  try {
    //检测用户名和手机号是否已存在
    const response = await instance.get('/user/auth/find', {
      params: {
        userName: username.value,
        phoneNumber: phonenumber.value
      },
      headers: {
        'Content-Type': 'application/json'
      }
    })
    if (response.data.code != 0) {
      ElMessage('用户名或手机号已存在')
      username.value = ''
      phonenumber.value = ''
      verificationCode.value = ''
      return
    }
  } catch (error) {
    ElMessage('网络错误')
    username.value = ''
    phonenumber.value = ''
    verificationCode.value = ''
    return
  }
  try {
    //验证验证码
    const response = await instance.post(
      '/user/auth/message',
      {
        userName: username.value,
        phoneNumber: phonenumber.value,
        messageCode: verificationCode.value
      },
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    const code = response.data.code
    if (code != 0) {
      ElMessage('验证码或手机号错误')
      verificationCode.value = ''
      return
    }
    localStorage.setItem('phoneNumber', phonenumber.value)
    localStorage.setItem('userName', username.value)
    localStorage.setItem('messageCode', verificationCode.value)
    router.push('/setPassword')
  } catch (error) {
    ElMessage('网络错误')
    verificationCode.value = ''
  }
}
</script>

<style scoped>
.login-form {
  margin: 150px auto;
  padding: 60px;
  width: 400px;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

.input-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.input-container input {
  flex: 10;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333333;
}

input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #cccccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
}

input:focus {
  border-color: #007bff;
  background-color: #ffffff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
  outline: none;
}

.button1 {
  width: 40%;
  padding: 10px 10px;
  padding-left: 20px;
  font-size: 16px;
  background-color: #007bff;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
}

.button1:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.button2 {
  margin-top: 10px;
  padding: 12px;
  font-size: 16px;
  background-color: #007bff;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
}

.button2:hover {
  background-color: #0056b3;
}

.button2:active {
  background-color: #004085;
  box-shadow: 0 2px 4px rgba(0, 123, 255, 0.3);
}

button {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  background-color: #007bff;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
}

button:hover {
  background-color: #0056b3;
}

button:active {
  background-color: #004085;
  box-shadow: 0 2px 4px rgba(0, 123, 255, 0.3);
}
</style>
