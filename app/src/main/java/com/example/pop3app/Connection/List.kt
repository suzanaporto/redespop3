package com.example.pop3app.Connection

import android.os.AsyncTask
import com.example.pop3app.Commands.Transaction
import com.example.pop3app.Controller.Main2Activity
import java.io.PrintWriter
import javax.net.ssl.SSLSocket
import android.util.Log


class List (socket: SSLSocket): AsyncTask<Void, Void, ArrayList<String> >(){

    var socket = socket


    override fun doInBackground(vararg params: Void?): ArrayList<String> {
        var response = list_message()
        return response
    }

    fun list_message():ArrayList<String>{

        //Call reader class
        val sReader = SocketMultReader()
        //create writer to send message to socket
        val writer = PrintWriter(this.socket.outputStream,true)
        val x = Transaction()
        x.LIST(writer)
        //Read from socket
        var mail_items = sReader.readFromSocket(this.socket)
        return mail_items
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)
    }

}