package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.ObserverTestUtils.getJson
import com.example.cleanarchitecture.data.ObserverTestUtils.getValue
import com.example.cleanarchitecture.domain.repository.ItemRepository
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
    fun testSearch() {
        val testObserver = TestObserver<ItemRepository>()

        // Mock a response with status 200 and sample JSON output
        val mockReponse = MockResponse()
                .setResponseCode(200)
                .setBody(getJson("search.json"))

        // Enqueue request
        mockWebServer.enqueue(mockReponse)

    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(source.readString(Charsets.UTF_8))
        )
    }
}