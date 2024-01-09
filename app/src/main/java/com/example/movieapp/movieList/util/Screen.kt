package com.example.movieapp.movieList.util

sealed class Screen(val rout: String) {
    object Main : Screen("main")
    object PopularMovieList : Screen("popularMovie")
    object UpcomingMovieList : Screen("upcomingMovie")
    object Details : Screen("details")
    object Home : Screen("home")
}