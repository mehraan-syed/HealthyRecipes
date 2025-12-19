package com.uog.healthyrecipes

import android.content.Context

object FavouritesStorage {

    private const val FILE_NAME = "favourites.txt"

    fun load(context: Context): Set<Int> {
        return runCatching {
            val text = context.openFileInput(FILE_NAME).bufferedReader().use { it.readText() }
            if (text.isBlank()) emptySet()
            else text.split(",")
                .mapNotNull { it.trim().toIntOrNull() }
                .toSet()
        }.getOrElse {
            emptySet()
        }
    }

    fun save(context: Context, favouriteIds: List<Int>) {
        runCatching {
            val text = favouriteIds.joinToString(",")
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
                it.write(text)
            }
        }
    }
}
