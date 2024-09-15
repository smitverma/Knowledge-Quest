package com.sevenbitsinabyte.knowledgequest

data class Question(
    val answer: String,
    val options: List<String>,
    val question: String
)