package com.example.hypto.webservice

import com.example.hypto.model.Questions
import retrofit2.http.GET

const val BASE_URL = "http://www.json-generator.com/api/json/get/"


interface ApiStories {
    @GET("cpOAeecWGa?indent=2")
    suspend fun getQuestions() : Questions
}