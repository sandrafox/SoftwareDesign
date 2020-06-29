package ru.lisitsyna.softwaredesign.calculator.print;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import ru.lisitsyna.softwaredesign.calculator.token.OperationToken;
import ru.lisitsyna.softwaredesign.calculator.token.Token;

import java.util.List;

public class PrintVisitor implements TokenVisitor {
    public void print(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        System.out.println();
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
