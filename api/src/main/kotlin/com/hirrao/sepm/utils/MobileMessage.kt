package com.hirrao.sepm.utils/*

import com.aliyun.dysmsapi20170525.Client
import com.aliyun.dysmsapi20170525.models.SendSmsRequest
import com.aliyun.teaopenapi.models.Config
import com.aliyun.teautil.models.RuntimeOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MobileMessage {
    private val logger: Logger = LoggerFactory.getLogger(MobileMessage::class.java)

    @Throws(Exception::class)
    fun createClient(): Client {
        val config = Config().setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID")).setAccessKeySecret(
            System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET")
        )
        config.endpoint = "dysmsapi.aliyuncs.com"
        return Client(config)
    }

    fun sendMessage(phoneNumber: String?, code: String?, time: String?): Int {
        try {
            val client = createClient()
            val sendSmsRequest =
                SendSmsRequest().setSignName(System.getenv("SIGN_NAME")).setTemplateCode(System.getenv("TEMPLATE_CODE"))
                    .setPhoneNumbers(phoneNumber).setTemplateParam(
                        String.format("{\"code\":\"%s\",\"time\":\"%s\"}", code, time)
                    )
            val runtime = RuntimeOptions()
            val response = client.sendSmsWithOptions(sendSmsRequest, runtime)
            when (response.getBody().getCode()) {
                "MOBILE_NOT_ON_SERVICE", "isv.MOBILE_NUMBER_ILLEGAL" -> {
                    logger.error("{}", response.body.message)
                    return -2
                }

                "OK" -> logger.info("{}", response.body.message)
                else -> {
                    logger.error("{}", response.body.message)
                    return -1
                }
            }
        } catch (error: Exception) {
            logger.error("{}", error.message)
            return -1
        }
        return 0
    }

    fun generateCode(): String {
        return ((Math.random() * 9 + 1) * 1000).toInt().toString()
    }
}
 */