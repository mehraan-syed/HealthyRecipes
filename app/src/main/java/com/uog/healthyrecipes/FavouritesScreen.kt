package com.uog.healthyrecipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uog.healthyrecipes.data.RecipeData

@Composable
fun FavouritesScreen(
    navController: NavHostController,
    favouriteRecipeIds: List<Int>
) {
    val favouriteRecipes = RecipeData.recipes.filter { favouriteRecipeIds.contains(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Favourites",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favouriteRecipes.isEmpty()) {
            Text(
                text = "No favourites yet. Star a recipe to add it here.",
                style = MaterialTheme.typography.bodySmall
            )
        } else {
            favouriteRecipes.forEach { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onClick = { navController.navigate("recipe_${recipe.id}") }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
