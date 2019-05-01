package com.example.pop3app.Connection

import java.io.BufferedReader
import java.io.InputStreamReader
import javax.net.ssl.SSLSocket

class SocketMultReader {

    fun readFromSocket(socket: SSLSocket): ArrayList<String>{

        // Instantiate reader and get socket info
        var reader = BufferedReader(InputStreamReader(socket.inputStream,"UTF-8"))

        // Create list to get socket info as Array
        var list_items = arrayListOf<String>()

        // Loop through text from socket
        do{

            var r = reader.readLine()
            list_items.add(r)

        }while( r != ".")

        //close reader
        reader.close()

        return list_items
    }
}