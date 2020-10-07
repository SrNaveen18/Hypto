package com.example.hypto.ui.landing

import com.example.hypto.helper.ApisResponse
import com.example.hypto.model.Questions
import com.example.hypto.webservice.ApiStories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LandingRepository(private val apiStories: ApiStories) {

    suspend fun getQuestions(): ApisResponse<Questions> {
        return withContext(Dispatchers.IO) {
            try {
                val callApi = apiStories.getQuestions()
                ApisResponse.Success(callApi)
            } catch (e: Exception) {
                ApisResponse.Error(e)
            }
        }
    }
}