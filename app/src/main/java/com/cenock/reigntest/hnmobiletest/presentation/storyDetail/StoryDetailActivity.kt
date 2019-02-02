package com.cenock.reigntest.hnmobiletest.presentation.storyDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cenock.reigntest.hnmobiletest.R
import kotlinx.android.synthetic.main.activity_story_detail.*

class StoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)
        iv_back.setOnClickListener { v -> finish() }

        webview.loadUrl(intent.getStringExtra(STORY_URL))

    }

    companion object {
        const val STORY_URL = "HNM_STORY_URL"
    }
}