package com.example.pop3app.Commands

import java.io.PrintWriter

class Optional:CommandOPT {

    // Server sends headers and the first n lines specified
    //RETURN: multiple lines from server
    override fun TOP(writer: PrintWriter,message_number:Int,lines_number:Int) {
        writer.println("TOP $message_number $lines_number")
    }
    // Unique id listing
    //RETURN: multiple lines from server
    override fun UIDL(writer: PrintWriter) {
        writer.println("UIDL")
    }

    //send username to socket in login process
    //RETURN: only one line from server
    override fun USER(user_name: String, writer: PrintWriter) {
        writer.println("USER $user_name")
    }

    //send password to socket in login process
    //RETURN: only one line from server
    override fun PASS(password: String, writer: PrintWriter) {
        writer.println("PASS $password")
    }

    //send user and pass to authenticate
    //RETURN: only one line from server
    override fun APOP(username: String, password: String, writer: PrintWriter) {
        TODO("not implemented")

    }
}