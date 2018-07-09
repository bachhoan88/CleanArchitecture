package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.model.Item
import io.reactivex.Single

interface ItemRepository : Repository {
    fun getItem(id: Int): Single<Item>

    fun searchItems(query: String, page: Int? = 1): Single<List<Item>>
}