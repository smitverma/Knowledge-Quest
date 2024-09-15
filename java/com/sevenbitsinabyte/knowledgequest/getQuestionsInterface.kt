package com.sevenbitsinabyte.knowledgequest

import retrofit2.Call
import retrofit2.http.GET

interface getQuestionsInterface {

    @GET("posts")
    fun getData(): Call<List<Question>>

}