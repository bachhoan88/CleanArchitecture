package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.model.ContributorEntityMapper
import com.example.cleanarchitecture.data.remote.api.ContributorApi
import com.example.cleanarchitecture.domain.model.Contributor
import com.example.cleanarchitecture.domain.repository.ContributorRepository
import io.reactivex.Observable
import javax.inject.Inject

class ContributorRepositoryImpl @Inject constructor(
    private val contributorApi: ContributorApi,
    private val contributorEntityMapper: ContributorEntityMapper
) : ContributorRepository {

    override fun getContribution(name: String, owner: String): Observable<List<Contributor>> {
        return contributorApi.getContributors(owner, name)
            .map { it.map { contributorEntityMapper.mapToDomain(it) } }
    }
}