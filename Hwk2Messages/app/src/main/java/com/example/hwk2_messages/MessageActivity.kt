package com.example.hwk2_messages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant

class MessageActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var NameTextView:EditText
    private lateinit var CityTextView:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        db = FirebaseFirestore.getInstance()

        var buttionGreeting = findViewById(R.id.greetingsButton) as Button
        var buttonGoodbye = findViewById(R.id.goodbyeButton) as Button
        var buttonCity = findViewById(R.id.showButton) as Button


        buttionGreeting.setOnClickListener {
            var greetingMessage = R.string.hello_Buttion_Message
            Toast.makeText(this@MessageActivity,greetingMessage,Toast.LENGTH_SHORT).show()
        }

        buttonGoodbye.setOnClickListener {
            var goodbyeMessage = R.string.goodbye_Buttion_Message
            Toast.makeText(this@MessageActivity,goodbyeMessage,Toast.LENGTH_SHORT).show()
        }

        buttonCity.setOnClickListener {
            NameTextView = findViewById(R.id.namePlanTextView)
            CityTextView = findViewById(R.id.CityTextView)
            var userNameData = NameTextView.text.toString()

            db.collection("Hwk2Addresses").whereEqualTo("name",userNameData)
                .get().addOnSuccessListener { result->
                    var doc =result.documents.get(0)
                    var City = doc.getString("city")
                    CityTextView.setText(City)
                }
                .addOnFailureListener{exception ->
                    Log.w("MYDEBUG","ERROR ON GETTING DOC.",exception)
                }
        }
    }
}