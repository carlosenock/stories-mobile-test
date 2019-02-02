package com.cenock.reigntest.hnmobiletest.data

import com.cenock.reigntest.hnmobiletest.model.StoryResponse

interface StoryDataSource {
    fun getStories(callback: StoryCallback)

    interface StoryCallback {
        fun onSuccess(stories: StoryResponse)

        fun onError(code: Int, error: String)

        fun onFailure(t: Throwable)
    }
}