package com.example.pop3app.Commands

import java.io.PrintWriter

interface CommandAUTH {
    //AUTHORIZATION State/OK
    fun QUIT(writer: PrintWriter)
}