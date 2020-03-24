package java.ru.lisitsyna.softwaredesign.calculator;

import java.ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(BraceToken token);

    void visit(OperationToken token);
}
