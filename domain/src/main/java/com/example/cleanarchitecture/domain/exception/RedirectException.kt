package com.example.cleanarchitecture.domain.exception

import com.example.cleanarchitecture.domain.annotation.ExceptionType
import com.example.cleanarchitecture.domain.model.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : CleanException(code, ExceptionType.REDIRECT, null)