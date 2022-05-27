package com.velus.responseinterpreter.commands;

import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.responseinterpreter.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;


public class RemoveGreater extends Command {
    public RemoveGreater(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {
        Float salary = (Float) rq.attachments.get("salary").get();
        ArrayList<String> messages = new ArrayList<>();
        manager.removeGreater(salary);
        messages.add("Выполнено");

        return new Response(Response.Status.OK, messages, new HashMap<>());


    }

}

