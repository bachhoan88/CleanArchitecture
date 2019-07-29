package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.base.ItemMapper
import com.example.cleanarchitecture.base.ModelItem
import com.example.cleanarchitecture.domain.model.User
import javax.inject.Inject

data class UserItem(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val address: String
) : ModelItem()

class UserItemMapper @Inject constructor() : ItemMapper<User, UserItem> {
    override fun mapToPresentation(model: User): UserItem = UserItem(
            id = model.id,
            name = model.name,
            username = model.username,
            email = model.email,
            phone = model.phone,
            address = model.address
    )

    override fun mapToDomain(modelItem: UserItem) = User(
            id = modelItem.id,
            name = modelItem.name,
            username = modelItem.username,
            email = modelItem.email,
            phone = modelItem.phone,
            address = modelItem.address
    )
}