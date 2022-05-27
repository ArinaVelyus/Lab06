package com.velus.responseinterpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.lab.Position;
import com.velus.responseinterpreter.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoveAllByPosition extends Command {

    public RemoveAllByPosition(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {
        Position position = (Position) rq.attachments.get("position").get();
        manager.removeAllByPosition(position);
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Выполнено");
        return new Response(Response.Status.OK, messages, new HashMap<>());
    }

}
