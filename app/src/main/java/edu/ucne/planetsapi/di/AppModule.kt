package edu.ucne.planetsapi.di

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.planetsapi.data.remote.DragonBallApi
import edu.ucne.planetsapi.data.remote.remotedatasource.CharacterRemoteDataSource
import edu.ucne.planetsapi.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.planetsapi.data.repository.CharacterRepositoryImpl
import edu.ucne.planetsapi.data.repository.PlanetRepositoryImpl
import edu.ucne.planetsapi.domain.repository.CharacterRepository
import edu.ucne.planetsapi.domain.repository.PlanetRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi {
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: PlanetRemoteDataSource): PlanetRepository {
        return PlanetRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: DragonBallApi): PlanetRemoteDataSource {
        return PlanetRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(remoteDataSource: CharacterRemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(api: DragonBallApi): CharacterRemoteDataSource {
        return CharacterRemoteDataSource(api)
    }
}