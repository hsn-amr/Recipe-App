package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVRecipe(private val recipes: ArrayList<RecipeInfo>): RecyclerView.Adapter<RVRecipe.ItemViewHolder>() {
    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.itemView.apply {
            tvTitle.text = recipe.title
            tvAuthor.text = recipe.author
            tvIngredients.text = recipe.ingredients
            tvInstructions.text = recipe.instructions
        }
    }

    override fun getItemCount() = recipes.size
}