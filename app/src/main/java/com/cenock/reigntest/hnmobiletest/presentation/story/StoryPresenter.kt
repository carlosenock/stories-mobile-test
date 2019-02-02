package com.cenock.reigntest.hnmobiletest.presentation.story

import com.cenock.reigntest.hnmobiletest.data.StoryDataSource
import com.cenock.reigntest.hnmobiletest.data.StoryRepository
import com.cenock.reigntest.hnmobiletest.model.Story
import com.cenock.reigntest.hnmobiletest.model.StoryResponse

class StoryPresenter(
    val repository: StoryRepository,
    private val storyView: StoryContract.View
) : StoryContract.Presenter, StoryDataSource.StoryCallback {

    init {
        storyView.presenter = this
    }

    override fun start() {
        getStories()
    }

    override fun onStoryClick(story: Story) {
        storyView.goToStorieseDetail(story)
    }

    override fun getStories() {
        repository.getStories(this)
    }

    override fun onSuccess(stories: StoryResponse) {
        storyView.loadStories(stories.stories)
        storyView.hideLoading()
    }

    override fun onError(code: Int, error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}