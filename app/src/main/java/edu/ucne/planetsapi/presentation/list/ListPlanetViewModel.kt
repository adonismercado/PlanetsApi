package edu.ucne.planetsapi.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetsapi.data.remote.Resource
import edu.ucne.planetsapi.domain.usecase.GetPlanetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ListPlanetUiState())
    val state = _state.asStateFlow()

    init {
        onLoad()
    }

    fun onEvent(event: ListPlanetUiEvent) {
        when (event) {
            is ListPlanetUiEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        nameFilter = event.name,
                        isDestroyedFilter = event.isDestroyed
                    )
                }
            }

            ListPlanetUiEvent.Search -> onLoad()
        }
    }

    fun onLoad() {
        viewModelScope.launch {
            val current = _state.value

            getPlanetsUseCase(
                name = current.nameFilter.takeIf { it.isNotBlank() }
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data.orEmpty(),
                                error = null
                            )
                        }
                    }

                    is Resource.Error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                }
            }
        }
    }
}