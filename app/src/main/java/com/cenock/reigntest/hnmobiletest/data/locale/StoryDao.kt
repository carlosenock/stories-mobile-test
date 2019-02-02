package com.cenock.reigntest.hnmobiletest.data.locale

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.cenock.reigntest.hnmobiletest.model.StoryEntity

@Dao
interface StoryDao {

    @Query("select * from story")
    fun getAllTasks(): Set<StoryEntity>

    @Insert(onConflict = REPLACE)
    fun insert(storyEntity: StoryEntity)

    @Delete
    fun deleteTask(story: StoryEntity)
}