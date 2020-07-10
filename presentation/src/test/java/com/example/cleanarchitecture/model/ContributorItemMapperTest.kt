package com.example.cleanarchitecture.model

import com.example.cleanarchitecture.createContributor
import com.example.cleanarchitecture.createContributorItem
import org.junit.Test
import kotlin.test.assertEquals

class ContributorItemMapperTest {
    private val contributorItemMapper = ContributorItemMapper()

    @Test
    fun mapperFromPresentationToDomain() {
        val contributorItem = createContributorItem()
        val contributor = contributorItemMapper.mapToDomain(contributorItem)

        assertEquals(contributorItem.avatarUrl, contributor.avatarUrl)
        assertEquals(contributorItem.contributions, contributor.contributions)
        assertEquals(contributorItem.login, contributor.login)
    }

    @Test
    fun mapperFromDomainToPresentation() {
        val contributor = createContributor()
        val contributorItem = contributorItemMapper.mapToPresentation(contributor)

        assertEquals(contributorItem.avatarUrl, contributor.avatarUrl)
        assertEquals(contributorItem.contributions, contributor.contributions)
        assertEquals(contributorItem.login, contributor.login)
    }
}