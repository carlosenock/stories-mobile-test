package com.cenock.reigntest.hnmobiletest.injection

import com.cenock.reigntest.hnmobiletest.data.StoryRepository
import com.cenock.reigntest.hnmobiletest.data.remote.network.NetworkClient
import com.cenock.reigntest.hnmobiletest.data.remote.StoryRemoteDataSource

object Injection {

    private fun provideClientApi(): NetworkClient {
        return NetworkClient.getInstance()
    }

    fun provideMoviesRepository(): StoryRepository {
        return StoryRepository(
            StoryRemoteDataSource(provideClientApi())
        )
    }

}