package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.createItem
import com.example.cleanarchitecture.createRepoItem
import org.junit.Test
import kotlin.test.assertEquals

class RepoItemMapperTest {
    private val ownerItemMapper = OwnerItemMapper()
    private val repoItemMapper = RepoItemMapper(ownerItemMapper)

    @Test
    fun mapperFromPresentationToDomain() {
        val repoItem = createRepoItem()
        val item = repoItemMapper.mapToDomain(repoItem)

        assertEquals(repoItem.id, item.id)
        assertEquals(repoItem.name, item.name)
        assertEquals(repoItem.description, item.description)
        assertEquals(repoItem.fullName, item.fullName)
        assertEquals(repoItem.stars.toIntOrNull(), item.stars)
        assertEquals(repoItem.url, item.url)
        item.owner?.let {
            assertEquals(repoItem.ownerItem, ownerItemMapper.mapToPresentation(it))
        }
    }

    @Test
    fun mapperFromDomainToPresentation() {
        val item = createItem()
        val repoItem = repoItemMapper.mapToPresentation(item)

        assertEquals(repoItem.id, item.id)
        assertEquals(repoItem.name, item.name)
        assertEquals(repoItem.description, item.description)
        assertEquals(repoItem.fullName, item.fullName)
        assertEquals(repoItem.stars.toIntOrNull(), item.stars)
        assertEquals(repoItem.url, item.url)
        item.owner?.let {
            assertEquals(repoItem.ownerItem, ownerItemMapper.mapToPresentation(it))
        }
    }
}