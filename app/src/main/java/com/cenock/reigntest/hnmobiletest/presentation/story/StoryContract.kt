package com.cenock.reigntest.hnmobiletest.presentation.story

import com.cenock.reigntest.core.presentation.BasePresenter
import com.cenock.reigntest.core.presentation.BaseView
import com.cenock.reigntest.hnmobiletest.model.Story

interface StoryContract {

    interface View : BaseView<Presenter> {
        fun loadStories(stories: List<Story>)
        fun goToStorieseDetail(story: Story)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter {
        fun getStories()
        fun onStoryClick(story: Story)
    }
}