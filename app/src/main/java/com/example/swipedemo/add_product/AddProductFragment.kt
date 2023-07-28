package com.example.swipedemo.add_product

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.swipedemo.add_product.utils.AddProductViewModel
import com.example.swipedemo.databinding.FragmentAddProductBinding
import com.example.swipedemo.utils.LoadingUtils
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class AddProductFragment : Fragment() {

    private lateinit var binding: FragmentAddProductBinding
    private val addProductViewModel: AddProductViewModel by viewModels()
    private var imagePart: MultipartBody.Part? = null
    private var selectedImageUri: Uri? = null
    private lateinit var productTypes: List<String>
    private var selectedProductType: String? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                selectedImageUri = uri
                displaySelectedImage()
                val imageFile = getRealPathFromUri(data.data)?.let { File(it) }
                val requestFile = imageFile?.asRequestBody("image/*".toMediaTypeOrNull())
                imagePart = requestFile?.let {
                    MultipartBody.Part.createFormData(
                        "files[]",
                        imageFile.name, it
                    )
                }
            }
        }
    }

    private fun displaySelectedImage() {
        selectedImageUri?.let { uri ->
            binding.ivPreviewImage.setImageURI(uri)
            binding.cvCardView.visibility = View.VISIBLE
        }
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddProductBinding.inflate(layoutInflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        doOnTextChanged()
        setUpSpinner()
        spinnerItemSelectedListener()
        setCta()
    }

    private fun spinnerItemSelectedListener() {
        binding.productTypeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedProductType = productTypes[position]
                    binding.errorMessage.visibility = View.GONE
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    selectedProductType = null
                }

            }
    }

    private fun setUpSpinner() {
        productTypes = listOf("Electronics", "Clothing", "Food", "Banking", "Furniture's")
        val productTypeSpinner: Spinner = binding.productTypeSpinner
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, productTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productTypeSpinner.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        LoadingUtils.hideDialog()
    }

    private fun observer() {
        addProductViewModel.getAddProductLiveData().observe(viewLifecycleOwner) {
            LoadingUtils.hideDialog()
            if (!it.success) {
                Snackbar.make(
                    binding.root,
                    it.data?.message.toString(),
                    Snackbar.ANIMATION_MODE_SLIDE
                )
                    .show()
                return@observe
            }
            Snackbar.make(binding.root, it.data?.message.toString(), Snackbar.LENGTH_SHORT)
                .show()
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun doOnTextChanged() {
        binding.etProductName.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) binding.productName.error = null
        }
        binding.etProductPrice.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) binding.productPrice.error = null
        }
        binding.etProductTax.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) binding.productTax.error = null
        }
    }

    private fun setCta() {
        binding.btCtaAddProduct.setOnClickListener {
            if (!validateInputs()) return@setOnClickListener
            LoadingUtils.showDialog(requireContext(), false)
            submitData()
        }
        binding.tvCtaAddImage.setOnClickListener {
            /*pickImage()*/
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(galleryIntent)
        }
    }

    private fun getRealPathFromUri(uri: Uri?): String? {
        uri?.let {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(uri, projection, null, null, null)
            cursor?.use {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.moveToFirst()
                return it.getString(columnIndex)
            }
        }
        return null
    }

    private fun submitData() {
        addProductViewModel.addProduct(
            binding.etProductName.text.toString()
                .toRequestBody("text/plain".toMediaTypeOrNull()),
            selectedProductType!!
                .toRequestBody("text/plain".toMediaTypeOrNull()),
            binding.etProductPrice.text.toString()
                .toRequestBody("text/plain".toMediaTypeOrNull()),
            binding.etProductTax.text.toString()
                .toRequestBody("text/plain".toMediaTypeOrNull()),
            imagePart
        )
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun validateInputs(): Boolean {
        if (binding.etProductName.text.isNullOrBlank()) {
            binding.productName.error = "Please enter product name"
            return false
        }
        if (selectedProductType.isNullOrEmpty()) {
            binding.errorMessage.visibility = View.VISIBLE
            return false
        }
        if (binding.etProductPrice.text.isNullOrBlank()) {
            binding.productPrice.error = "Please enter product price"
            return false
        }
        if (binding.etProductTax.text.isNullOrBlank()) {
            binding.productTax.error = "Please enter applicable tax"
            return false
        }
        return true
    }

    companion object {
        val TAG2: String? = AddProductFragment::class.java.canonicalName

        @JvmStatic
        fun newInstance() =
            AddProductFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}