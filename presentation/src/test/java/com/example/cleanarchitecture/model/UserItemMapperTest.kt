package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.createUserItem
import com.example.cleanarchitecture.createUser
import org.junit.Before
import org.junit.Test

class UserItemMapperTest {
    private lateinit var userItemMapper: UserItemMapper

    @Before
    fun setup() {
        userItemMapper = UserItemMapper()
    }

    @Test
    fun mapPresentationToDomainTest() {
        // generate user entity
        val item = createUserItem()
        // mapper
        val model = userItemMapper.mapToDomain(item)

        assert(item.id == model.id)
        assert(item.name == model.name)
        assert(item.username == model.username)
        assert(item.address == model.address)
        assert(item.email == model.email)
        assert(item.phone == model.phone)
    }

    @Test
    fun mapDomainToPresentationTest() {
        // generate model
        val model = createUser()

        // mapper to entity
        val item = userItemMapper.mapToPresentation(model)

        assert(item.id == model.id)
        assert(item.name == model.name)
        assert(item.username == model.username)
        assert(item.address == model.address)
        assert(item.email == model.email)
        assert(item.phone == model.phone)
    }
}