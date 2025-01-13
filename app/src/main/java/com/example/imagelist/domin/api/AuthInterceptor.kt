package com.example.imagelist.domin.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // Not to put it here
        val newHeader = req.headers.newBuilder().add("Authorization", "kXvvd97Kf9qGgGhyPxa2Giaib6ELF79aWhwBppsjPKRdEZbkshVmolNp").build()
        req = req.newBuilder().headers(newHeader).build()
        return chain.proceed(req)
    }
}