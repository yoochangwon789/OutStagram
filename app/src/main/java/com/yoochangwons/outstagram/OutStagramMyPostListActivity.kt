package com.yoochangwons.outstagram

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoochangwons.outstagram.databinding.ActivityOutStagramMyPostListBinding
import com.yoochangwons.outstagram.databinding.OutstagramItemViewBinding

class OutStagramMyPostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStagramMyPostListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramMyPostListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

class MyPostAdapter(
    private val dataSet: ArrayList<Post>,
    private val activity: Activity
) : RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    class ViewHolder(val itemViewBinding: OutstagramItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.outstagram_item_view, viewGroup, false)

        return ViewHolder(OutstagramItemViewBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemViewBinding.postItemOwner.text = dataSet[position].owner
        viewHolder.itemViewBinding.postItemContent.text = dataSet[position].content
        Glide.with(activity)
            .load(dataSet[position].image)
            .into(viewHolder.itemViewBinding.postItemImage)
    }

    override fun getItemCount() = dataSet.size
}