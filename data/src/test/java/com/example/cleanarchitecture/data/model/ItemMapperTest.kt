package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.createItem
import com.example.cleanarchitecture.data.createItemEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ItemMapperTest {
    private lateinit var itemEntityMapper: ItemEntityMapper
    private lateinit var ownerEntityMapper: OwnerEntityMapper

    @Before
    fun setup() {
        ownerEntityMapper = OwnerEntityMapper()
        itemEntityMapper = ItemEntityMapper(ownerEntityMapper)
    }

    @Test
    fun mapEntityToDomainTest() {
        val itemEntity = createItemEntity()
        val item = itemEntityMapper.mapToDomain(itemEntity)

        assertEquals(itemEntity.id, item.id)
        assertEquals(itemEntity.name, item.name)
        assertEquals(itemEntity.fullName, item.fullName)
        assertEquals(itemEntity.description, item.description)
        assertEquals(itemEntity.url, item.url)
        assertEquals(itemEntity.stars, item.stars)
        assertEquals(itemEntity.ownerEntity, item.owner?.let { ownerEntityMapper.mapToEntity(it) })
    }

    @Test
    fun mapDomainToEntityTest() {
        val item = createItem()
        val itemEntity = itemEntityMapper.mapToEntity(item)

        assertEquals(itemEntity.id, item.id)
        assertEquals(itemEntity.name, item.name)
        assertEquals(itemEntity.fullName, item.fullName)
        assertEquals(itemEntity.description, item.description)
        assertEquals(itemEntity.url, item.url)
        assertEquals(itemEntity.stars, item.stars)
        assertEquals(itemEntity.ownerEntity, item.owner?.let { ownerEntityMapper.mapToEntity(it) })
    }
}