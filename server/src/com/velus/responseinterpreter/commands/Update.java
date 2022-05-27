package com.velus.responseinterpreter.commands;


import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.lab.Worker;
import com.velus.responseinterpreter.Interpreter;
import com.velus.workersmanager.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;


public class Update extends Command {
    public Update(Interpreter interpreter) {
        super(interpreter);
    }

    public Response execute(Request rq) {
        ArrayList<String> messages = new ArrayList<>();
        Long id = (Long) rq.attachments.get("id").get();
        Worker w = (Worker) rq.attachments.get("worker").get();
        try {
            String key = manager.update(id, w);
            messages.add("Элемент [" + key + "] успешно обновлён");
        } catch (NotFoundException ex) {
            messages.add("Элемент не найден");
        }


        return new Response(Response.Status.OK, messages, new HashMap<>());
    }
}