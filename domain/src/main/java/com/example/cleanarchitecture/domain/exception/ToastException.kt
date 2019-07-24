package com.example.cleanarchitecture.domain.exception

import com.example.cleanarchitecture.domain.annotation.ExceptionType

class ToastException(
    override val code: Int,
    override val message: String
) : CleanException(code, ExceptionType.TOAST, message)