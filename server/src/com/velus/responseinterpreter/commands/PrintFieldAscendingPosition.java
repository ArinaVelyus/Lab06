package com.velus.responseinterpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.lab.Position;
import com.velus.responseinterpreter.Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrintFieldAscendingPosition extends Command {
    public PrintFieldAscendingPosition(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {

        ArrayList<String> messages = new ArrayList<>();

        HashMap<Position, List<String>> res = manager.fieldAscendingPosition();
        for (Position p : res.keySet()) {
            messages.add("------" + p.toString() + "------");
            messages.addAll(res.get(p));
        }
        return new Response(Response.Status.OK, messages, new HashMap<>());

    }

}
