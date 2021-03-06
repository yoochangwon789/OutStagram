package com.yoochangwons.outstagram

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoochangwons.outstagram.databinding.ActivityOutStagramMyPostListBinding
import com.yoochangwons.outstagram.databinding.OutstagramItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStagramMyPostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStagramMyPostListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramMyPostListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.myPostAllPostView.setOnClickListener {
            startActivity(
                Intent(this, OutStagramPostListActivity::class.java)
            )
        }
        binding.myPostUpLoadView.setOnClickListener {
            startActivity(
                Intent(this, OutStagramUpLoadActivity::class.java)
            )
        }
        binding.myPostUserInfoView.setOnClickListener {
            startActivity(
                Intent(this, OutStagrmLoginInfoActivity::class.java)
            )
        }

        (application as MasterApplication).service.getUserPostList().enqueue(
            object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    Log.d("123123", "error" + response.body())
                    if (response.isSuccessful) {
                        val myPostList = response.body()
                        val adapter = MyPostAdapter(myPostList!!, this@OutStagramMyPostListActivity)
                        binding.myPostRecyclerView.adapter = adapter
                        binding.myPostRecyclerView.layoutManager = LinearLayoutManager(this@OutStagramMyPostListActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                    Log.d("123123", "error")
                }
            }
        )
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