package com.example.cleanarchitecture.domain.usecase.user

import com.example.cleanarchitecture.domain.createUser
import com.example.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class FindUserUseCaseTest {
    private lateinit var findUserUseCase: FindUserUseCase

    private val userRepository = mock(UserRepository::class.java)

    @Before
    fun setup() {
        findUserUseCase = FindUserUseCase(userRepository)
    }

    @Test
    fun createObservable() {
        val params = FindUserUseCase.Params(userId = anyString(), fromServer = anyBoolean())
        findUserUseCase.createObservable(params)

        verify(userRepository).getUser(params.userId, params.fromServer)
    }

    @Test
    fun createObservableNull() {
        val test = findUserUseCase.createObservable(null).test()
        test.assertError { false }
    }

    @Test
    fun getUserComplete() {
        given(userRepository.getUser(anyString(), anyBoolean())).willReturn(Single.just(createUser()))
        val test = findUserUseCase.createObservable(FindUserUseCase.Params(anyString(), anyBoolean())).test()
        test.assertComplete()
    }

    @Test
    fun getUserReturnData() {
        val params = FindUserUseCase.Params(userId = anyString(), fromServer = anyBoolean())
        val user = createUser()

        `when`(userRepository.getUser(id = params.userId, fromServer = params.fromServer)).thenReturn(Single.just(user))
        val test = findUserUseCase.createObservable(params).test()
        test.assertValue(user)
    }
}