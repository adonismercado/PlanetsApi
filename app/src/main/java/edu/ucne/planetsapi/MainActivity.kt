package edu.ucne.planetsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.planetsapi.presentation.navigation.AppNavHost
import edu.ucne.planetsapi.presentation.navigation.DrawerMenu
import edu.ucne.planetsapi.ui.theme.PlanetsApiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanetsApiTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)

                DrawerMenu(
                    drawerState = drawerState,
                    navHostController = navController
                ) {
                    AppNavHost(navHostController = navController)
                }
            }
        }
    }
}