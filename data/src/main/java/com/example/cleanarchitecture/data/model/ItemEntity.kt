package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.base.EntityMapper
import com.example.cleanarchitecture.data.base.ModelEntity
import com.example.cleanarchitecture.domain.model.Item
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ItemEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("stargazers_count") val stars: Int?,
    @SerializedName("owner") val ownerEntity: OwnerEntity?
) : ModelEntity()

class ItemEntityMapper @Inject constructor(
    private val ownerEntityMapper: OwnerEntityMapper
) : EntityMapper<Item, ItemEntity> {
    override fun mapToDomain(entity: ItemEntity) = Item(
        id = entity.id,
        name = entity.name,
        fullName = entity.fullName,
        description = entity.description,
        url = entity.url,
        stars = entity.stars,
        owner = entity.ownerEntity?.let { ownerEntityMapper.mapToDomain(it) }
    )

    override fun mapToEntity(model: Item) = ItemEntity(
        id = model.id,
        name = model.name,
        fullName = model.fullName,
        description = model.description,
        url = model.url,
        stars = model.stars,
        ownerEntity = model.owner?.let { ownerEntityMapper.mapToEntity(it) }
    )
}