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
            val filterName = current.nameFilter.trim()

            _state.update {
                it.copy(isLoading = true)
            }

            val result = getPlanetsUseCase(
                name = null,
                isDestroyed = current.isDestroyedFilter
            )

            when (result) {
                is Resource.Success -> {
                    val planets = result.data.orEmpty()

                    val filtered = if (filterName.isBlank()) {
                        planets
                    } else {
                        planets.filter { planet ->
                            planet.name.contains(filterName, ignoreCase = true)
                        }
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            planets = filtered,
                            error = null
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }
}