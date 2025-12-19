package com.uog.healthyrecipes

import com.uog.healthyrecipes.data.Recipe

/**
 * Represents the UI state for recipe-related screens.
 * */
data class RecipeUiState(
    val recipes: List<Recipe> = emptyList(),
    val favouriteIds: Set<Int> = emptySet()
)
