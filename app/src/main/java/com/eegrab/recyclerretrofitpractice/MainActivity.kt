package com.eegrab.recyclerretrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eegrab.recyclerretrofitpractice.databinding.ActivityMainBinding
import okio.IOException
import retrofit2.HttpException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getUserDetails()
            } catch (e: IOException) {
                Log.d(TAG,"IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException)  {
                Log.d(TAG,"HttpException, unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                userAdapter.userList = response.body()!!
            } else {
                Log.d(TAG,"Response not successful")
            }
        }
    }

    private fun setupRecyclerView() = binding.rvUser.apply {
            userAdapter = UserAdapter()
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
    }
}


