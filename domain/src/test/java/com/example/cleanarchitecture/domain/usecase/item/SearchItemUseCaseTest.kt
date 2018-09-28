package com.example.cleanarchitecture.domain.usecase.item

import com.example.cleanarchitecture.domain.createItem
import com.example.cleanarchitecture.domain.repository.ItemRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import java.util.*

class SearchItemUseCaseTest {
    private lateinit var searchItemUseCase: SearchItemUseCase
    private val itemRepository: ItemRepository = mock(ItemRepository::class.java)

    @Before
    fun setup() {
        searchItemUseCase = SearchItemUseCase(itemRepository)
    }

    @After
    fun clear() {
        searchItemUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        // given

        // when

        // then
        val params = SearchItemUseCase.Params(query = anyString(), pageNumber = anyInt())

        searchItemUseCase.createObservable(params)

        verify(itemRepository).searchItems(params.query, params.pageNumber)
    }

    @Test
    fun createObservableNull() {
        val useCase = searchItemUseCase.createObservable(null).test()
        useCase.assertError { true }
    }

    @Test
    fun searchComplete() {
        given(itemRepository.searchItems(anyString(), anyInt())).willReturn(Single.just(Arrays.asList(createItem())))

        val test = searchItemUseCase.createObservable(SearchItemUseCase.Params(anyString(), anyInt())).test()

        test.assertComplete()
    }

    @Test
    fun searchReturnData() {
        val params = SearchItemUseCase.Params(query = anyString(), pageNumber = anyInt())
        val items = Arrays.asList(createItem())

        given(itemRepository.searchItems(params.query, params.pageNumber)).willReturn(Single.just(items))
        val test = searchItemUseCase.createObservable(params).test()

        test.assertValue(items)
    }
}