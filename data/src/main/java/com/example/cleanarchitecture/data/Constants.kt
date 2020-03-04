package com.example.cleanarchitecture.data

object Constants {
    const val DATABASE_NAME = "example.db"
}

object HttpClient {
    const val CONNECT_TIMEOUT = 10L
    const val READ_TIMEOUT = 10L
    const val WRITE_TIMEOUT = 10L
    const val CONNECTION_TIME_OUT_MLS = CONNECT_TIMEOUT * 1000L
}

object Authentication {
    const val MAX_RETRY = 1
}