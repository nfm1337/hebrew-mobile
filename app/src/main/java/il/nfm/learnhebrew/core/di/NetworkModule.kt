package il.nfm.learnhebrew.core.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import il.nfm.learnhebrew.BuildConfig
import il.nfm.learnhebrew.data.network.ApiService
import il.nfm.learnhebrew.data.network.RetrofitClient
import il.nfm.learnhebrew.data.repository.SessionRepositoryImpl
import il.nfm.learnhebrew.domain.repository.SessionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        RetrofitClient.create(
            baseUrl = BuildConfig.BASE_URL,
            debug = BuildConfig.DEBUG
        )
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository
}
