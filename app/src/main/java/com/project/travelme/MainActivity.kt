package com.project.travelme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.VoicemailContract
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.firebase.auth.FirebaseAuth
import com.project.travelme.Utils.Enums.Status
import java.util.*


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 123
    private val api = "AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById<Button>(R.id.addButton)




        button.setOnClickListener {
            createSignInIntent()


        }

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
        // [END auth_fui_create_intent]
    }

    // [START auth_fui_result]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            val user = FirebaseAuth.getInstance().currentUser
            // Successfully signed in


            if (user != null) {
                Toast.makeText(this, user.uid.toString(), Toast.LENGTH_LONG).show()
            }
            if (resultCode == Activity.RESULT_OK) {

                val i = Intent(this@MainActivity, AddTravelActivity::class.java)
                startActivity(i)


                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    // [END auth_fui_result]
}




