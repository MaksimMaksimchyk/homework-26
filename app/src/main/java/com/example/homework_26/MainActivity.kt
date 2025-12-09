package com.example.homework_26

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_26.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AdviceAdapter
    private lateinit var binding: ActivityMainBinding
    private var advices = mutableListOf<Advice>()

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
        advices.add(Advice("test 1", 1))
        advices.add(Advice("test 2", 22))
        adapter.updateList(advices.toList())
    }

    fun setupRecyclerView() {
        adapter = AdviceAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(this)

    }
}