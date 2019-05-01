package com.example.pop3app.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log
import com.example.pop3app.Connection.Connection
import com.example.pop3app.Connection.Login
import com.example.pop3app.R
import android.content.Intent
import javax.net.ssl.SSLSocket


class MainActivity : AppCompatActivity() {

    private val HOST = "pop.gmail.com"

    private val PORT = 995

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        print("Hello World!")

        //reference to elements in view
        var et_user_name = findViewById(R.id.editText4) as EditText
        var et_password = findViewById(R.id.editText3) as EditText
        var btn_submit = findViewById(R.id.button) as Button

        var conn = Connection
        var s = conn.execute()

        Toast.makeText(this@MainActivity,s.get(), Toast.LENGTH_LONG).show()


        // set on-click listener
        btn_submit.setOnClickListener {
            val username = et_user_name.text;
            val password = et_password.text;

            Log.d("Log","POP3 Client Started")
            val login = Login(username.toString(),password.toString(),conn.socket)
            var resp = login.execute()
            //Use message from server for Toast
            Toast.makeText(this@MainActivity,resp.get(), Toast.LENGTH_LONG).show()
            if (resp.get() =="+OK Welcome.") {
                val i = Intent(applicationContext, Main2Activity::class.java)
                startActivity(i)
            }

        }
    }

}
