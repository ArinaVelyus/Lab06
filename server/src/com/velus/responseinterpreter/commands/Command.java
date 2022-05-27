package com.velus.responseinterpreter.commands;

import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.responseinterpreter.Interpreter;
import com.velus.workersmanager.WorkersManager;

public abstract class Command {
    Interpreter interpreter;
    WorkersManager manager;

    public Command(Interpreter interpreter) {
        this.interpreter = interpreter;
        this.manager = this.interpreter.getManager();
    }

    public abstract Response execute(Request rq);

}
