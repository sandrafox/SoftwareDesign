package ru.lisitsyna.softwaredesign.calculator.token;

public enum BraceType {
    LEFT, RIGHT;

    @Override
    public String toString() {
        switch (this) {
            case RIGHT: return "RIGHT";
            case LEFT: return "LEFT";
        }
        return "";
    }
}
