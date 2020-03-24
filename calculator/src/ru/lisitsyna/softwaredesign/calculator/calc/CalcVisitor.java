package java.ru.lisitsyna.softwaredesign.calculator.calc;

import java.ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import java.ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.OperationToken;

public class CalcVisitor implements TokenVisitor {

    @Override
    public void visit(NumberToken token) {

    }

    @Override
    public void visit(BraceToken token) {

    }

    @Override
    public void visit(OperationToken token) {

    }
}
