package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) : UseCase() {
    /**
     * Communicate  Presentation with Data layer
     * Y can execute business logic from here:
     *      + Execute error
     *      + Execute save to storage
     *      + ...
     */
    fun getUser(id: String, fromServer: Boolean): Single<User> = repository.getUser(id, fromServer)
}