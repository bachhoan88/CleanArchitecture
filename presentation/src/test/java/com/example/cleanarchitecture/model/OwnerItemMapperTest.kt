package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.createOwner
import com.example.cleanarchitecture.createOwnerItem
import org.junit.Test
import kotlin.test.assertEquals

class OwnerItemMapperTest {
    private val ownerItemMapper = OwnerItemMapper()

    @Test
    fun mapperFromPresentationToDomain() {
        val ownerItem = createOwnerItem()
        val owner = ownerItemMapper.mapToDomain(ownerItem)

        assertEquals(ownerItem.id, owner.id)
        assertEquals(ownerItem.avatar, owner.avatar)
        assertEquals(ownerItem.login, owner.login)
    }

    @Test
    fun mapperFromDomainToPresentation() {
        val owner = createOwner()
        val ownerItem = ownerItemMapper.mapToPresentation(owner)

        assertEquals(ownerItem.id, owner.id)
        assertEquals(ownerItem.avatar, owner.avatar)
        assertEquals(ownerItem.login, owner.login)
    }
}