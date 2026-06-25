package edu.ucne.planetsapi.domain.usecase

import edu.ucne.planetsapi.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int) = repository.getPlanetDetail(id)
}