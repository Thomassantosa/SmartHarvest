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
import com.example.smartharvest.data.responses.DataItem
import com.example.smartharvest.databinding.ProductHistoryItemBinding
import com.example.smartharvest.ui.productitemdetail.ProductItemDetailActivity

class ProductHistoryListAdapter(private val listProductItem: List<DataItem>) : RecyclerView.Adapter<ProductHistoryListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHistoryListAdapter.ListViewHolder {
        return ListViewHolder(
            ProductHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ProductHistoryListAdapter.ListViewHolder, position: Int) {
        holder.bind(listProductItem[position])
    }

    override fun getItemCount(): Int = listProductItem.size

    class ListViewHolder(binding: ProductHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var imgPhoto: ImageView = binding.imgProductHistory
        private var tvProductName: TextView = binding.textProductName
        private var tvCategory: TextView = binding.textCategory
        private var tvStatus: TextView = binding.textStatus
        private var tvProducerName: TextView = binding.textProducerName
        private var tvDistributorName: TextView = binding.textDistributorName
        private var tvSellerName: TextView = binding.textSellerName

        fun bind(productHistoryItem: DataItem) {

            Glide.with(itemView.context)
                .load(productHistoryItem.photoUrl)
                .into(imgPhoto)
            tvProductName.text = productHistoryItem.name
            tvCategory.text = productHistoryItem.category
            tvStatus.text = productHistoryItem.status
            tvProducerName.text = productHistoryItem.producerName
            tvDistributorName.text = productHistoryItem.distributorName
            tvSellerName.text = productHistoryItem.sellerName

            itemView.setOnClickListener {

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair(imgPhoto, "photo"),
                        Pair(tvProductName, "productName"),
                        Pair(tvCategory, "category"),
                        Pair(tvStatus, "status"),
                        Pair(tvProducerName, "ProducerName"),
                        Pair(tvDistributorName, "DistributorName"),
                        Pair(tvSellerName, "SellerName"),
                    )

                val intent = Intent(itemView.context, ProductItemDetailActivity::class.java)
                intent.putExtra("ProductItem", productHistoryItem)
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }
}