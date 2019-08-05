package com.example.cleanarchitecture.domain.usecase.contributor

import com.example.cleanarchitecture.domain.exception.AlertException
import com.example.cleanarchitecture.domain.model.Contributor
import com.example.cleanarchitecture.domain.repository.ContributorRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetContributorUseCase @Inject constructor(
    private val contributorRepository: ContributorRepository
) : UseCase<GetContributorUseCase.Params, Observable<List<Contributor>>>() {

    override fun createObservable(params: Params?): Observable<List<Contributor>> {
        return when (params) {
            null -> Observable.error(AlertException(code = -1, message = "Params input not null"))
            else -> contributorRepository.getContribution(params.name, params.owner)
        }
    }

    data class Params(val name: String, val owner: String)
}