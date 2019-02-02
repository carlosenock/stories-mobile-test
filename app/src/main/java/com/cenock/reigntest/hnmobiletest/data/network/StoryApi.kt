package com.cenock.reigntest.hnmobiletest.data.network

import com.cenock.reigntest.hnmobiletest.model.StoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryApi {

    @GET(ApiDefinitions.SEARCH_BY_DATE)
    fun getStories(@Query("query") platform: String): Call<StoryResponse>
}