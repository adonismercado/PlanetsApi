package edu.ucne.planetsapi.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetsapi.data.remote.Resource
import edu.ucne.planetsapi.domain.usecase.GetPlanetDetailUseCase
import edu.ucne.planetsapi.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlanetViewModel @Inject constructor(
  private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailPlanetUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Screen.PlanetDetail>()
        onLoad(args.id)
    }
    private fun onLoad(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val result = getPlanetDetailUseCase(id)) {
                is Resource.Success ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            planet = result.data
                        )
                    }

                is Resource.Error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                is Resource.Loading ->
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
            }
        }
    }
}