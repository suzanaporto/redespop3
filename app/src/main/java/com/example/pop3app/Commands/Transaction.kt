package com.example.pop3app.Commands

import java.io.PrintWriter

class Transaction:CommandTRANS {

    // Get number of messages and total number of bytes
    //RETURN: only one line from server
    override fun STAT(writer: PrintWriter) {
        writer.println("STAT")
    }

    // List messages (number and bytes)
    //RETURN: multiple lines from server
    override fun LIST(writer: PrintWriter) {
        writer.println("LIST")

    }

    // Get all e-mail (partially in html code)
    //RETURN: multiple lines from server
    override fun RETR(writer: PrintWriter,message_number:Int) {
        writer.println("RETR $message_number")

    }

    // Delete message
    //RETURN: only one line from server
    override fun DELE(writer: PrintWriter,message_number:Int) {
        writer.println("DELE $message_number")

    }

    //Replies a positive response
    //RETURN: only one line from server
    override fun NOOP(writer: PrintWriter) {
        writer.println("NOOP")

    }

    //undelete all messages/
    //RETURN: only one line from server
    override fun RSET(writer: PrintWriter) {
        writer.println("RSET")

    }
}