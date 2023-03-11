package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {

    private lateinit var edt_name: EditText
    private lateinit var edt_email: EditText
    private lateinit var edt_pass: EditText
    private lateinit var edt_phone: EditText
    private lateinit var btn_register: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var dbref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        edt_name = findViewById(R.id.edt_name)
        edt_email = findViewById(R.id.edt_email)
        edt_phone = findViewById(R.id.edt_Phone)
        edt_pass = findViewById(R.id.edt_pass)
        btn_register = findViewById(R.id.btn_register)
        dbref = FirebaseDatabase.getInstance().getReference()

        auth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {

            val email = edt_email.text.toString()
            val password = edt_pass.text.toString()
            //logic for register

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid!!
                        val name = edt_name.text.toString()
                        val phone = edt_phone.text.toString()

                        dbref.child("user").child(uid).setValue(user(name, email, phone, uid))

                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "User is not created", Toast.LENGTH_SHORT).show()
                    }
                }


        }



    }
}