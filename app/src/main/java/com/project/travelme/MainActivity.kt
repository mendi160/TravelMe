package com.project.travelme

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.project.travelme.Ui.AddTravelActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {
            val i = Intent(this@MainActivity, AddTravelActivity::class.java)
            startActivity(i)
        }

    }
}

