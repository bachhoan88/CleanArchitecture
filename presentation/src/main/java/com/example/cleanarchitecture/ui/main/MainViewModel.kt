package com.example.cleanarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.item.SearchItemUseCase
import com.example.cleanarchitecture.extension.add
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.RepoItemMapper
import com.example.cleanarchitecture.util.RxUtils
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val searchItemUseCase: SearchItemUseCase,
    private val repoItemMapper: RepoItemMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<RepoItem>>()
    val query = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }

    fun searchRepo() {
        query.value?.let { input ->
            if (input.isNotEmpty()) {
                searchItemUseCase.createObservable(SearchItemUseCase.Params(query = input, pageNumber = 1))
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
    }
}
