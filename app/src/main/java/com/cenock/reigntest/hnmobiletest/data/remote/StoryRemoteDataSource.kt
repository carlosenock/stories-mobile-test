package com.cenock.reigntest.hnmobiletest.data.remote

import com.cenock.reigntest.hnmobiletest.data.StoryDataSource
import com.cenock.reigntest.hnmobiletest.data.network.Callback
import com.cenock.reigntest.hnmobiletest.data.network.NetworkClient
import com.cenock.reigntest.hnmobiletest.model.StoryResponse

class StoryRemoteDataSource(private var client: NetworkClient) : StoryDataSource {

    override fun getStories(cb: StoryDataSource.StoryCallback) {
        client.getStories(callBack(cb))
    }

    private fun callBack(cb: StoryDataSource.StoryCallback) : Callback<StoryResponse> {
        return object : Callback<StoryResponse>() {

            override fun onSuccess(response: StoryResponse?) {
                cb.onSuccess(response!!)
            }

            override fun onError(code: Int, error: String?) {
                cb.onError(code, error!!)
            }

            override fun onFailure(t: Throwable?) {
                cb.onFailure(t!!)
            }

        }
    }
}