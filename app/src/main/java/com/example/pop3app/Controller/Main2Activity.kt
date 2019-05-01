package com.example.pop3app.Controller

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.View
import android.widget.*
import com.example.pop3app.R

import kotlinx.android.synthetic.main.activity_main2.*
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.example.pop3app.Connection.*
import com.example.pop3app.Connection.List


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        // Make sure we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // Set labels
        val list_text = findViewById(R.id.textView11) as TextView

        var labelOK = findViewById(R.id.textView10) as TextView
        labelOK.setText("+OK Welcome")

        // Connect to socket again
        var conn = Connection

        // Get stat message and put in resp variable
        var stat_message = Stat(conn.socket)
        var resp = stat_message.execute()

        list_text.text = resp.get()

        // Get emails list and put in a ListView
        var email_list = List(conn.socket)
        var email_list_to_view = email_list.execute()

        var emails = email_list_to_view.get()

        var email_numbers = ArrayList<String>()

        //send to TOP command to execute async task and get subject

        Log.d("TAMANHO DA LISTA: ", emails.size.toString())

        var listView = findViewById(R.id.list_email) as ListView

        // Drop fist and last lines from emails list.
        // First one is number of messages information
        // and second one is a dot .
        emails.removeAt(0)
        emails.removeAt(emails.size - 1)

        for(n in emails){
            email_numbers.add(n.split(" ")[0])
        }

//        var subjects = EmailHeader(conn.socket,email_numbers)
//        var list_titles = subjects.execute()
//
//        var titles = list_titles.get()


        // Put list items in listView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emails)

        listView.adapter = adapter

        // fab button is a quit button
        // fab button action
        val fab = findViewById<FloatingActionButton>(com.example.pop3app.R.id.floatingActionButton)
        fab.setOnClickListener {

            // End connection
            val quit = Leave(conn.socket)
            val q = quit.execute()

            // Get farewell message from socket
            Toast.makeText(applicationContext, q.get(), Toast.LENGTH_SHORT).show()
            // Close socket connection
            conn.socket.close()
            // Send user to login page

            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }

        // Go see email when click in element in list view
        listView.setOnItemClickListener(object : OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // When clicked perform some action...
                //get text from list to identify mail
                var text_from_item =(listView.getItemAtPosition(position));
                Log.d("TEXT from item",text_from_item.toString())

                //pass mail number to web view activity
                val item_list = text_from_item.toString().split(" ")

                val email_number = item_list.get(0)

//                var number_sub = position
//
//                println("NUMBER"+number_sub)

                //go to web_view activity to see mail
                val go_main3 = Intent(applicationContext, Main3Activity::class.java)
                go_main3.putExtra("e_number", email_number)
                startActivity(go_main3)

            }
        })

    }

}
