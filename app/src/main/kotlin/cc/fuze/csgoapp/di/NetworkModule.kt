package cc.fuze.csgoapp.di

import cc.fuze.csgoapp.BuildConfig
import cc.fuze.csgoapp.data.remote.PandaScoreService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API = "https://api.pandascore.co"

const val AUTH_INTERCEPTOR = "AUTH_INTERCEPTOR"

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named(AUTH_INTERCEPTOR)))
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single(named(AUTH_INTERCEPTOR)) {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", BuildConfig.PANDASCORE_TOKEN)
            chain.proceed(request.build())
        }
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BODY else NONE
        }
    }

    single {
        get<Retrofit>().create(PandaScoreService::class.java)
    }
}