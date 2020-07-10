package com.example.cleanarchitecture

import com.example.cleanarchitecture.data.model.ItemEntity
import com.example.cleanarchitecture.data.model.OwnerEntity
import com.example.cleanarchitecture.domain.model.Contributor
import com.example.cleanarchitecture.domain.model.Item
import com.example.cleanarchitecture.domain.model.Owner
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.model.ContributorItem
import com.example.cleanarchitecture.model.OwnerItem
import com.example.cleanarchitecture.model.RepoItem
import com.example.cleanarchitecture.model.UserItem

fun createUserItem(): UserItem = UserItem("1",
        "Bach",
        "bachhoan88",
        "hoanbn88@gmail.com",
        "0904576359  ",
        "Tu Liem - Ha Noi")

fun createUser(): User = User(
        "2",
        "Hoan",
        "hoanbn88",
        "bach.ngoc.hoai@framgia.com",
        "0123456789",
        "Thanh Liem - Ha Nam"
)

fun createRepoEntity() = ItemEntity(
        id = 1,
        name = "Bach",
        fullName = "Bach Hoan",
        description = "framgia",
        url = "",
        stars = 1,
        ownerEntity = OwnerEntity(id = 1, login = null, avatar = null)
)

fun createRepoItem() = RepoItem(
        id = 1,
        name = "Bach",
        fullName = "Bach Hoan",
        description = "framgia",
        url = "",
        stars = "5",
        ownerItem = OwnerItem(id = 1, login = null, avatar = null)
)

fun createItem() = Item(
        id = 2,
        name = "Hoan",
        fullName = "Bach Hoan",
        description = "ha nam",
        url = "",
        stars = 1,
        owner = Owner(id = 1, login = "abc", avatar = null)
)

fun createOwnerItem() = OwnerItem(id = 1, login = null, avatar = null)

fun createOwner() = Owner(id = 1, login = null, avatar = null)

fun createContributor() = Contributor(
        login = "abc",
        contributions = 1,
        avatarUrl = "abc"
)

fun createContributorItem() = ContributorItem(
        login = "abc",
        contributions = 1,
        avatarUrl = "abc"
)