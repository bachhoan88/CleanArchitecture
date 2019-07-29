package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.data.createItem
import com.example.cleanarchitecture.data.createItemEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ItemMapperTest {
    private lateinit var itemEntityMapper: ItemEntityMapper

    @Before
    fun setup() {
        itemEntityMapper = ItemEntityMapper()
    }

    @Test
    fun mapEntityToDomainTest() {
        val itemEntity = createItemEntity()
        val item = itemEntityMapper.mapToDomain(itemEntity)

        assertEquals(itemEntity.id, item.id)
        assertEquals(itemEntity.name, item.name)
        assertEquals(itemEntity.description, item.description)
        assertEquals(itemEntity.url, item.url)
    }

    @Test
    fun mapDomainToEntityTest() {
        val item = createItem()
        val itemEntity = itemEntityMapper.mapToEntity(item)

        assertEquals(itemEntity.id, item.id)
        assertEquals(itemEntity.name, item.name)
        assertEquals(itemEntity.description, item.description)
        assertEquals(itemEntity.url, item.url)
    }
}