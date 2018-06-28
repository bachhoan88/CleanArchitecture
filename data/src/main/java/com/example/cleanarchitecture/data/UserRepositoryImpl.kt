package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.local.db.AppDatabase
import com.example.cleanarchitecture.data.model.UserEntity
import com.example.cleanarchitecture.data.model.UserEntityMapper
import com.example.cleanarchitecture.data.remote.api.UserApi
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
        private val mUserApi: UserApi,
        private val mAppDatabase: AppDatabase,
        private val mMapper: UserEntityMapper
) : UserRepository {
    override fun signin(userName: String, password: String): Completable {
        return mUserApi.signin(userName, password)
    }

    override fun getUser(id: String, fromServer: Boolean): Single<User> = when (fromServer) {
        false -> mAppDatabase.userDao().findById(id).map { mMapper.mapToDomain(it) }
        true -> mUserApi.getUser(id)
                .map { mMapper.mapToDomain(it) }
                .onErrorResumeNext(getUser(id, false))
    }

    override fun saveUser(user: User) {
        return set(mMapper.mapToEntity(user))
    }

    private fun set(userEntity: UserEntity) = mAppDatabase.userDao().insert(userEntity)
}