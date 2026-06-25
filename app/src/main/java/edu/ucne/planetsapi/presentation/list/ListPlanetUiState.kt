package edu.ucne.planetsapi.presentation.list

import edu.ucne.planetsapi.data.remote.dto.PlanetDto
import edu.ucne.planetsapi.domain.model.Planet

data class ListPlanetUiState (
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null
)