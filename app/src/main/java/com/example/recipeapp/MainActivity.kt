package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recipeTitle: EditText
    lateinit var recipeAuthor: EditText
    lateinit var recipeIngredients: EditText
    lateinit var recipeInstructions: EditText

    lateinit var saveButton: Button
    lateinit var viewButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        recipeTitle = findViewById(R.id.etTitle)
        recipeAuthor = findViewById(R.id.etAuthor)
        recipeIngredients = findViewById(R.id.etIngredients)
        recipeInstructions = findViewById(R.id.etInstructions)

        saveButton = findViewById(R.id.btnSave)
        viewButton = findViewById(R.id.btnView)

        saveButton.setOnClickListener {
            if(recipeTitle.text.isNotEmpty() && recipeAuthor.text.isNotEmpty()
                && recipeIngredients.text.isNotEmpty() && recipeInstructions.text.isNotEmpty()){
                val title = recipeTitle.text.toString()
                val author = recipeAuthor.text.toString()
                val ingredients = recipeIngredients.text.toString()
                val instructions = recipeInstructions.text.toString()

                val recipe = Recipe(title, author, ingredients, instructions)
                addNewRecipe(apiInterface!!,recipe)
            }else{
                Toast.makeText(this@MainActivity, "Fill in the blanks please", Toast.LENGTH_LONG).show()
            }
        }

        viewButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    fun addNewRecipe(apiInterface: APIInterface, recipe: Recipe){

        apiInterface.addNewRecipe(recipe).enqueue(object : Callback<Recipe>{
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                Toast.makeText(this@MainActivity, "Recipe Saved", Toast.LENGTH_LONG).show()
                recipeTitle.text.clear()
                recipeAuthor.text.clear()
                recipeIngredients.text.clear()
                recipeInstructions.text.clear()
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }
}