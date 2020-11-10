package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.project.travelme.Utils.Address


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {
            val i = Intent(this@MainActivity, AddTravelActivity::class.java)
            startActivity(i)
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")
            try {
                myRef.setValue(Address("asdasd", "asdsada", 55))
            }
            catch (e:Exception)
            {
                Log.i("shit",e.message.toString())
            }
        }

    }
}

