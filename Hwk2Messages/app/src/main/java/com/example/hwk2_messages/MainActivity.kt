package com.example.hwk2_messages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var mAuth : FirebaseAuth
    private lateinit var userNameText:EditText
    private lateinit var PasswordText:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        var buttonLogin = findViewById(R.id.Login_buttion) as Button

        buttonLogin.setOnClickListener {
            userNameText = findViewById(R.id.UsernameTextView)
            PasswordText = findViewById(R.id.passwordTextView)

            var userNamedata = userNameText.text.toString()
            var passWord = PasswordText.text.toString()

            mAuth.signInWithEmailAndPassword(userNamedata,passWord).addOnCompleteListener(
                this
            ){task ->
                if (task.isSuccessful){
                    val intent = Intent(this@MainActivity,MessageActivity::class.java)
                    startActivity(intent)
                }
                else{
                    var failMeassage=R.string.loginFail
                    Toast.makeText(this@MainActivity,failMeassage,Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}