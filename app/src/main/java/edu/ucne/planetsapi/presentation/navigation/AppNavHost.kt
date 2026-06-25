package edu.ucne.planetsapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.planetsapi.presentation.characters.detail.CharacterDetailScreen
import edu.ucne.planetsapi.presentation.characters.detail.CharacterDetailViewModel
import edu.ucne.planetsapi.presentation.characters.list.CharacterListScreen
import edu.ucne.planetsapi.presentation.planets.detail.DetailPlanetScreen
import edu.ucne.planetsapi.presentation.planets.detail.DetailPlanetViewModel
import edu.ucne.planetsapi.presentation.planets.list.ListPlanetScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            ListPlanetScreen(
                onPlanetClick = { planetId ->
                    navHostController.navigate(Screen.PlanetDetail(planetId))
                }
            )
        }

        composable<Screen.PlanetDetail> {
            val viewModel: DetailPlanetViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DetailPlanetScreen(
                state = state,
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.PlanetList> {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navHostController.navigate(Screen.CharacterDetail(characterId))
                }
            )
        }

        composable<Screen.CharacterDetail> {
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            CharacterDetailScreen(
                state = state,
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}