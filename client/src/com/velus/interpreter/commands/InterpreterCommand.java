package com.velus.interpreter.commands;

import com.velus.Client;
import com.velus.interpreter.Interpreter;
import com.velus.io.InputManager;
import com.velus.io.OutputManager;

public abstract class InterpreterCommand extends Command{
    Interpreter interpreter;
    InputManager inputManager;
    OutputManager outputManager;
    Client client;
    public InterpreterCommand(Interpreter interpreter){

        this.interpreter = interpreter;
        this.inputManager = interpreter.getInputManager();
        this.outputManager = interpreter.getOutputManager();
        this.client = interpreter.getClient();
    }
    public String info(){
        return "";
    };
}
