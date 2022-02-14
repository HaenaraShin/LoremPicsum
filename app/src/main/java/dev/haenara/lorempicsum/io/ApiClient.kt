package dev.haenara.lorempicsum.io

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.haenara.lorempicsum.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object ApiClient {
    private const val BASE_URL = "https://picsum.photos/"

    val api: LoremPicsumApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
            .create(LoremPicsumApi::class.java)
    }

    private val loggingInterceptor: Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}
