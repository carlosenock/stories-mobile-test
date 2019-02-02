package com.cenock.reigntest.hnmobiletest.data.network

import com.cenock.reigntest.hnmobiletest.model.StoryResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient {

    private val api: StoryApi

    companion object {

        private var instance: NetworkClient? = null

        private const val TIME_OUT_MINS = 3L

        fun getInstance(): NetworkClient {
            if (instance == null) {
                instance =
                        NetworkClient()
            }
            return instance as NetworkClient
        }

    }

    init {

        val retrofit = initRetrofit()

        api = retrofit.create(StoryApi::class.java)

    }

    private fun initRetrofit(): Retrofit {
        /**
         * Logging interceptor
         */
        val bodyInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        /**
         * Headers interceptor
         */
        val headersInterceptor = Interceptor { chain ->

            val original = chain.request()

            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        val httpClient = OkHttpClient().newBuilder()
            .addInterceptor(bodyInterceptor)
            .addInterceptor(headersInterceptor)
            .connectTimeout(TIME_OUT_MINS, TimeUnit.MINUTES)
            .readTimeout(TIME_OUT_MINS, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiDefinitions.STORY_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun getStories(cb: Callback<StoryResponse>) {
        api.getStories(ApiDefinitions.PLATFORM).enqueue(cb)
    }
}