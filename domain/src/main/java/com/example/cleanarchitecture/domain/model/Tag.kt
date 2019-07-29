package com.example.cleanarchitecture.domain.model

import com.example.cleanarchitecture.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)