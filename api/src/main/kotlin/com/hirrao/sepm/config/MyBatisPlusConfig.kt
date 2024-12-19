package com.hirrao.sepm.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MyBatisPlusConfig {
    @Bean
    open fun paginationInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        val paginationInnerInterceptor = PaginationInnerInterceptor(DbType.MYSQL)
        interceptor.addInnerInterceptor(paginationInnerInterceptor)
        return interceptor
    }
}
