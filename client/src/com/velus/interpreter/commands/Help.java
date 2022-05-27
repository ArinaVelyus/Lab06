package com.velus.interpreter.commands;

import com.velus.interpreter.Interpreter;

public class Help extends InterpreterCommand{
    public Help(Interpreter interpreter){
        super(interpreter);
    }
    public void execute(){
        for(String key: this.interpreter.getExecutors().keySet()){
            outputManager.println(key + ": " + this.interpreter.getExecutors().get(key).info());
        }

    }
    public String info(){
        return "вывести справку по доступным командам";
    }
}
