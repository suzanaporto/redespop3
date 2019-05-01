package com.example.pop3app.Controller

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.pop3app.Connection.Connection
import com.example.pop3app.R

import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.content_main2.*
import android.webkit.WebView
import android.widget.TextView
import com.example.pop3app.Connection.EmailBody


class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(toolbar)

        //Setting textViews
        var subject = findViewById(R.id.textView2) as TextView
        subject.setText("Email Title")

        var from = findViewById(R.id.textView3) as TextView
        from.setText("From: ")

        var to = findViewById(R.id.textView5) as TextView
        to.setText("To: ")

        var email_number = ""

        //get Email Number
        val extras = intent.extras
        if (extras != null) {
            email_number = extras.getString("e_number")
        }

        println("NUMBER: " + email_number)

        //Get Socket
        var conn = Connection

        var email = EmailBody(conn.socket,email_number.toInt())

        var resp = email.execute()

        var email_html = resp.get()

        var test = false

        var email_mime = arrayListOf<String>()

        for (line in email_html){

            if ( line.contains("MIME-Version", ignoreCase = true ) ){
                test = true
            }
            if ( test ){
                email_mime.add(line)
            }
        }

        val sb = StringBuilder()

        for (line_e in email_mime){
            sb.append(line_e)
        }

        val email_mime_full = sb.toString()

        var webView = findViewById(R.id.Email_view_1) as WebView
        webView.getSettings().setJavaScriptEnabled(true)

        var sender:String
        var receiver:String
        var contentType = "text/html"
        var subject_text:String


        for (l in email_mime){
            if (l.contains(Regex("(From: )(\\w|=)+"))){
                sender = l
                from.setText(sender)
            }else if (l.contains(Regex("(To: )(\\w)+"))){
                receiver = l
                to.setText(receiver)
            } else if(l.contains("Subject:",ignoreCase = false)){
                var start_index = l.indexOf(":") + 1
                subject_text = l.substring(start_index)
                subject.setText(subject_text)
            }
        }

        var stringList = ArrayList<String>();

//        stringList.add(email_mime_full.split("From: ")[1].split("Date: ")[0]);
//        stringList.add(email_mime_full.split("Date: ")[1].split("Message-ID: ")[0]);
//        stringList.add(email_mime_full.split("Subject: ")[1].split("To: ")[0]);
//        stringList.add(email_mime_full.split("To: ")[1].split("\r\n")[0]);
//        stringList.add(email_mime_full.split("Encoding: ")[1].split("<")[0])

//        from.setText(stringList[0])
//        to.setText(stringList[3])
//        subject.setText(stringList[2])
//
//        var html_full = stringList[4]

//        println("CONTENT TYPE: $contentType")

        val customHtml = email_mime_full
//        val text_html = "<${customHtml.split("Encoding: ")[1].split("<")[1]}"
        webView.loadData(customHtml, "text/html", "UTF-8")
//        webView.loadData(customHtml, "text/plain", "Base64")
    }

}
