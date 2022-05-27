package com.velus;

import com.velus.responseinterpreter.Interpreter;
import com.velus.workersmanager.WorkersManager;

import java.io.IOException;
import java.net.BindException;

public class Main {

    public static void main(String[] args) throws IOException {
        int port;
        if(args.length < 2){
            port = 13338;
        } else{
            port = Integer.parseInt(args[1]);
        }
        WorkersManager manager = new WorkersManager();
        try {
            manager.load();
        } catch (Exception ex) {
            manager.dump();
        }
        Interpreter interpreter = new Interpreter(manager);
        Server server = new Server(interpreter);
        try {
            server.bind(port);
            server.run();
        } catch (BindException ex) {
            System.out.println("Порт занят");
        }
    }
}
