package ru.lisitsyna.softwaredesign.calculator.token;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;

public class BraceToken implements Token {
    private BraceType type;

    public BraceToken(BraceType type) {
        this.type = type;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public BraceType getType() {
        return type;
    }
}
