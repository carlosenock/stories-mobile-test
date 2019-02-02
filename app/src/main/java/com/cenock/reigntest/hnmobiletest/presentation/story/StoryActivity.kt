package com.cenock.reigntest.hnmobiletest.presentation.story

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.cenock.reigntest.hnmobiletest.R
import com.cenock.reigntest.hnmobiletest.injection.Injection
import com.cenock.reigntest.hnmobiletest.model.Story
import com.cenock.reigntest.hnmobiletest.presentation.storyDetail.StoryDetailActivity
import com.cenock.reigntest.hnmobiletest.presentation.storyDetail.StoryDetailActivity.Companion.STORY_URL
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity(), StoryContract.View {

    override lateinit var presenter: StoryContract.Presenter
    private var mMovieAdapter: StoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        StoryPresenter(Injection.provideMoviesRepository(), this)

        presenter.start()
    }

    override fun loadStories(stories: List<Story>) {
        if (stories.isNotEmpty()) {
            if (mMovieAdapter == null) {
                mMovieAdapter = StoryAdapter(stories, this) { movie: Story ->
                    presenter.onStoryClick(movie)
                }
                recyclerView.adapter = mMovieAdapter
                recyclerView.layoutManager = GridLayoutManager(this, 1)
                recyclerView.setHasFixedSize(true)
                recyclerView.itemAnimator = DefaultItemAnimator()
            } else {
                mMovieAdapter?.updateAdapter(stories)
            }

        }
    }

    override fun goToStorieseDetail(story: Story) {
        if (!story.url.isNullOrEmpty()) {
            val intent = Intent(this, StoryDetailActivity::class.java)
            intent.putExtra(STORY_URL, story.url)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            startActivity(intent)
        }else{
            Toast.makeText(
                this,
                getString(R.string.url_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
