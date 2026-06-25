package edu.ucne.planetsapi.domain.repository

import edu.ucne.planetsapi.data.remote.Resource
import edu.ucne.planetsapi.data.remote.dto.PlanetDto
import edu.ucne.planetsapi.domain.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}