package com.example.pop3app.Connection

import android.os.AsyncTask
import com.example.pop3app.Commands.Optional
import java.io.PrintWriter
import javax.net.ssl.SSLSocket

class EmailHeader (socket: SSLSocket, email_numbers:ArrayList<String>): AsyncTask<Void, Void, ArrayList<String>>(){

    var socket = socket
    var email_numbers = email_numbers

    override fun doInBackground(vararg params: Void?): ArrayList<String> {
        var response = get_email_subject()
        return response
    }


    fun get_email_subject():ArrayList<String>{

        var array_subjects = ArrayList<String>()

        for(num in this.email_numbers){

            var sReader = SocketMultReader()
            val writer = PrintWriter(this.socket.outputStream,true)
            val opt_commands = Optional()
            opt_commands.TOP(writer,num.toInt(),0)
            var mail_lines = sReader.readFromSocket(this.socket)
            for(line in mail_lines){
                if (line.contains("Subject:")){
                    array_subjects.add(line)
                }
            }
        }
        return array_subjects
    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)
    }



}