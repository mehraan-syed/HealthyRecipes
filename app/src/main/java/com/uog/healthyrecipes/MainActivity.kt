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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uog.healthyrecipes.data.RecipeData
import com.uog.healthyrecipes.ui.theme.HealthyRecipesTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthyRecipesTheme {
                val navController = rememberNavController()
                val favouriteRecipeIds = remember { mutableStateListOf<Int>() }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_name)
                                )
                            }
                        )
                    }) { paddingValues ->
                    RecipeNavHost(
                        navController = navController,
                        paddingValues = paddingValues,
                        favouriteRecipeIds = favouriteRecipeIds
                    )
                }
            }
        }
    }
}



@Composable
fun RecipeNavHost(navController : NavHostController,
                  paddingValues: PaddingValues,
                  favouriteRecipeIds: MutableList<Int>
    )
{
    NavHost(
        navController = navController,
        startDestination = "recipe_list",
        Modifier.padding(paddingValues)) {
        composable("recipe_list"){
            RecipeListScreen(navController = navController)}
        composable("favourites"){
            FavouritesScreen(
                navController = navController,
                favouriteRecipeIds = favouriteRecipeIds
            )
        }
        composable("recipe_1"){
            RecipeScreen( RecipeData.recipes[0],
                favouriteRecipeIds = favouriteRecipeIds)}
        composable("recipe_2"){
            RecipeScreen(RecipeData.recipes[1],
                favouriteRecipeIds = favouriteRecipeIds)}
        composable("recipe_3"){
            RecipeScreen(RecipeData.recipes[2],
                favouriteRecipeIds = favouriteRecipeIds)}
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeNavHostPreview() {
    HealthyRecipesTheme {
        val navController = rememberNavController()
        RecipeNavHost(navController = navController,
            paddingValues = PaddingValues(),
            favouriteRecipeIds = mutableListOf())
    }
}
