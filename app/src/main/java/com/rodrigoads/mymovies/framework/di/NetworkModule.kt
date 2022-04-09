package com.rodrigoads.mymovies.framework.di

import com.rodrigoads.mymovies.BuildConfig
import com.rodrigoads.mymovies.data.network.TmdbService
import com.rodrigoads.mymovies.framework.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideRetrofit(
        provideGsonConverterFactory: GsonConverterFactory,
        provideOkHttpClient: OkHttpClient
    ) : TmdbService{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient)
            .addConverterFactory(provideGsonConverterFactory)
            .build()
            .create(TmdbService::class.java)
    }

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(
        provideAuthorizationInterceptor: AuthorizationInterceptor,
        provideLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(provideLoggingInterceptor)
            .addInterceptor(provideAuthorizationInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideAuthorizationInterceptor() : AuthorizationInterceptor {
        return AuthorizationInterceptor(
            apiKey = BuildConfig.API_KEY,
            apiToken = BuildConfig.API_TOKEN,
            responseLanguage = BuildConfig.LANGUAGE,
            responseRegion = BuildConfig.REGION
        )
    }

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor.Level.BODY
                }else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

}