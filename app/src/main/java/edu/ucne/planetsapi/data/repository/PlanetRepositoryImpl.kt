package edu.ucne.planetsapi.data.repository

import edu.ucne.planetsapi.data.remote.DragonBallApi
import edu.ucne.planetsapi.data.remote.Resource
import edu.ucne.planetsapi.data.remote.dto.PlanetDto
import edu.ucne.planetsapi.data.remote.remotedatasource.PlanetRemoteDataSource
import edu.ucne.planetsapi.domain.model.Planet
import edu.ucne.planetsapi.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetRemoteDataSource
) : PlanetRepository {
    override fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planet>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getPlanets(page,limit,name,isDestroyed)
            .onSuccess { dto ->
                emit(Resource.Success(dto.items.map { it.toDomain() }))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido."))
            }
    }

    override fun getPlanetDetail(id: Int): Flow<Resource<Planet>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getPlanetDetail(id)
            .onSuccess { dto ->
                emit(Resource.Success(dto.toDomain()))
            }.onFailure {
                emit(Resource.Error(it.message ?: "Error desconocido."))
            }
    }
}