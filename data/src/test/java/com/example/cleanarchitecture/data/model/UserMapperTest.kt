package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.createUser
import com.example.cleanarchitecture.data.createUserEntity
import org.junit.Before
import org.junit.Test

class UserMapperTest {
    private lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setup() {
        userEntityMapper = UserEntityMapper()
    }

    @Test
    fun mapEntityToDomainTest() {
        // generate user entity
        val entity = createUserEntity()
        // mapper
        val model = userEntityMapper.mapToDomain(entity)

        assert(entity.id == model.id)
        assert(entity.name == model.name)
        assert(entity.username == model.username)
        assert(entity.address == model.address)
        assert(entity.email == model.email)
        assert(entity.phone == model.phone)
    }

    @Test
    fun mapDomainToEntityTest() {
        // generate model
        val model = createUser()

        // mapper to entity
        val entity = userEntityMapper.mapToEntity(model)

        assert(entity.id == model.id)
        assert(entity.name == model.name)
        assert(entity.username == model.username)
        assert(entity.address == model.address)
        assert(entity.email == model.email)
        assert(entity.phone == model.phone)
    }
}