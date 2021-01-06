package com.project.travelme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RequestsActivity : AppCompatActivity() {
   // lateinit var model: TravelViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requests)
//        model = ViewModelProvider(this).get(TravelViewModel::class.java)
//            .also { model = it }
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

