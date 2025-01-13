package com.example.imagelist.domin.di

import com.example.imagelist.domin.ImageDataSource
import com.example.imagelist.domin.ImageRepository
import com.example.imagelist.domin.api.AuthInterceptor
import com.example.imagelist.domin.api.ImageListApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val repositoryModule = module {
    single<ImageRepository> {
        ImageDataSource(imageListApi = get())
    }
}

val networkModule = module {
    factory { providesAuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}

fun providesAuthInterceptor(): AuthInterceptor = AuthInterceptor()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val contentType = "application/json".toMediaType()
    val retrofitJson = Json { ignoreUnknownKeys = true }
    return Retrofit.Builder().baseUrl("https://api.pexels.com/v1/").client(okHttpClient)
        .addConverterFactory(retrofitJson.asConverterFactory(contentType))
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor()
            .apply { this.setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()
}

fun provideForecastApi(retrofit: Retrofit): ImageListApi = retrofit.create(ImageListApi::class.java)
