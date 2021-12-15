package com.mayco.desafiospaceflight.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mayco.desafiospaceflight.network.ApiService
import com.mayco.desafiospaceflight.repository.NewsRepository
import com.mayco.desafiospaceflight.ui.dialog.DialogDetailsViewModel
import com.mayco.desafiospaceflight.ui.fragment.FragmentViewModel
import com.mayco.desafiospaceflight.ui.home.HomeViewModel
import com.mayco.desafiospaceflight.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val applicationModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttp(get()) }
    single { provideHttpLoggingInterceptor() }

}


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FragmentViewModel(get()) }
    viewModel { DialogDetailsViewModel(get()) }
}


val repositoruModule = module {
    factory { NewsRepository(get()) }
}


private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


private fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.apply {
        addInterceptor(httpLoggingInterceptor)
    }
    return okHttpClient.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create((ApiService::class.java))
}