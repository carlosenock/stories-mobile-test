package com.cenock.reigntest.hnmobiletest.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

@Entity(tableName = "story")
class StoryEntity (
    @ColumnInfo(name="story_id") var id: Long,
    @ColumnInfo(name="story_title") var storyTitle: String,
    @ColumnInfo(name="title") var title: String,
    @ColumnInfo(name="story_url") var url: String,
    @ColumnInfo(name="author") var author: String,
    @ColumnInfo(name="created_at_i") var createdAtI: Long
)