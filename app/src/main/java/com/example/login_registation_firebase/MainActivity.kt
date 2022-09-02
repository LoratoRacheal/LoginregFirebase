package com.example.login_registation_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ayush.quizapp.activity.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var EdtEmail:EditText
    private lateinit var EdtPass:EditText
    private lateinit var EdtConPas:EditText
    lateinit var BtnSingUp:Button
    lateinit var TvDirectLogin:TextView
    private lateinit var Auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EdtEmail=findViewById(R.id.etSEmailAddress)
        EdtPass=findViewById(R.id.etSPassword)
        EdtConPas=findViewById(R.id.etSConfPassword)
        BtnSingUp=findViewById(R.id.btnSSigned)
        TvDirectLogin=findViewById(R.id.tvRedirectLogin)
        Auth= Firebase.auth


        BtnSingUp.setOnClickListener {
            SignUpUser()
        }
        TvDirectLogin.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    fun SignUpUser(){
        val email=EdtEmail.text.toString()
        val pass=EdtPass.text.toString()
        val confirmPass=EdtConPas.text.toString()
        
        if (email.isBlank() or pass.isBlank() or confirmPass.isBlank() ){
            Toast.makeText(this, "Email or Password can't be blank", Toast.LENGTH_LONG).show()
            return
        }
        if(pass!=confirmPass){
           Toast.makeText(this,"Passwords do not match ", Toast.LENGTH_LONG).show()
            return
        }
        Auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Failed to create",Toast.LENGTH_LONG).show()
            }
        }
    }
}