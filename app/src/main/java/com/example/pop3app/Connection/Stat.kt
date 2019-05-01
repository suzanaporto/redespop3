package com.example.pop3app.Connection

import android.os.AsyncTask
import com.example.pop3app.Commands.Transaction
import java.io.PrintWriter
import javax.net.ssl.SSLSocket

class Stat (socket: SSLSocket): AsyncTask<Void, Void, String>(){

    var socket = socket

    override fun doInBackground(vararg params: Void?): String {
        var response = stat_message()
        return response
    }

    fun stat_message():String{

        //Call reader class
        val sReader = SocketReader()
        //Read from socket
        var reader = sReader.readFromSocket(this.socket)
        //create writer to send message to socket
        val writer = PrintWriter(this.socket.outputStream,true)
        val x = Transaction()
        x.STAT(writer)
        return reader.readLine()
    }
}