package com.example.cleanarchitecture.domain

import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.model.Owner

fun createItem() = Item(
        id = 2,
        name = "Hoan",
        fullName = "Bach Hoan",
        description = "ha nam",
        url = "",
        stars = 1,
        owner = Owner(id = 1, login = null, avatar = null)
)