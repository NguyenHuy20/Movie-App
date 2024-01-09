package com.example.movieapp.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.details.presentaion.DetailsScreen
import com.example.movieapp.movieList.presentation.MovieListViewModel
import com.example.movieapp.movieList.presentation.PopularMoviesScreen
import com.example.movieapp.movieList.presentation.UpComingMovieScreen
import com.example.movieapp.movieList.util.Screen
import com.example.movieapp.ui.theme.MainColor
import com.example.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                SetBarColor(color = MainColor)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainColor
                ) {
                    val movieListViewModel = hiltViewModel<MovieListViewModel>()
                    val movieListState = movieListViewModel.movieListState.collectAsState().value
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.rout
                    ) {
                        composable(Screen.Main.rout) {
                            MainScreen(navController, movieListViewModel)
                        }
                        composable(Screen.UpcomingMovieList.rout) {
                            UpComingMovieScreen(
                                navController = navController,
                                movieListState = movieListState,
                                onEvent = movieListViewModel::onEvent
                            )
                        }
                        composable(Screen.PopularMovieList.rout) {
                            PopularMoviesScreen(
                                navController = navController,
                                movieListState = movieListState,
                                onEvent = movieListViewModel::onEvent
                            )
                        }
                        composable(
                            Screen.Details.rout + "/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            DetailsScreen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}

