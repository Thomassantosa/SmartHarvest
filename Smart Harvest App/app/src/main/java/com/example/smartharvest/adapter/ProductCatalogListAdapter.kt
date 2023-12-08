package com.example.smartharvest.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartharvest.data.responses.ProductcatalogItem
import com.example.smartharvest.databinding.ProductCatalogItemBinding
import com.example.smartharvest.ui.detailproductcatalog.ProductCatalogDetailActivity

class ProductCatalogListAdapter(private val listStory: List<ProductcatalogItem>) : RecyclerView.Adapter<ProductCatalogListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCatalogListAdapter.ListViewHolder {
        return ListViewHolder(ProductCatalogItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ProductCatalogListAdapter.ListViewHolder, position: Int) {
        holder.bind(listStory[position])
    }

    override fun getItemCount(): Int = listStory.size

    class ListViewHolder(binding: ProductCatalogItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var imgPhoto: ImageView = binding.productCatalogImg
        private var tvName: TextView = binding.productCatalogName
        private var tvCategory: TextView = binding.productCatalogCategory
        private var tvNationalPrice: TextView = binding.nationalPrice
        private var tvPredictionPrice: TextView = binding.predictionPrice

        fun bind(productCatalogItem: ProductcatalogItem) {

            Glide.with(itemView.context)
                .load(productCatalogItem.photoUrl)
                .into(imgPhoto)
            tvName.text = productCatalogItem.name
            tvCategory.text = productCatalogItem.category
            tvNationalPrice.text = productCatalogItem.nationalPrice.toString()
            tvPredictionPrice.text = productCatalogItem.predictionPrice.toString()

            itemView.setOnClickListener {

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(imgPhoto, "photo"),
                        Pair(tvName, "name"),
                        Pair(tvCategory, "category"),
                        Pair(tvNationalPrice, "nationalPrice"),
                        Pair(tvPredictionPrice, "predictionPrice")
                    )

                val intent = Intent(itemView.context, ProductCatalogDetailActivity::class.java)
                intent.putExtra("ProductCatalog", productCatalogItem)
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }
}