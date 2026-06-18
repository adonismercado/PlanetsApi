package edu.ucne.planetsapi.presentation.list

interface ListPlanetUiEvent {
    data class UpdateFilters(
        val name: String,
        val isDestroyed: Boolean?
    ): ListPlanetUiEvent

    data object Search: ListPlanetUiEvent
}