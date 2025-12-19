package com.uog.healthyrecipes

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.uog.healthyrecipes.data.RecipeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    var uiState by mutableStateOf(RecipeUiState(recipes = RecipeData.recipes))
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val loaded = FavouritesStorage.load(getApplication())
            uiState = uiState.copy(favouriteIds = loaded)
        }
    }

    fun toggleFavourite(recipeId: Int) {
        val current = uiState.favouriteIds.toMutableSet()
        if (current.contains(recipeId)) current.remove(recipeId) else current.add(recipeId)

        uiState = uiState.copy(favouriteIds = current)


        viewModelScope.launch(Dispatchers.IO) {
            FavouritesStorage.save(getApplication(), current.toList())
        }
    }
}
