package ru.lisitsyna.softwaredesign.calculator;

import ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import ru.lisitsyna.softwaredesign.calculator.token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(BraceToken token);

    void visit(OperationToken token);
}
