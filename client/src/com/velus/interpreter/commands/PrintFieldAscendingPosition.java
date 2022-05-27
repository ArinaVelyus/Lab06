package com.velus.interpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.datatransfer.Utils;
import com.velus.interpreter.Interpreter;

import java.util.HashMap;

public class PrintFieldAscendingPosition extends InterpreterCommand{
    public PrintFieldAscendingPosition(Interpreter interpreter){
        super(interpreter);
    }
    public void execute(){
        Request rq = new Request("print_field_ascending_position", new HashMap<>());
        if(client.sendRequest(rq)){
            Response resp = client.receive();
            Utils.printResponseMessages(resp);
        }

    }
    @Override
    public String info(){
        return "вывести список рабочих, отсортированный по должности в формате key - position";
    }
}
