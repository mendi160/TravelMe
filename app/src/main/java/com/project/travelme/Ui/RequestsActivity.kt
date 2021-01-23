package com.project.travelme.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.travelme.R

class RequestsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requests)
        recyclerView = findViewById(R.id.recycleView)
        recyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(applicationContext)
        }
        MainActivity.viewModel.getAllTravels().observe(this, {
            if (it != null)
                recyclerView.adapter = TravelRecyclerViewAdapter(it)
        })
    }
}

