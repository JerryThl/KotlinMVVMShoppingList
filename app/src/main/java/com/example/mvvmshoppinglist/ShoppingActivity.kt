package com.example.mvvmshoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmshoppinglist.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}