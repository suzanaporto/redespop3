package com.example.pop3app.Connection

import java.io.BufferedReader
import java.io.InputStreamReader
import javax.net.ssl.SSLSocket

class SocketReader {

    fun readFromSocket(socket:SSLSocket):BufferedReader{

        var reader = BufferedReader(InputStreamReader(socket.inputStream,"utf-8"))
        return reader

    }
}