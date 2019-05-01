package com.example.pop3app.Connection

import android.os.AsyncTask
import android.util.Log
import java.io.PrintWriter
import javax.net.ssl.SSLSocket
import com.example.pop3app.Commands.Optional
class Login(username:String,password:String,socket:SSLSocket) : AsyncTask<Void, Void, String>() {

    var username = username
    var password = password
    var socket = socket

    override fun doInBackground(vararg params: Void?): String {
        var response = makeLogin()
        return response
    }

    fun makeLogin():String{
        //Call reader class
        val sReader = SocketReader()
        //Read from socket
        var reader = sReader.readFromSocket(this.socket)
        //create writer to send message to socket
        val writer = PrintWriter(this.socket.outputStream,true)
        val x = Optional()
        x.USER(this.username,writer)
        Log.d("FROM SERVER",reader.readLine())// +OK PASS
        x.PASS(this.password,writer)
        return reader.readLine()
    }
}