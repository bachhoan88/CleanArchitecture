package com.example.cleanarchitecture.domain.model

data class Item(
    val id: Int,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val url: String?,
    val stars: Int?,
    val owner: Owner?
) : Model()
