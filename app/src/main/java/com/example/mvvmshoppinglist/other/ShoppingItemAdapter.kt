package com.example.mvvmshoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppinglist.R
import com.example.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.example.mvvmshoppinglist.databinding.ShoppingItemBinding
import com.example.mvvmshoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        //Beware that viewBinding enabled required different approach
        val binding =
            ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bind(currentShoppingItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.tvName.text = item.name
            binding.tvAmount.text = "${item.amount}"

            binding.ivDelete.setOnClickListener {
                viewModel.delete(item)
            }

            binding.ivAdd.setOnClickListener {
                //Increase List by 1 record before updating
                item.amount++
                viewModel.upsert(item)
            }

            binding.ivMinus.setOnClickListener {
                if (item.amount > 0) {
                    //Decrease List by 1 record before updating
                    item.amount--
                    viewModel.upsert(item)
                }
            }
        }
    }

}