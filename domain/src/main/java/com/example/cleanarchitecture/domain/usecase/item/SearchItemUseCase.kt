package com.example.cleanarchitecture.domain.usecase.item

import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.repository.ItemRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class SearchItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) : UseCase<SearchItemUseCase.Params, Single<List<Item>>>() {
    override fun createObservable(params: Params?): Single<List<Item>> {
        params?.let { return itemRepository.searchItems(query = params.query, page = params.pageNumber) }

        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(val query: String, val pageNumber: Int? = 1)
}