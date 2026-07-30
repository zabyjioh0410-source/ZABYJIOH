package com.example.ecochoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EcoProductsAdapter(
    private var products: List<EcoProduct>
) : RecyclerView.Adapter<EcoProductsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val ecoScore: TextView = itemView.findViewById(R.id.ecoScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        holder.productImage.setImageResource(product.imageResId)
        holder.ecoScore.text = "Эко-рейтинг: ${product.ecoScore}%"
        
        // Цветовая индикация рейтинга
        val scoreColor = when {
            product.ecoScore >= 90 -> android.graphics.Color.parseColor("#4CAF50") // Зеленый
            product.ecoScore >= 75 -> android.graphics.Color.parseColor("#FFC107") // Желтый
            else -> android.graphics.Color.parseColor("#F44336") // Красный
        }
        holder.ecoScore.setTextColor(scoreColor)
    }

    override fun getItemCount(): Int = products.size

    fun updateList(newProducts: List<EcoProduct>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
