package ru.lisitsyna.softwaredesign.calculator.token;

public enum OperationType {
    PLUS, MINUS, MUL, DIV;

    @Override
    public String toString() {
        switch (this) {
            case DIV: return "DIV";
            case MUL: return "MUL";
            case MINUS: return "MINUS";
            case PLUS: return "PLUS";
        }
        return "";
    }
}
