package com.vladan.diplomski.ui.articles

import com.vladan.diplomski.model.Article
import com.vladan.diplomski.model.mockArticles

data class ArticlesUiState(
    val articles: List<Article> = mockArticles
)
