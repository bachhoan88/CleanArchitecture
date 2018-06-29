package com.example.cleanarchitecture

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.model.UserItem

fun createUserItem(): UserItem = UserItem("1",
        "Bach",
        "bachhoan88",
        "hoanbn88@gmail.com",
        "0904576359  ",
        "Tu Liem - Ha Noi")

fun createUser(): User = User(
        "2",
        "Hoan",
        "hoanbn88",
        "bach.ngoc.hoai@framgia.com",
        "0123456789",
        "Thanh Liem - Ha Nam"
)