package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.model.ItemEntity
import com.example.cleanarchitecture.domain.model.Item

fun createItemEntity() = ItemEntity(
        id = 1,
        name = "Bach",
        description = "framgia",
        url = ""
)

fun createItem() = Item(
        id = 2,
        name = "Hoan",
        description = "ha nam",
        url = ""
)