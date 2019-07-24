package com.example.cleanarchitecture.domain.exception

import com.example.cleanarchitecture.domain.annotation.ExceptionType

open class CleanException(
    open val code: Int,
    @ExceptionType val exceptionType: Int,
    override val message: String?
) : Throwable(message)