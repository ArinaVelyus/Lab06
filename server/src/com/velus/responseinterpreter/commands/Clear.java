package com.velus.responseinterpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.responseinterpreter.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class Clear extends Command {
    public Clear(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {
        manager.clear();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Коллекция очищена");
        return new Response(Response.Status.OK, messages, new HashMap<>());


    }
}
