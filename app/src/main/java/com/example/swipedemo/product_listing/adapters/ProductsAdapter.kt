package com.example.swipedemo.product_listing.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipedemo.R
import com.example.swipedemo.databinding.RowProductLayoutBinding
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem


internal class ProductsAdapter(
    private var selectedProduct: ArrayList<ProductListingResponseModelItem>,
    private var context: Context,
    private var mOnSelectedListenerCallback: OnProductSelectedListenerCallback
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsItemViewHolder>() {

    inner class ProductsItemViewHolder(private val productBinding: RowProductLayoutBinding) :
        RecyclerView.ViewHolder(productBinding.root) {

        fun bind(productsSource: ProductListingResponseModelItem) {
            productBinding.apply {
                Glide.with(context)
                    .load(productsSource.image)
                    .placeholder(R.drawable.broken_image)
                    .into(ivProductImage)

                tvPrice.text = buildString {
                    append("₹")
                    append(productsSource.price)
                    append("/-")
                }

                tvProductName.text = productsSource.productName
                tvProductType.text = buildString {
                    append("(${productsSource.productType})")
                }
                tvTax.text = buildString {
                    append("${productsSource.tax}% Tax")
                }

                root.setOnClickListener {
                    mOnSelectedListenerCallback.onSelected(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsItemViewHolder {
        val productsBinding = RowProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsItemViewHolder(productsBinding)
    }


    override fun onBindViewHolder(holder: ProductsItemViewHolder, position: Int) {
        val productsItemModel = selectedProduct[position]
        holder.bind(productsItemModel)
    }

    override fun getItemCount(): Int {
        return selectedProduct.size
    }

    fun addItemAtLast(productsModel: ProductListingResponseModelItem) {
        selectedProduct.add(productsModel)
        notifyItemInserted(selectedProduct.size - 1)
    }

    fun clearAndAddData(productItems: ArrayList<ProductListingResponseModelItem>) {
        this.selectedProduct.clear()
        this.selectedProduct.addAll(productItems)
        this.notifyDataSetChanged()
    }

    fun clear(){
        this.selectedProduct.clear()
        this.notifyDataSetChanged()
    }

}

interface OnProductSelectedListenerCallback {
    fun onSelected(position: Int)
}