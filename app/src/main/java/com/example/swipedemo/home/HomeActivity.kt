package com.example.swipedemo.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swipedemo.R
import com.example.swipedemo.databinding.ActivityHomeBinding
import com.example.swipedemo.product_listing.ProductListingFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setProductListingFragment()
    }

    private fun setProductListingFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            binding.flRoot.id,
            ProductListingFragment.newInstance(),
            ProductListingFragment.TAG2
        )
        transaction.commit()
    }
}