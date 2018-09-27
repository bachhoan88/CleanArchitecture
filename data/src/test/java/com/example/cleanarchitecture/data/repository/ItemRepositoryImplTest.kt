package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.ItemRepositoryImpl
import com.example.cleanarchitecture.data.createItemEntity
import com.example.cleanarchitecture.data.model.ItemEntityMapper
import com.example.cleanarchitecture.data.remote.api.ItemApi
import com.example.cleanarchitecture.data.remote.response.SearchRepoResponse
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import java.util.*

class ItemRepositoryImplTest {
    private lateinit var itemRepositoryImpl: ItemRepositoryImpl
    private val itemEntityMapper = ItemEntityMapper()
    private val itemApi = mock(ItemApi::class.java)

    @Before
    fun setup() {
        itemRepositoryImpl = ItemRepositoryImpl(itemApi, itemEntityMapper)
    }

    @Test
    fun searchComplete() {
        val query = anyString()
        val page = anyInt()

        val searchRepoResponse = SearchRepoResponse(total = 1, items = Arrays.asList(createItemEntity()))
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.just(searchRepoResponse))
        itemRepositoryImpl.searchItems(query, page).test().assertComplete()
    }

    @Test
    fun searchItems() {
        val query = anyString()
        val page = anyInt()

        val searchRepoResponse = SearchRepoResponse(total = 1, items = Arrays.asList(createItemEntity()))
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.just(searchRepoResponse))

        val test = itemRepositoryImpl.searchItems(query, page).test()
        test.assertValue { items ->
            items == searchRepoResponse.items.map { itemEntityMapper.mapToDomain(it) }
        }
    }

    @Test
    fun searchReturnNull() {
        val query = anyString()
        val page = anyInt()
        `when`(itemApi.searchRepos(query, page)).thenReturn(Single.error(Throwable("error")))

        val test = itemRepositoryImpl.searchItems(query, page).test()
        test.assertError {
            true
        }
    }
}