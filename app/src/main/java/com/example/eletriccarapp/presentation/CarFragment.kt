package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.domain.Car
import com.example.eletriccarapp.presentation.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {
    private lateinit var btnRedirect: FloatingActionButton
    private lateinit var carList: RecyclerView
    private lateinit var progressBar: ProgressBar

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

        setupView(view)
        callService()
        setupListeners()
    }

    fun setupView(view: View) {
        btnRedirect = view.findViewById(R.id.fab_calculate)
        carList = view.findViewById(R.id.rv_list_cars)
        progressBar = view.findViewById(R.id.pb_loader)

    }

    fun setupList() {
        carList.visibility = View.VISIBLE
        val adapter = CarAdapter(carsArray)
        carList.adapter = adapter
    }

    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))
        }
    }

    fun callService() {
        val urlbase = "https://igorbag.github.io/cars-api/cars.json"
        MyTask().execute(urlbase)
    }

    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val responseCode = urlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK){
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                }else{
                    Log.e("Erro","Serviço indisponivel no momento")
                }


            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento")
            } finally {
                urlConnection?.disconnect()
            }

            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID -> ", id)

                    val price = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("preco -> ", price)

                    val battery = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("bateria -> ", battery)

                    val power = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("potencia -> ", power)

                    val charger = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("recarga -> ", charger)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto -> ", urlPhoto)

                    val model = Car(
                        id = id.toInt(),
                        price = price,
                        battery = battery,
                        power = power,
                        charger = charger,
                        urlPhoto = urlPhoto
                    )

                    carsArray.add(model)

                }
                progressBar.visibility = View.GONE
                setupList()
            } catch (ex: Exception) {
                Log.e("Erro ->", ex.message.toString())
            }
        }
    }
}