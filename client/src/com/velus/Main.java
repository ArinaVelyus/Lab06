package com.velus;

import com.velus.datatransfer.Request;
import com.velus.interpreter.Interpreter;
import com.velus.io.CommandLineInputManager;
import com.velus.io.CommandLineOutManager;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here


        int port;
        if(args.length < 2){
            port = 13338;
        } else{
            port = Integer.parseInt(args[1]);
        }

        CommandLineInputManager manager = new CommandLineInputManager();
        Request rq;
        Client client = new Client();
        client.connect("127.0.0.1", port);
        if(!client.isConnected()){
            client.waitConnection();
        }
        Interpreter interpreter = new Interpreter(new CommandLineInputManager(), new CommandLineOutManager(), client);
        interpreter.run();



    }
}
