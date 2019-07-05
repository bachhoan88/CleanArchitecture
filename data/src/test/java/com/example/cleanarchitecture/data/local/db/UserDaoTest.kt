package com.example.cleanarchitecture.data.local.db

import com.example.cleanarchitecture.data.createUserEntity
import com.example.cleanarchitecture.data.model.UserEntity
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21))
class UserDaoTest : DbTest() {

    @Test
    fun testInsertAndGet() {
        // fake user entity
        val userEntity = createUserEntity()
        db.userDao().insert(userEntity)

        // create observer to get user entity with user id
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // get user id
        db.userDao().findById(userEntity.id).toObservable().subscribe(testObserver)

        // data not null or no errors
        testObserver.assertNoErrors()
        testObserver.assertValue {
            userEntity == it
        }
    }

    @Test
    fun findNotExists() {
        val userId = anyString()
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        db.userDao().findById(userId).toObservable().subscribe(testObserver)
        testObserver.assertValueCount(0)
    }

    @Test
    fun testInsertValueExists() {
        // create fake user
        val userId = "1"
        val userEntity1 = UserEntity(userId,
            "Bach",
            "bachhoan88",
            "hoanbn88@gmail.com",
            "0904576359  ",
            "Tu Liem - Ha Noi")

        val userEntity2 = UserEntity(userId,
            "Hoan",
            "abc123",
            "email@gmail.com",
            "01234567  ",
            "Address")
        // first insert
        db.userDao().insert(userEntity1)

        // re-insert user
        db.userDao().insert(userEntity2)

        // check data
        val testObserver = TestObserver<UserEntity>()
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        db.userDao().findById(userId).toObservable().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it == userEntity2
        }
    }
}