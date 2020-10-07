package com.example.hypto.ui.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.hypto.base.BaseViewModel
import com.example.hypto.helper.ApisResponse
import com.example.hypto.model.Questions

class LandingViewModel(private val landingRepository: LandingRepository) : BaseViewModel() {

    fun getQuestions(): LiveData<ApisResponse<Questions>> = liveData {
        emit(ApisResponse.LOADING)
        emit(landingRepository.getQuestions())
        emit(ApisResponse.COMPLETED)
    }
}