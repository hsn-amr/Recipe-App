package com.example.recipeapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    lateinit var adapter: RVRecipe
    lateinit var rvMain: RecyclerView
    var recipes = ArrayList<RecipeInfo>()

    lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rvMain = findViewById(R.id.rvMain)

        adapter = RVRecipe(recipes)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        showProgressDialog()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<RecipeInfo>> =apiInterface!!.getRecipes()

        call.enqueue(object : Callback<List<RecipeInfo>>{
            override fun onResponse(
                call: Call<List<RecipeInfo>>,
                response: Response<List<RecipeInfo>>
            ) {
                val response: List<RecipeInfo>? = response.body()
                for (recipe in response!!){
                    recipes.add(recipe)
                }
                rvMain.adapter!!.notifyDataSetChanged()
                removeProgressDialog()
            }

            override fun onFailure(call: Call<List<RecipeInfo>>, t: Throwable) {
                Toast.makeText(this@MainActivity2, "Something went wrong $t", Toast.LENGTH_LONG).show()
                removeProgressDialog()
            }

        })
    }

    private fun showProgressDialog(){
        progress = ProgressDialog(this@MainActivity2)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.show()
    }
    private fun removeProgressDialog(){
        progress.dismiss()
    }
}