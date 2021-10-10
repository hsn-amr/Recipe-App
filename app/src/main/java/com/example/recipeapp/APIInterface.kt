package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("/recipes/")
    fun getRecipes(): Call<List<RecipeInfo>>

    @POST("/recipes/")
    fun addNewRecipe(@Body recipeData: Recipe): Call<Recipe>
}