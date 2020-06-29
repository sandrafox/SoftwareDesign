package ru.lisitsyna.softwaredesign.calculator.calc;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;
import ru.lisitsyna.softwaredesign.calculator.token.BraceToken;
import ru.lisitsyna.softwaredesign.calculator.token.NumberToken;
import ru.lisitsyna.softwaredesign.calculator.token.OperationToken;
import ru.lisitsyna.softwaredesign.calculator.token.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private int result;
    Stack<Integer> operands;

    public int calc(List<Token> tokens) {
        result = 0;
        operands = new Stack<>();
        for (Token token : tokens) {
            token.accept(this);
        }
        return operands.pop();
    }

    @Override
    public void visit(NumberToken token) {
        operands.push(token.getNumber());
    }

    @Override
    public void visit(BraceToken token) {
        throw new IllegalStateException("Problem with right brace sequence");
    }

    @Override
    public void visit(OperationToken token) {
        if (operands.empty()) throw new IllegalStateException("Problem with numbers");
        int op1 = operands.pop();
        if (operands.empty()) throw new IllegalStateException("Problem with numbers");
        int op2 = operands.pop();
        switch (token.getType()) {
            case PLUS:
                operands.push(op1 + op2);
                break;
            case MINUS:
                operands.push(op2 - op1);
                break;
            case MUL:
                operands.push(op2 * op1);
                break;
            case DIV:
                operands.push(op2 / op1);
        }
    }
}
