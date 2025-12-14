package com.example.homework_26

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_26.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AdviceAdapter
    private lateinit var binding: ActivityMainBinding
    private var fullResponses = mutableListOf<FullResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        adapter.updateList(fullResponses.toList())

        binding.getButton.setOnClickListener {
            getRandomAdvice()
        }
    }

    private fun getRandomAdvice() {
        lifecycleScope.launch {
            val newFullResponse = fetchStringFromNetwork()
            fullResponses.add(parseJSON(newFullResponse))
            adapter.updateList(fullResponses.toList())
        }
    }

    private suspend fun fetchStringFromNetwork(): String {

        val result: String = withContext(Dispatchers.IO) {
            val url = URL("https://api.adviceslip.com/advice")
            val urlConnetion = url.openConnection() as HttpURLConnection
            urlConnetion.requestMethod = "GET"
            urlConnetion.connect()

            val inputStream = urlConnetion.getInputStream()
            val reader = BufferedReader(InputStreamReader(inputStream))
            return@withContext reader.readLine()
        }

        return result
    }

    private fun parseJSON(stringFromNetwork: String): FullResponse {
        val rootObject = JSONObject(stringFromNetwork)
        val slipObject = rootObject.getJSONObject("slip")
        val id = slipObject.getInt("id")
        val advice = slipObject.getString("advice")
        return FullResponse(stringFromNetwork, advice, id)

    }

    fun setupRecyclerView() {
        adapter = AdviceAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(this)

    }
}