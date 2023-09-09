package com.vladan.diplomski.model.requests

import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.Article

@JsonClass(generateAdapter = true)
data class AddArticleRequest(
    val article: Article
)