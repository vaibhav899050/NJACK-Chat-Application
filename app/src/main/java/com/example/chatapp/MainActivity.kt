package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var btn_login: Button
    private lateinit var btn_register: Button
    private lateinit var edt_email: EditText
    private lateinit var edt_pass: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login = findViewById(R.id.btn_login)
        btn_register = findViewById(R.id.btn_register)
        edt_email = findViewById(R.id.edt_email)
        edt_pass = findViewById(R.id.edt_pass)
        auth = Firebase.auth


        btn_register.setOnClickListener {

            val intent = Intent(this, register::class.java)
            startActivity(intent)

        }
        btn_login.setOnClickListener {

            //logic for login
            val email = edt_email.text.toString()
            val password = edt_pass.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, dashboard::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }



        }

    }
}