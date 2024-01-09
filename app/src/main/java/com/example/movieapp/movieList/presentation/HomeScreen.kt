package com.example.movieapp.movieList.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.movieList.presentation.componentes.MovieItem
import com.example.movieapp.movieList.util.Screen
import com.example.movieapp.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUIEvent) -> Unit

) {
    var searchTxt by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainColor),
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            item {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .border(border = BorderStroke(1.dp, Color.White), shape = CircleShape)
                        .clip(RoundedCornerShape(25.dp)),
                    value = searchTxt,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.White,
                        disabledLabelColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = { newValue -> searchTxt = newValue },
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    placeholder = { Text(text = "Search", style = TextStyle(color = Color.White)) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 15.dp),
                        text = "Popular",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 15.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.PopularMovieList.rout
                                )
                            },
                        text = "See more",
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    )
                }


                if (movieListState.popularMovieList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
                    ) {
                        items(movieListState.popularMovieList.size) { index ->
                            MovieItem(
                                movie = movieListState.popularMovieList[index],
                                navHostController = navController
                            )
//                        if (index >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
//                            onEvent(MovieListUIEvent.Paginate(com.example.movieapp.movieList.util.Category.POPULAR))
//                        }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 15.dp),
                        text = "Up Coming",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 15.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.UpcomingMovieList.rout
                                )
                            },
                        text = "See more",
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    )
                }
                if (movieListState.upcomingMovieList.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
                    ) {
                        items(movieListState.upcomingMovieList.size) { index ->
                            MovieItem(
                                movie = movieListState.upcomingMovieList[index],
                                navHostController = navController
                            )
//                        if (index >= movieListState.upcomingMovieList.size - 1 && !movieListState.isLoading) {
//                            onEvent(MovieListUIEvent.Paginate(com.example.movieapp.movieList.util.Category.UPCOMING))
//                        }
                        }
                    }
                }
            }

        }

    }
}