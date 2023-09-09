package com.vladan.diplomski.model.responses

import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.Article

@JsonClass(generateAdapter = true)
data class ArticlesResponse(
    val data: List<Article>
)
