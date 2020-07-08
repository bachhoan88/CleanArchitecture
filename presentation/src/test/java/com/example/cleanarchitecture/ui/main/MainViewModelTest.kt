package com.example.cleanarchitecture.ui.main

import androidx.lifecycle.Observer
import com.example.cleanarchitecture.createItem
import com.example.cleanarchitecture.domain.annotation.Redirect.Companion.OPEN_HOME_SCREEN
import com.example.cleanarchitecture.domain.exception.*
import com.example.cleanarchitecture.domain.model.Dialog
import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.model.Redirect
import com.example.cleanarchitecture.domain.model.Tag
import com.example.cleanarchitecture.domain.usecase.item.SearchItemUseCase
import com.example.cleanarchitecture.mock
import com.example.cleanarchitecture.model.OwnerItemMapper
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.ui.BaseViewModelTest
import io.reactivex.Single
import org.hamcrest.Matchers.nullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.BDDMockito.`when`
import org.mockito.Mock

/**
 * Unit Test for [MainViewModel]
 */
class MainViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var searchItemUseCase: SearchItemUseCase

    private lateinit var mainViewModel: MainViewModel
    private val ownerItemMapper = OwnerItemMapper()
    private val repoItemMapper = RepoItemMapper(ownerItemMapper)

    override fun setup() {
        super.setup()
        mainViewModel = MainViewModel(searchItemUseCase, repoItemMapper)
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
        `when`(searchItemUseCase.createObservable(SearchItemUseCase.Params(query = query)))
            .thenReturn(Single.just(listItem))

        val observer = mock<Observer<List<RepoItem>>>()
        mainViewModel.data.observeForever(observer)

        mainViewModel.searchRepo()
        assertEquals(
            mainViewModel.data.value,
            listItem.map { repoItemMapper.mapToPresentation(item) })
    }

    @Test
    fun testToastException() {
        val query = "query"
        mainViewModel.query.value = query
        val message = "message"

        val toastException = ToastException(code = 1, message = message)
        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(toastException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.toastMessage.value, message)
    }

    @Test
    fun testSnackException() {
        val query = "query"
        mainViewModel.query.value = query
        val message = "message"

        val snackException = SnackBarException(code = 1, message = message)
        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(snackException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.snackBarMessage.value, message)
    }

    @Test
    fun testInlineException() {
        val query = "query"
        mainViewModel.query.value = query
        val tag = Tag(name = "", message = "")

        val inlineException = InlineException(code = 1, tags = *arrayOf(tag))
        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(inlineException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.inlineException.value?.first(), tag)
    }

    @Test
    fun testAlertException() {
        val query = "query"
        mainViewModel.query.value = query
        val message = "message"
        val alertException = AlertException(code = 1, message = message)

        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(alertException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.alertException.value?.second, message)
    }

    @Test
    fun testDialogException() {
        val query = "query"
        mainViewModel.query.value = query
        val message = "message"
        val dialogException = DialogException(code = 1, dialog = Dialog(message = message))

        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(dialogException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.dialogException.value?.message, message)
    }

    @Test
    fun testRedirectException() {
        val query = "query"
        mainViewModel.query.value = query
        val redirectException = RedirectException(code = 1, redirect = Redirect(redirect = OPEN_HOME_SCREEN))

        `when`(searchItemUseCase.createObservable((SearchItemUseCase.Params(query = query))))
            .thenReturn(Single.error(redirectException))

        mainViewModel.searchRepo()
        assertEquals(mainViewModel.redirectException.value?.redirect, OPEN_HOME_SCREEN)
    }

    @Test
    override fun clear() {
        super.clear()
        mainViewModel.clear()
    }
}