package com.example.pop3app.Commands

import java.io.PrintWriter

interface CommandOPT {
    //Optional POP3
    fun TOP(writer: PrintWriter,message_number:Int,lines_number:Int)
    fun UIDL(writer: PrintWriter)
    fun USER(user_name:String,writer: PrintWriter)
    fun PASS(password:String,writer: PrintWriter)
    fun APOP(username:String,password:String,writer: PrintWriter)
}