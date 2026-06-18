package edu.ucne.planetsapi.domain.repository

import edu.ucne.planetsapi.data.remote.Resource
import edu.ucne.planetsapi.data.remote.dto.PlanetDto

interface PlanetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Resource<List<PlanetDto>>

    suspend fun getPlanetDetail(id: Int): Resource<PlanetDto>
}