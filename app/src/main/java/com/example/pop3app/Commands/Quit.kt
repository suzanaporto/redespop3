package com.example.pop3app.Commands

import java.io.PrintWriter
import javax.net.ssl.SSLSocket

class Quit :CommandAUTH{

    // Close connection to pop3
    override fun QUIT(writer: PrintWriter) {
        writer.println("QUIT")
    }

}