package grapher.ui;

import java.awt.Color;

import grapher.fc.Function;

public class ColoredFunction {
    Color color;
    Function function;

    public ColoredFunction(Color color, Function function) {
        this.color = color;
        this.function = function;
    }

    public Color getColor() {
        return color;
    }

    public Function getFunction() {
        return function;
    }
}
