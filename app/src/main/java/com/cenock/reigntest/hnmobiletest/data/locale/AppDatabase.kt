package com.cenock.reigntest.hnmobiletest.data.locale

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cenock.reigntest.hnmobiletest.model.StoryEntity

@Database(entities = arrayOf(StoryEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

abstract fun storyDao(): StoryDao
}