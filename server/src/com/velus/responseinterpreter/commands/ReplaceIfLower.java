package com.velus.responseinterpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.responseinterpreter.Interpreter;
import com.velus.workersmanager.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;


public class ReplaceIfLower extends Command {
    public ReplaceIfLower(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {
        ArrayList<String> messages = new ArrayList<>();
        String key = (String) rq.attachments.get("key").get();
        Float salary = (Float) rq.attachments.get("salary").get();
        try {
            if (manager.replaceIfLower(key, salary)) {
                messages.add("Элемент обновлён");
            } else {
                messages.add("Элемент не обновлён");
            }
        } catch (NotFoundException ex) {
            messages.add("Элемент не найден");
        }

        return new Response(Response.Status.OK, messages, new HashMap<>());


    }
}