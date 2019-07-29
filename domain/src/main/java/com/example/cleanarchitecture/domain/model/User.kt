package com.example.cleanarchitecture.domain.model

data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val address: String
) : Model()
