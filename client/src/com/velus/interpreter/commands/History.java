package com.velus.interpreter.commands;

import com.velus.interpreter.Interpreter;

public class History extends InterpreterCommand{
    public History(Interpreter interpreter){
        super(interpreter);
    }
    public void execute(){

        outputManager.println(this.interpreter.getHistory().toString());
    }
    public String info(){
        return "вывести последние 6 команд к интерпретатору";
    }
}
