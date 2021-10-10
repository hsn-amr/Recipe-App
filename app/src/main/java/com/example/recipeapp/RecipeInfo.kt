package com.example.recipeapp

import com.google.gson.annotations.SerializedName

class RecipeInfo {

    @SerializedName("pk")
    val id: Int? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("author")
    val author: String? = null

    @SerializedName("ingredients")
    val ingredients: String? = null

    @SerializedName("instructions")
    val instructions: String? = null
}