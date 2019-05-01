package com.example.pop3app.Connection

import android.os.AsyncTask
import com.example.pop3app.Commands.Transaction
import java.io.PrintWriter
import javax.net.ssl.SSLSocket

class EmailBody (socket:SSLSocket,email_number:Int): AsyncTask<Void,Void,ArrayList<String>>(){

    var socket = socket
    var number = email_number

    override fun doInBackground(vararg params: Void?): ArrayList<String> {
        var response = getEmailBody()
        return response
    }

    fun getEmailBody():ArrayList<String>{

        var sReader = SocketMultReader()
        val writer = PrintWriter(this.socket.outputStream,true)
        val trans_commands = Transaction()
        trans_commands.RETR(writer,this.number)

        var mail_lines = sReader.readFromSocket(this.socket)
        return mail_lines

    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)
    }
}