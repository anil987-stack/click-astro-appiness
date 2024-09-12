package com.android.appinesstask

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.appinesstask.adapter.LanguageAdapter
import com.android.appinesstask.databinding.ActivityProductDetailsBinding
import com.android.appinesstask.model.ProductModel
import com.bumptech.glide.Glide

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: ProductModel? = intent.getParcelableExtra("product")


        product?.let {
            Glide.with(this).load(it.imagePath.square).into(binding.productIc)
            binding.productName.text = it.name
            binding.productPrice.text = "₹${it.price}"
            binding.productDes.text = it.description
            binding.pageTxt.text = it.pages.toString()
            binding.authTxt.text = it.authentic
            binding.vedicTxt.text = it.vedic
            binding.remediesTxt.text = it.remedies

            binding.recyclerViewLanguages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewLanguages.adapter = LanguageAdapter(it.availableLanguages)
        }
    }
}