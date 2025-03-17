package com.smartAd.api.interfaces

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageRouteController {

    @GetMapping("/", "")
    fun rootIndex() = "index"
    @GetMapping("/auth/login")
    fun login() = "auth-login"

    @GetMapping("/auth/register")
    fun register() = "auth-register"

    @GetMapping("/auth/password")
    fun password() = "auth-forgot-password"
}