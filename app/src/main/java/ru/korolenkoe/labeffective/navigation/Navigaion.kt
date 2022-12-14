package ru.korolenkoe.labeffective.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ru.korolenkoe.labeffective.screens.heroscreen.HeroScreen
import ru.korolenkoe.labeffective.screens.heroscreen.ViewModelGetHeroApi
import ru.korolenkoe.labeffective.screens.mainscreen.MainScreen
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi

@Composable
fun Navigation(
    navController: NavHostController,
    viewModelGetHeroesApi: ViewModelGetHeroesApi,
    viewModelGetHeroApi: ViewModelGetHeroApi,
    characterDBViewModel: CharacterDBViewModel
) {
    val uri = "https://heroapp.com"
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModelGetHeroesApi, characterDBViewModel)
        }
        composable(
            "hero?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
        ) { entry ->
            HeroScreen(
                navController,
                entry.arguments?.getInt("id")!!,
                viewModelGetHeroApi,
                characterDBViewModel
            )
        }
        composable(
            route = Screen.HeroScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
            )
        ) { entry ->
            HeroScreen(
                navController = navController,
                entry.arguments?.get("id") as Int,
                viewModelGetHeroApi,
                characterDBViewModel
            )
        }
    }
}


