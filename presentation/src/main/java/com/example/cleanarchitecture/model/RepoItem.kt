package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.base.ItemMapper
import com.example.cleanarchitecture.base.ModelItem
import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.model.Model
import javax.inject.Inject

data class RepoItem(
        val id: Int,
        val name: String,
        val description: String,
        val url: String
) : ModelItem()

class RepoItemMapper @Inject constructor() : ItemMapper<Item, RepoItem> {
    override fun mapToPresentation(model: Item) = RepoItem(
            id = model.id,
            name = model.name,
            description = model.description,
            url = model.url
    )

    override fun mapToDomain(modelItem: RepoItem) = Item(
        id = modelItem.id,
        name = modelItem.name,
        description = modelItem.description,
        url = modelItem.url
    )

}