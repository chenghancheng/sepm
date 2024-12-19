package com.hirrao.sepm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorPage {
    @GetMapping("/error")
    fun error(): String {
        return "Error Page"
    }
}
