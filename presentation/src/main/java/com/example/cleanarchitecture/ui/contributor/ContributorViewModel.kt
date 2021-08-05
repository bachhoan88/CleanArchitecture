package com.example.cleanarchitecture.ui.contributor

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.contributor.GetContributorUseCase
import com.example.cleanarchitecture.extension.add
import com.example.cleanarchitecture.model.ContributorItem
import com.example.cleanarchitecture.model.ContributorItemMapper
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.util.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    private val getContributorUseCase: GetContributorUseCase,
    private val contributorItemMapper: ContributorItemMapper
) : BaseViewModel() {

    val repoItem = MutableLiveData<RepoItem>()
    val avatar: LiveData<String>
        get() = Transformations.map(repoItem) { it.ownerItem?.avatar }

    private val contributions = MutableLiveData<List<ContributorItem>>()

    fun getContributions(): LiveData<List<ContributorItem>> = Transformations.switchMap(repoItem) { item ->
        when {
            item.name != null && item.ownerItem?.login != null ->
                getContributorUseCase.createObservable(GetContributorUseCase.Params(item.name, item.ownerItem.login))
                    .compose(RxUtils.applyObservableScheduler())
                    .map { it.map { contributorItemMapper.mapToPresentation(it) } }
                    .subscribe({
                        contributions.value = it
                    }, {
                        Timber.e("Get contributor error: $it")
                        setThrowable(it)
                    })
                    .add(this)
        }

        contributions
    }

    @VisibleForTesting
    fun clear() {
        super.onCleared()
    }
}