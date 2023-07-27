package com.example.swipedemo.product_listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.swipedemo.R
import com.example.swipedemo.add_product.AddProductFragment
import com.example.swipedemo.databinding.FragmentProductListingBinding
import com.example.swipedemo.product_listing.adapters.OnProductSelectedListenerCallback
import com.example.swipedemo.product_listing.adapters.ProductsAdapter
import com.example.swipedemo.product_listing.models.ProductListingResponseModelItem
import com.example.swipedemo.product_listing.utils.ProductListingViewModel
import com.example.swipedemo.utils.LoadingUtils


class ProductListingFragment : Fragment() {
    private lateinit var binding: FragmentProductListingBinding
    private var productList: ArrayList<ProductListingResponseModelItem> = arrayListOf()
    private lateinit var productsAdapter: ProductsAdapter
    private val productsViewModel: ProductListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProductListingBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pullToRefresh.isRefreshing = true
        productsLiveDataObserver()
        setUpRecyclerView()
        onRefresh()
        setCTA()
    }

    private fun setCTA() {
        binding.tvCtaAddProduct.setOnClickListener {
            LoadingUtils.showDialog(requireContext(), false)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.fl_root,
                AddProductFragment.newInstance(),
                AddProductFragment.TAG2
            )
            transaction.addToBackStack(AddProductFragment.TAG2)
            transaction.commit()
        }
    }

    private fun onRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            productsAdapter.clear()
            productsViewModel.fetchProducts()
        }
    }

    override fun onResume() {
        super.onResume()
        LoadingUtils.showDialog(requireContext(), false)
        productsViewModel.fetchProducts()
    }

    private fun productsLiveDataObserver() {
        productsViewModel.getProductsLiveData().observe(viewLifecycleOwner) {
            LoadingUtils.hideDialog()
            binding.pullToRefresh.isRefreshing = false
            if (!it.success) return@observe
            productList =
                it.data as ArrayList<ProductListingResponseModelItem>
            productsAdapter.clearAndAddData(productList)
        }
    }

    private fun setUpRecyclerView() {
        productsAdapter = ProductsAdapter(
            productList,
            requireContext(),
            object : OnProductSelectedListenerCallback {
                override fun onSelected(position: Int) {
                    Toast.makeText(requireContext(), "Iam selected : $position", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        )
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    companion object {
        val TAG2: String? = ProductListingFragment::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
            ProductListingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}