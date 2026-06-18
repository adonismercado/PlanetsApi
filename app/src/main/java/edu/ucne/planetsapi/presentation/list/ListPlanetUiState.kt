package edu.ucne.planetsapi.presentation.list

import edu.ucne.planetsapi.data.remote.dto.PlanetDto

data class ListPlanetUiState (
    val isLoading: Boolean = false,
    val planets: List<PlanetDto> = emptyList(),
    val error: String? = null,
    val nameFilter: String = "",
    val isDestroyedFilter: Boolean? = null
)