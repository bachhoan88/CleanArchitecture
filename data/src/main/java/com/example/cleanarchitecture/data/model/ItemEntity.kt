package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.base.EntityMapper
import com.example.cleanarchitecture.data.base.ModelEntity
import com.example.cleanarchitecture.domain.model.Item
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ItemEntity(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val description: String,
        @SerializedName("url") val url: String
) : ModelEntity()

class ItemEntityMapper @Inject constructor() : EntityMapper<Item, ItemEntity> {
    override fun mapToDomain(entity: ItemEntity) = Item(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            url = entity.url
    )

    override fun mapToEntity(model: Item) = ItemEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            url = model.url
    )

}