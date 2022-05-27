package com.velus.interpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.interpreter.Interpreter;
import com.velus.datatransfer.Utils;
import java.util.HashMap;

public class Clear extends InterpreterCommand{
    public Clear(Interpreter interpreter){
        super(interpreter);
    }
    public void execute(){
        Request rq = new Request("clear", new HashMap<>());
        if(client.sendRequest(rq)){
            Response resp = client.receive();
            Utils.printResponseMessages(resp);
        }

    }
}
