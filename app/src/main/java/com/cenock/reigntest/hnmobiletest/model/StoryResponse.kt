package com.cenock.reigntest.hnmobiletest.model

import com.google.gson.annotations.SerializedName

class StoryResponse (@SerializedName("hits")
                          var stories: List<Story>)