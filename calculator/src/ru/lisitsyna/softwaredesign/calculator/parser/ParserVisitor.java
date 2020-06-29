package ru.lisitsyna.softwaredesign.calculator.parser;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import ru.lisitsyna.softwaredesign.calculator.token.*;

import ru.lisitsyna.softwaredesign.calculator.token.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    List<Token> postfixTokens;
    Stack<Token> operations;

    public List<Token> toPostfix(List<Token> tokens) {
        postfixTokens = new ArrayList<>();
        operations = new Stack<>();
        for (Token token : tokens) {
            token.accept(this);
        }
        while (!operations.empty()) {
            postfixTokens.add(operations.pop());
        }
        return postfixTokens;
    }

    @Override
    public void visit(NumberToken token) {
        postfixTokens.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        if (token.getType() == BraceType.LEFT) {
            operations.push(token);
        } else {
            if (operations.empty())
                throw new IllegalStateException("Problem with right brace sequence");
            Token t = operations.pop();
            while (!operations.empty() && t.getClass() != BraceToken.class) {
                postfixTokens.add(t);
                t = operations.pop();
            }
            if (t.getClass() != BraceToken.class) {
                throw new IllegalStateException("Problem with right brace sequence");
            }
        }
    }

    @Override
    public void visit(OperationToken token) {
        if (token.getType() == OperationType.PLUS || token.getType() == OperationType.MINUS) {
            Token t;
            while (!operations.empty() && (t = operations.peek()).getClass() == OperationToken.class) {
                operations.pop();
                postfixTokens.add(t);
            }
            operations.push(token);
        } else {
            Token t;
            while (!operations.empty() && (t = operations.peek()).getClass() == OperationToken.class) {
                OperationToken o = (OperationToken) t;
                if (o.getType() == OperationType.PLUS || o.getType() == OperationType.MINUS) break;
                operations.pop();
                postfixTokens.add(t);
            }
            operations.push(token);
        }
    }
}
