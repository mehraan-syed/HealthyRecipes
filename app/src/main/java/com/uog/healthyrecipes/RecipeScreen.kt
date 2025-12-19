package com.uog.healthyrecipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uog.healthyrecipes.data.*
import com.uog.healthyrecipes.ui.theme.HealthyRecipesTheme
import androidx.compose.material3.Button
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext

/**
 * Displays detailed information about a recipe.
 * Allows the user to add or remove the recipe from favourites.
 */

@Composable
fun RecipeScreen(recipe: Recipe, isFavourite: Boolean, onToggleFavourite: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = recipe.imageResId),
            contentDescription = recipe.title,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = recipe.title, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onToggleFavourite,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isFavourite) "★ Remove from favourites" else "☆ Add to favourites"
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = recipe.description, style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Ingredients", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        recipe.ingredients.forEach { ingredient ->
            Text(text = "• $ingredient", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRecipeScreen(){
    HealthyRecipesTheme {
        RecipeScreen(recipe = RecipeData.recipes[0],
            isFavourite = false,
            onToggleFavourite = {})
    }
}
