package com.example.cleanarchitecture.data

import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


object ObserverTestUtils {
    fun <T> getValue(singleData: Single<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)

        singleData.subscribe({ t ->
            data[0] = t
        }, {})

        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }

    fun getJson(fileName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")

        val reader = BufferedReader(InputStreamReader(inputStream))
        val out = StringBuilder()
        var line: String

        do {
            line = reader.readLine()
            out.append(line)
        } while (line != null)

        return out.toString()
    }
}