package com.example.cleanarchitecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.RxSchedulersOverrideRule
import com.example.cleanarchitecture.createItem
import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.usecase.item.SearchItemUseCase
import com.example.cleanarchitecture.mock
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Unit Test for [MainViewModel]
 */
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var searchItemUseCase: SearchItemUseCase

    private val schedulerProvider = AppSchedulerProvider()

    private val repoItemMapper = RepoItemMapper()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        mainViewModel = MainViewModel(searchItemUseCase, schedulerProvider, repoItemMapper)
    }

    @Test
    fun searchNull() {
        mainViewModel.query.value = null

        mainViewModel.searchRepo()

        assertThat(mainViewModel.data.value, nullValue())
        assertEquals(mainViewModel.loading.value, false)
    }

    @Test
    fun testSearchUserId() {
        val query = "Bach"
        mainViewModel.query.value = query

        val item = createItem()
        val listItem: List<Item> = arrayListOf(item)
        given(searchItemUseCase.createObservable(SearchItemUseCase.Params(query = query))).willReturn(Single.just(listItem))

        val observer = mock<Observer<List<RepoItem>>>()
        mainViewModel.data.observeForever(observer)

        mainViewModel.searchRepo()

        assertEquals(mainViewModel.data.value, listItem.map { repoItemMapper.mapToPresentation(item) })
    }
}