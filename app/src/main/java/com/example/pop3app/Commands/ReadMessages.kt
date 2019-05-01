package com.example.pop3app.Commands

import java.io.BufferedReader

class ReadMessages {

    fun readFromServer (message_type:Int,reader: BufferedReader){

        //read one line or two depending on the message sent
        if (message_type == 1){
            println("[FROM Server] " + reader.readLine())
        }else{
            if (message_type == 2){
                for(line in reader.readLines()){
                    println("[FROM Server] $line")
                }
            }
        }
    }
}