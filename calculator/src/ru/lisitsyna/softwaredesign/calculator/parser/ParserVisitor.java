package java.ru.lisitsyna.softwaredesign.calculator.parser;

import java.rmi.server.Operation;
import java.ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import java.ru.lisitsyna.softwaredesign.calculator.token.*;
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
            Token t = new OperationToken(OperationType.PLUS);
            while (!operations.empty() && (t = operations.pop()).getClass() != BraceToken.class) {
                postfixTokens.add(t);
            }
            if (t.getClass() != BraceToken.class) {
                throw new IllegalStateException("");
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
