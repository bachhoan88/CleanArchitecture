package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.ObserverTestUtils.getJson
import com.example.cleanarchitecture.data.remote.response.SearchRepoResponse
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ItemApiTest {

    private lateinit var itemApi: ItemApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        itemApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ItemApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun testMockResponse() {
        val mockedResponse = MockResponse()

        mockedResponse.setResponseCode(200)
        mockedResponse.setBody("{}")

        mockWebServer.enqueue(mockedResponse)
    }

    @Test
    fun testSearchResponseOk() {
        val testObserver = TestObserver<SearchRepoResponse>()
        val query = "query"
        val page = 1

        val path = "/search/repositories?q=$query&page=$page"
        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(getJson("search.json"))

        // Enqueue request
        mockWebServer.enqueue(mockResponse)

        // Call API
        itemApi.searchRepos(query, page).toObservable().subscribe(testObserver)
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)

        // then no error
        testObserver.assertNoErrors()
        // check values
        testObserver.assertValueCount(1)

        // get request to test
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, path)

        // test success value by search.json
        testObserver.assertValue { searchRepoResponse ->
            searchRepoResponse.total == 41 &&
                    searchRepoResponse.items.get(0).id == 63478084
        }
    }

    @Test
    fun testSearchResponseError() {
//        val testObserver = TestObserver<SearchRepoResponse>()
//        val query = anyString()
//        val page = anyInt()
//
//        // Mock a response with status 200 and sample JSON output
//        val mockResponse = MockResponse()
//                .setResponseCode(500)
//
//        // Enqueue request
//        mockWebServer.enqueue(mockResponse)
//
//        // Call API
//        itemApi.searchRepos(query, page).toObservable().subscribe(testObserver)
//        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
//
//        // no value
//        testObserver.assertNoValues()
    }
}