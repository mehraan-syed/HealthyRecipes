package com.uog.healthyrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uog.healthyrecipes.data.RecipeData
import com.uog.healthyrecipes.ui.theme.HealthyRecipesTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.navArgument




class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthyRecipesTheme {
                val navController = rememberNavController()
                val recipeViewModel: RecipeViewModel = viewModel(factory = ViewModelProvider.AndroidViewModelFactory(application))
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                androidx.compose.foundation.layout.Column {
                                    Text(text = stringResource(id = R.string.app_name))
                                    Text(
                                        text = "A start to a healthy life.",
                                        style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                                    )
                                }
                            },
                                actions = {
                                    TextButton(onClick = { navController.navigate("favourites") }) {
                                        Text("Favourites (${recipeViewModel.uiState.favouriteIds.size})")
                                    }
                                    TextButton(onClick = {navController.navigate("help")}){
                                        Text("Help")
                                }
                            }
                        )
                    }) { paddingValues ->
                    RecipeNavHost(
                        navController = navController,
                        paddingValues = paddingValues,
                        recipeViewModel = recipeViewModel
                    )
                }
            }
        }
    }
}



@Composable
fun RecipeNavHost(navController : NavHostController,
                  paddingValues: PaddingValues,
                  recipeViewModel: RecipeViewModel
    ) {


    NavHost(
        navController = navController,
        startDestination = "recipe_list",
        Modifier.padding(paddingValues)
    ) {
        composable("recipe_list") {
            RecipeListScreen(navController = navController)
        }
        composable("favourites") {
            FavouritesScreen(
                navController = navController,
                favouriteRecipeIds = recipeViewModel.uiState.favouriteIds.toList()
            )
        }
        composable("help") { HelpScreen() }

        composable(
            route = "recipe/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            val recipe = RecipeData.recipes.firstOrNull { it.id == id }

            if (recipe == null) {
                NotFoundScreen() // we’ll create this file next
            } else {
                RecipeScreen(
                    recipe = recipe,
                    isFavourite = recipeViewModel.uiState.favouriteIds.contains(recipe.id),
                    onToggleFavourite = { recipeViewModel.toggleFavourite(recipe.id) }
                )
            }
        }
    }
}
