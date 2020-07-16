package com.example.cleanarchitecture.ui.contributor

import com.example.cleanarchitecture.createContributor
import com.example.cleanarchitecture.createItem
import com.example.cleanarchitecture.domain.exception.ToastException
import com.example.cleanarchitecture.domain.usecase.contributor.GetContributorUseCase
import com.example.cleanarchitecture.model.ContributorItemMapper
import com.example.cleanarchitecture.model.OwnerItemMapper
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.ui.BaseViewModelTest
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class ContributorViewModelTest : BaseViewModelTest() {
    @Mock
    private lateinit var getContributorUseCase: GetContributorUseCase

    private lateinit var contributorViewModel: ContributorViewModel
    private val contributeItemMapper = ContributorItemMapper()
    private val ownerItemMapper = OwnerItemMapper()
    private val repoItemMapper = RepoItemMapper(ownerItemMapper)

    override fun setup() {
        super.setup()
        contributorViewModel = ContributorViewModel(getContributorUseCase, contributeItemMapper)
    }

    @Test
    fun verifyAvatar() {
        val repoItem = createItem()
        contributorViewModel.repoItem.value = repoItemMapper.mapToPresentation(repoItem)
        repoItem.owner?.let { owner ->
            assertEquals(contributorViewModel.avatar.value, ownerItemMapper.mapToPresentation(owner).avatar)
        }
    }

    @Test
    fun getContributorSuccess() {
        val item = createItem()
        val defaultValue = "abc"
        val contributor = createContributor()
        val contributors = listOf(contributor)

        `when`(getContributorUseCase.createObservable(GetContributorUseCase.Params(item.name ?: defaultValue, item.owner?.login ?: defaultValue)))
            .thenReturn(Observable.just(contributors))

        contributorViewModel.repoItem.value = repoItemMapper.mapToPresentation(item)
        contributorViewModel.getContributions().observeForever {
            assertEquals(it, contributors.map { contributeItemMapper.mapToPresentation(it) })
        }
    }

    @Test
    fun getContributorError() {
        val item = createItem()
        val defaultValue = "abc"
        val message = "message"
        val toastException = ToastException(code = 1, message = message)

        `when`(getContributorUseCase.createObservable(GetContributorUseCase.Params(item.name ?: defaultValue, item.owner?.login ?: defaultValue)))
            .thenReturn(Observable.error(toastException))

        contributorViewModel.repoItem.value = repoItemMapper.mapToPresentation(item)
        contributorViewModel.toastMessage.observeForever {
            assertEquals(it, message)
        }
    }

    @Test
    override fun clear() {
        super.clear()
        contributorViewModel.clear()
    }
}