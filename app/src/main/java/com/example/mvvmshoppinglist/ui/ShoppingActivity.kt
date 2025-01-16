package com.example.mvvmshoppinglist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmshoppinglist.data.db.ShoppingDatabase
import com.example.mvvmshoppinglist.data.repositories.ShoppingRepository
import com.example.mvvmshoppinglist.databinding.ActivityShoppingBinding
import com.example.mvvmshoppinglist.ui.shoppinglist.ShoppingViewModel
import com.example.mvvmshoppinglist.ui.shoppinglist.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //These are a bad practice, will can be solve by using injection dependency
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        //ViewModelProviders deprecated, but this work the same
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

    }
}