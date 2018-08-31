package com.example.cleanarchitecture.ui.main

import android.arch.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.item.SearchItemUseCase
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.rx.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val mSearchItemUseCase: SearchItemUseCase,
        private val mSchedulerProvider: SchedulerProvider,
        private val mRepoItemMapper: RepoItemMapper
) : BaseViewModel(mSearchItemUseCase) {

    val data = MutableLiveData<List<RepoItem>>()
    val query = MutableLiveData<String>()

    fun searchRepo() {

        query.value?.let {
            if (it.isNotBlank()) {
                compositeDisposable.add(mSearchItemUseCase.createObservable(SearchItemUseCase.Params(query = it, pageNumber = 1))
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .map { it.map { mRepoItemMapper.mapToPresentation(it) } }
                        .subscribe({
                            data.value = it
                        }, {})
                )
            }
        }
    }

}
