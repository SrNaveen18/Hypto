package com.example.hypto.di

import com.example.hypto.ui.landing.LandingRepository
import com.example.hypto.ui.landing.LandingViewModel
import com.example.hypto.webservice.ApiStories
import com.example.hypto.webservice.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { LandingViewModel(get()) }
}

val repositoryModule = module {
    factory { LandingRepository(get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient().newBuilder().addNetworkInterceptor(interceptor).build()
}

fun provideApi(retrofit: Retrofit): ApiStories = retrofit.create(ApiStories::class.java)


