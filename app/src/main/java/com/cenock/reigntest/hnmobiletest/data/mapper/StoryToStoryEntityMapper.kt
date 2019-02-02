package com.cenock.reigntest.hnmobiletest.data.mapper

import com.cenock.reigntest.hnmobiletest.model.Story
import com.cenock.reigntest.hnmobiletest.model.StoryEntity

class StoryToStoryEntityMapper {
    companion object {
        fun mapToEntity(input: Story): StoryEntity {
            return StoryEntity(input.id, input.storyTitle, input.title, input.url, input.author, input.createdAtI)
        }

        fun map(input: StoryEntity): Story {
            return Story(input.id, input.storyTitle, input.title, input.url, input.author, input.createdAtI)
        }

        fun mapAll(inputs: Set<StoryEntity>): Set<Story> {
            var stories: MutableSet<Story> = mutableSetOf()
            inputs.forEach { stories.plus(it) }
            return stories
        }
    }
}