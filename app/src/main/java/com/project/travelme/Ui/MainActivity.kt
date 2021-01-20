package com.project.travelme.Ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.project.travelme.R
import java.util.*

const val RC_SIGN_IN = 123

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var viewModel: TravelViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button: Button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {
            val i = Intent(this@MainActivity, AddTravelActivity::class.java)
            startActivity(i)
        }

        if (FirebaseAuth.getInstance().currentUser != null) {
            viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
            startService(Intent(this, MyService::class.java))
            return
        }
     //   createSignInIntent()
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser==null)
            createSignInIntent()
    }

    fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false).setLogo(R.drawable.klipartz)
                .build(),
            RC_SIGN_IN
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                Toast.makeText(this, "Logged In Successfully!", Toast.LENGTH_LONG).show()
                viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
                startService(Intent(this, MyService::class.java))
            }
        }
    }

    fun myRequestsActivity(view: View) {
        val i = Intent(
            this@MainActivity,
            RequestsActivity::class.java
        )
        startActivity(i)
    }

}




