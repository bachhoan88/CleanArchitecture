package com.example.cleanarchitecture.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.item.SearchItemUseCase
import com.example.cleanarchitecture.extension.add
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.util.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchItemUseCase: SearchItemUseCase,
    private val repoItemMapper: RepoItemMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<RepoItem>>()
    val query = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }

    fun searchRepo() = when (query.value.isNullOrEmpty()) {
        true -> Unit
        false -> query.value?.let {
            searchItemUseCase.createObservable(SearchItemUseCase.Params(query = it, pageNumber = 1))
                .compose(RxUtils.applySingleScheduler(loading))
                .doFinally { loading.value = false }
                .map { it.map { repoItemMapper.mapToPresentation(it) } }
                .subscribe({ data.value = it }, {
                    Timber.e("Get repo error: $it")
                    setThrowable(it)
                })
                .add(this)
        }
    }

    @VisibleForTesting
    fun clear() {
        super.onCleared()
    }
}