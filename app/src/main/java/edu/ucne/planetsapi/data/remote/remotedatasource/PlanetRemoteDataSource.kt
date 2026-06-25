package edu.ucne.planetsapi.data.remote.remotedatasource

import edu.ucne.planetsapi.data.remote.DragonBallApi
import edu.ucne.planetsapi.data.remote.dto.PlanetDto
import edu.ucne.planetsapi.data.remote.dto.PlanetResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Result<PlanetResponseDto> {
        return try {
            val response = api.getPlanets(page,limit,name,isDestroyed)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else {
                Result.success(response.body()!!)
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else {
                Result.success(response.body()!!)
            }
        } catch (e: HttpException) {
            Result.failure(Exception("Error de red", e))
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido", e))
        }
    }
}