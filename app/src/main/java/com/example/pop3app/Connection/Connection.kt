package com.example.pop3app.Connection

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

object Connection : AsyncTask<Void, Void, String>() {

    var HOST:String = "pop.gmail.com"
    var PORT:Int = 995
    lateinit var socket:SSLSocket

    override fun doInBackground(vararg params: Void?):String? {

        var s = connect_POP3(this.HOST,this.PORT)
        var reader = BufferedReader(InputStreamReader(socket.inputStream))
        return reader.readLine()
    }

    fun connect_POP3(HOST:String,PORT:Int):SSLSocket{
        val factory = SSLSocketFactory.getDefault() as SSLSocketFactory
        this.socket = factory.createSocket(HOST, PORT) as SSLSocket
        //set client mode/Optional?
        this.socket.useClientMode = true
        //set Cipher and Protocols/Optional?
        val x = this.socket.supportedProtocols
        val suites = this.socket.enabledCipherSuites
        this.socket.enabledProtocols = x
        this.socket.enabledCipherSuites = suites

        this.socket.startHandshake()
        return this.socket

    }

}