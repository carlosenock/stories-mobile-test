package com.cenock.reigntest.hnmobiletest.data

import com.cenock.reigntest.hnmobiletest.model.StoryResponse

class StoryRepository(private val dataSource: StoryDataSource) : StoryDataSource {

    override fun getStories(callback: StoryDataSource.StoryCallback) {
        dataSource.getStories(object : StoryDataSource.StoryCallback {
            override fun onSuccess(stories: StoryResponse) {
                callback.onSuccess(stories)
            }

            override fun onError(code: Int, error: String) {
                callback.onError(code, error)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}