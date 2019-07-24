package com.example.cleanarchitecture.domain.model

import com.example.cleanarchitecture.domain.annotation.Redirect

data class Redirect(@Redirect val redirect: Int, val redirectObject: Any? = null)