package com.yoochangwons.outstagram

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.yoochangwons.outstagram.databinding.ActivityOutStagramUpLoadBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class OutStagramUpLoadActivity : AppCompatActivity() {

    lateinit var filePath: String
    private lateinit var binding: ActivityOutStagramUpLoadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStagramUpLoadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.uploadPicture.setOnClickListener {
            getPicture()
        }

        binding.uploadButton.setOnClickListener {
            upLoadPost(binding.uploadContentText.text.toString())
        }

        binding.uploadMyPostListView.setOnClickListener {
            startActivity(
                Intent(this, OutStagramMyPostListActivity::class.java)
            )
        }

        binding.uploadUserInfoView.setOnClickListener {
            startActivity(
                Intent(this, OutStagrmLoginInfoActivity::class.java)
            )
        }

        binding.uploadPostAllView.setOnClickListener {
            startActivity(
                Intent(this, OutStagramPostListActivity::class.java)
            )
        }
    }

    fun getPicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val uri: Uri = data!!.data!!
            filePath = getImageFilePath(uri)
            Log.d("pathh", "path : $filePath")
        }
    }

    @SuppressLint("Recycle")
    fun getImageFilePath(contentUri: Uri): String {
        var columnIndex = 0
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri, projection, null, null, null)

        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(columnIndex)
    }

    fun upLoadPost(text: String) {
        val file = File(filePath)
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        val content = RequestBody.create(MediaType.parse("text/plain"), text)

        (application as MasterApplication).service.upLoadPost(part, content).enqueue(
            object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val post = response.body()
                        Log.d("pathh", post!!.content!!)
                        finish()
                        startActivity(
                            Intent(this@OutStagramUpLoadActivity, OutStagramMyPostListActivity::class.java)
                        )
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.d("1234", "error")
                }

            }
        )
    }

    fun getContent(): String {
        return binding.uploadContentText.text.toString()
    }
}