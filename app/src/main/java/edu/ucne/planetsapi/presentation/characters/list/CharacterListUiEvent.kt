package edu.ucne.planetsapi.presentation.characters.list

interface CharacterListUiEvent {
    data class UpdateFilters(
        val name: String,
        val gender: String,
        val race: String,
    ): CharacterListUiEvent

    data object Search: CharacterListUiEvent
}