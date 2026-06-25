package edu.ucne.planetsapi.presentation.detail

import edu.ucne.planetsapi.data.remote.dto.PlanetDto
import edu.ucne.planetsapi.domain.model.Planet

data class DetailPlanetUiState (
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)