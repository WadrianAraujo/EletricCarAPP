package com.example.eletriccarapp.presentation

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.data.CarsApi
import com.example.eletriccarapp.domain.Car
import com.example.eletriccarapp.presentation.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {
    private lateinit var btnRedirect: FloatingActionButton
    private lateinit var carList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var noInternetImage: ImageView
    private lateinit var noInternetText: TextView
    private lateinit var carsAPI: CarsApi


    var carsArray: ArrayList<Car> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
        setupListeners()
        if (checkForInternet(context)) {
            getALlCars()
        } else {
            showEmptyState()
        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wadrianaraujo.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        carsAPI = retrofit.create(CarsApi::class.java)
    }

    fun getALlCars(){
        carsAPI.getAllCars().enqueue(object :Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful){
                    progressBar.visibility = View.GONE

                    response.body()?.let {
                        setupList(it)
                    }
                }else{
                    Toast.makeText(context,R.string.response_error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Toast.makeText(context,R.string.response_error,Toast.LENGTH_LONG).show()
            }

        })
    }

    fun showEmptyState() {
        carList.isVisible = false
        progressBar.isVisible = false
        noInternetText.isVisible = true
        noInternetImage.isVisible = true
    }

    fun setupView(view: View) {
        btnRedirect = view.findViewById(R.id.fab_calculate)
        carList = view.findViewById(R.id.rv_list_cars)
        progressBar = view.findViewById(R.id.pb_loader)
        noInternetImage = view.findViewById(R.id.iv_empty_state)
        noInternetText = view.findViewById(R.id.tv_no_internet)
    }

    fun setupList(list : List<Car>) {
        carList.visibility = View.VISIBLE
        val adapter = CarAdapter(list)
        carList.adapter = adapter
    }

    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }

    fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}