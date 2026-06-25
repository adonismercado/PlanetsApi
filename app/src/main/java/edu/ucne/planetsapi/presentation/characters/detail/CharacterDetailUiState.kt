package edu.ucne.planetsapi.presentation.characters.detail

import edu.ucne.planetsapi.domain.model.Character

data class CharacterDetailUiState (
    val isLoading: Boolean = false,
    val characters: Character? = null,
    val error: String? = null,
)