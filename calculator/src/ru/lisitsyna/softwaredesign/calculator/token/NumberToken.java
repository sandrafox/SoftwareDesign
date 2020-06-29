package ru.lisitsyna.softwaredesign.calculator.token;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;

public class NumberToken implements Token {
    private int number;

    public NumberToken(int number) {
        this.number = number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public int getNumber() {
        return number;
    }
}
