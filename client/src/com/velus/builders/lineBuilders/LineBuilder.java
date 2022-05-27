package com.velus.builders.lineBuilders;

import com.velus.builders.Builder;
import com.velus.io.InputManager;
import com.velus.io.OutputManager;

public abstract class LineBuilder<T extends Object> extends Builder<T> {
    InputManager inputManager;
    OutputManager outputManager;
    public LineBuilder(InputManager inputManager, OutputManager outputManager){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }


}
