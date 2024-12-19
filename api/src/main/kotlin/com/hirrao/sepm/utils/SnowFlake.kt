package com.hirrao.sepm.utils

class SnowFlake(datacenterId: Long, machineId: Long) {
    private val datacenterId: Long // 数据中心
    private val machineId: Long // 机器标识
    private var sequence = 0L // 序列号
    private var lastTimestamp = -1L // 上一次时间戳

    init {
        require(!(datacenterId > MAX_DATACENTER_NUM || datacenterId < 0)) { "Datacenter ID can't be greater than MAX_DATACENTER_NUM or less than 0" }
        require(!(machineId > MAX_MACHINE_NUM || machineId < 0)) { "Machine ID can't be greater than MAX_MACHINE_NUM or less than 0" }
        this.datacenterId = datacenterId
        this.machineId = machineId
    }

    /**
     * 产生下一个ID
     *
     * @return 下一个ID
     */
    @Synchronized
    fun nextId(): Long {
        var currTimestamp = timestamp
        if (currTimestamp < lastTimestamp) {
            throw RuntimeException("Clock moved backwards. Refusing to generate id")
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (currTimestamp == lastTimestamp) {
            sequence = (sequence + 1) and MAX_SEQUENCE
            // 序列号已经达到最大值，下一个毫秒
            if (sequence == 0L) {
                currTimestamp = nextTimestamp
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L
        }

        // 上次生成ID的时间截
        lastTimestamp = currTimestamp

        // 移位并通过或运算拼到一起组成64位的ID
        return ((currTimestamp - START_TIMESTAMP) shl TIMESTAMP_LEFT.toInt() // 时间戳部分
                or (datacenterId shl DATACENTER_LEFT.toInt() // 数据中心部分
                ) or (machineId shl MACHINE_LEFT.toInt() // 机器标识部分
                ) or sequence) // 序列号部分
    }

    private val nextTimestamp: Long
        /**
         * 获取下一个毫秒数
         *
         * @return 下一个毫秒数
         */
        get() {
            var timestamp = timestamp
            while (timestamp <= lastTimestamp) {
                timestamp = this.timestamp
            }
            return timestamp
        }

    private val timestamp: Long
        /**
         * 获取当前的时间戳
         *
         * @return 当前的时间戳
         */
        get() = System.currentTimeMillis()

    companion object {
        private const val START_TIMESTAMP = 1480166465631L

        // 每一部分占用的位数，符号位不算在内
        private const val SEQUENCE_BIT: Long = 12 // 序列号占用的位数
        private const val MACHINE_BIT: Long = 5 // 机器标识占用的位数
        private const val DATACENTER_BIT: Long = 5 // 数据中心占用的位数

        // 每一部分的最大值
        private const val MAX_SEQUENCE = (-1L shl SEQUENCE_BIT.toInt()).inv()
        private const val MAX_MACHINE_NUM = (-1L shl MACHINE_BIT.toInt()).inv()
        private const val MAX_DATACENTER_NUM = (-1L shl DATACENTER_BIT.toInt()).inv()

        // 每一部分向左的位移
        private const val MACHINE_LEFT = SEQUENCE_BIT
        private const val DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT
        private const val TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT
    }
}