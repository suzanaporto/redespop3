package com.example.pop3app.Commands

import java.io.PrintWriter

interface CommandTRANS {
    //TRANSACTION State
    fun STAT(writer: PrintWriter)
    fun LIST(writer: PrintWriter)
    fun RETR(writer: PrintWriter,message_number:Int)
    fun DELE(writer: PrintWriter,message_number:Int)
    fun NOOP(writer: PrintWriter)
    fun RSET(writer: PrintWriter)
}