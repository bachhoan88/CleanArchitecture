package com.example.cleanarchitecture.data.remote.api

import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith (JUnit4::class)
class ItemApiTest {

    private lateinit var itemApi: ItemApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        itemApi = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemApi::class.java)
    }

}