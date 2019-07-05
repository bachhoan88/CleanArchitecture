package com.example.cleanarchitecture.domain.usecase.user

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class FindUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<FindUserUseCase.Params?, Single<User>>() {

    override fun createObservable(params: Params?): Single<User> {
        if (params != null) {
            return userRepository.getUser(params.userId, params.fromServer)
        }

        return Single.error(Throwable("Params input not valid"))
    }

    data class Params(val userId: String, val fromServer: Boolean)
}