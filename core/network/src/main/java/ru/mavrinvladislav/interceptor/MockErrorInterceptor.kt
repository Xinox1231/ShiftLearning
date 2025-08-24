package ru.mavrinvladislav.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockErrorInterceptor : Interceptor {

    var errorCode: Int = 500
    var errorMessage: String = "Internal server error"
    var mockJson: String = """
        {
          "error": "Something went wrong. Our programmers already working on that!"
        }
    """.trimIndent()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val body = mockJson.toResponseBody("application/json".toMediaType())

        return Response.Builder()
            .code(errorCode)
            .message(errorMessage)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(body)
            .addHeader("content-type", "application/json")
            .build()
    }
}