package edu.ucne.planetsapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.planetsapi.presentation.planets.detail.DetailPlanetScreen
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
            DetailPlanetScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}