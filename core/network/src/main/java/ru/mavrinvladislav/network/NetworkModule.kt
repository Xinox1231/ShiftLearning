package ru.mavrinvladislav.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mavrinvladislav.di.scopes.ApplicationScope
import ru.mavrinvladislav.interceptor.MockErrorInterceptor

@Module
interface NetworkModule {

    companion object {

        private const val BASE_URL = "https://randomuser.me/api/"

        private const val IS_ERROR_EXPECTED = false

        @Provides
        @ApplicationScope
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        @Provides
        @ApplicationScope
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
            mockErrorInterceptor: MockErrorInterceptor
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)

            if (IS_ERROR_EXPECTED) {
                builder.addInterceptor(mockErrorInterceptor)
            }

            return builder.build()
        }

        @Provides
        @ApplicationScope
        fun provideMockErrorInterceptor(): MockErrorInterceptor {
            return MockErrorInterceptor().apply {
                errorCode = 500
                errorMessage = "Test server error"
                mockJson = """{"error": "Test error message"}"""
            }
        }

        @Provides
        @ApplicationScope
        fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }
}