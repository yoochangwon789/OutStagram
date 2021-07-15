package com.yoochangwons.outstagram

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoochangwons.outstagram.databinding.ActivityOutStagramPostListBinding
import com.yoochangwons.outstagram.databinding.OutstagramItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStagramPostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStagramPostListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramPostListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (application as MasterApplication).service.getAllPosts()
            .enqueue(object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val postList = response.body()
                        val adapter = PostAdapter(postList!!, this@OutStagramPostListActivity)
                        binding.postRecyclerView.apply {
                            this.adapter = adapter
                            layoutManager = LinearLayoutManager(this@OutStagramPostListActivity)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                }
            })

        binding.postUserInfoView.setOnClickListener {
            startActivity(
                Intent(this, OutStagrmLoginInfoActivity::class.java)
            )
        }

        binding.postUpLoadView.setOnClickListener {
            startActivity(
                Intent(this, OutStagramUpLoadActivity::class.java)
            )
        }

        binding.postMyPostView.setOnClickListener {
            startActivity(
                Intent(this, OutStrgramMyPostListActivity::class.java)
            )
        }
    }
}

class PostAdapter(
    private val dataSet: ArrayList<Post>,
    private val activity: Activity
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

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