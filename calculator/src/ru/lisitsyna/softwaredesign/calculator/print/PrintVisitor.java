package java.ru.lisitsyna.softwaredesign.calculator.print;

import java.ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import java.ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.OperationToken;
import java.ru.lisitsyna.softwaredesign.calculator.token.Token;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintVisitor implements TokenVisitor {
    public void print(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
    }

    @Override
    public void visit(NumberToken token) {
        System.out.print(token.getNumber() + " ");
    }

    @Override
    public void visit(BraceToken token) {
        System.out.print(token.getType().toString() + " ");
    }

    @Override
    public void visit(OperationToken token) {
        System.out.print(token.getType().toString() + " ");
    }
}
