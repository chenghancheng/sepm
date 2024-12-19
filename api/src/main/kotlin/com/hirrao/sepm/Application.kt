package com.hirrao.sepm

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan("com.hirrao.sepm.mapper")
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}