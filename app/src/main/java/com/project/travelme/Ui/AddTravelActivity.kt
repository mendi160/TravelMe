package com.project.travelme.Ui

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.project.travelme.R
import com.project.travelme.R.id.fixed
import com.project.travelme.R.id.passengers

class AddTravelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()
       // findViewById<EditText>(passengers).setText("0")
        findViewById<EditText>(passengers).addTextChangedListener {
           val  text=findViewById<EditText>(passengers).text.toString()



            if ( text==""||text.toInt()< 0)
                findViewById<EditText>(passengers).setText("0")
        }
    }


    fun less(view: View) {


            findViewById<EditText>(passengers).setText(
                (findViewById<EditText>(passengers).text.toString().toInt() - 1).toString()
            )
//


    }

    fun more(view: View) {
        if (  findViewById<EditText>(passengers).text.toString()==""  )
            findViewById<EditText>(passengers).setText("0")
        findViewById<EditText>(passengers).setText(
            (findViewById<EditText>(passengers).text.toString().toInt() + 1).toString()
        )
    }
}