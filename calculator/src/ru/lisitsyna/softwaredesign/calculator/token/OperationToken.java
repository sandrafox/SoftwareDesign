package ru.lisitsyna.softwaredesign.calculator.token;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;

public class OperationToken implements Token {
    private OperationType type;

    public OperationToken(OperationType type) {
        this.type = type;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public OperationType getType() {
        return type;
    }
}
