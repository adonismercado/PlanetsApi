package edu.ucne.planetsapi.presentation.detail

import edu.ucne.planetsapi.data.remote.dto.PlanetDto

data class DetailPlanetUiState (
    val isLoading: Boolean = false,
    val planet: PlanetDto? = null,
    val error: String? = null
)