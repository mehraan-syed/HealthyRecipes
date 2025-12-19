package com.uog.healthyrecipes

import com.uog.healthyrecipes.data.Recipe

data class RecipeUiState(
    val recipes: List<Recipe> = emptyList(),
    val favouriteIds: Set<Int> = emptySet()
)
