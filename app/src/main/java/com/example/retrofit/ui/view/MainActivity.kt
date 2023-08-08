package com.example.retrofit.ui.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.repository.PostRepository
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.ui.viewmodel.PostViewModel
import com.example.retrofit.ui.viewmodel.factory.PostViewModelFactory
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkForInternet(this)) {

            bindViews()

        } else {

            AlertDialog.Builder(this).apply {
                setTitle("Internet Error")
                setMessage("Please check your internet connection")

                setPositiveButton("RETRY") { _, _ ->
                    val intent = Intent(this@MainActivity, this@MainActivity::class.java)
                    startActivity(intent)
                }

                setNegativeButton("EXIT") { _, _ ->
                    MainActivity().finish()
                    moveTaskToBack(true)
                    exitProcess(-1)
                }

                setCancelable(false)
            }.create().show()
        }
    }

    private fun bindViews() {
        // initializing recycler view
        /* binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        } */

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]

        val postItem = PostItem(10, 100, "Retrofit", "Retrofit Post App")
        viewModel.setPosts(postItem)
        viewModel.pushPostsResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Toast.makeText(this, response.body().toString(), Toast.LENGTH_LONG).show()
            } else Toast.makeText(this, response.code().toString(), Toast.LENGTH_LONG).show()
        }

    }

    // Check Internet Connection
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}