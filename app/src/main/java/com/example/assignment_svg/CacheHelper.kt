package com.example.assignment_svg

import android.content.Context
import android.content.SharedPreferences

class CacheHelper(context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("dog_images_cache", Context.MODE_PRIVATE)

    // Load cached images from SharedPreferences
    fun loadImages(): List<String> {
        val cachedImages = sharedPrefs.getString("image_cache", "")
        return cachedImages?.split(",") ?: listOf()
    }

    // Save images to SharedPreferences
    fun saveImages(dogImages: List<String>) {
        val editor = sharedPrefs.edit()
        editor.putString("image_cache", dogImages.joinToString(","))
        editor.apply()
    }

    // Clear the cache
    fun clearCache() {
        val editor = sharedPrefs.edit()
        editor.remove("image_cache")
        editor.apply()
    }
}
