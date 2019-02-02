package com.cenock.reigntest.hnmobiletest.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Story(
    @SerializedName("story_id") var id: Long,
    @SerializedName("story_title") var storyTitle: String,
    @SerializedName("title") var title: String,
    @SerializedName("story_url") var url: String,
    @SerializedName("author") var author: String,
    @SerializedName("created_at_i") var createdAtI: Long,
    @SerializedName("created_at") var createdAt: Date,
    var ignored: Boolean
)