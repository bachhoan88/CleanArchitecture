package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.model.ItemEntityMapper
import com.example.cleanarchitecture.data.remote.api.ItemApi
import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.repository.ItemRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryImpl @Inject constructor(
        private val mItemApi: ItemApi,
        private val mItemEntityMapper: ItemEntityMapper
) : ItemRepository {
    override fun searchItems(query: String, page: Int?): Single<List<Item>> {
        return mItemApi.searchRepos(query = query, page = if (page == null) 0 else page)
                .map {
                    it.items.map { mItemEntityMapper.mapToDomain(it) }
                }
                .doOnError { Throwable("Not found!") }
    }

    override fun getItem(id: Int): Single<Item> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}