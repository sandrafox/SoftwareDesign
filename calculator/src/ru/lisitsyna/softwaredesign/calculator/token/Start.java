package ru.lisitsyna.softwaredesign.calculator.token;

public class Start extends TokenizerState {
    public Start(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parseSymbol(char c) {
        switch (c) {
            case '(':
                tokenizer.addToken(new BraceToken(BraceType.LEFT));
                break;
            case ')':
                tokenizer.addToken(new BraceToken(BraceType.RIGHT));
                break;
            case '+':
                tokenizer.addToken(new OperationToken(OperationType.PLUS));
                break;
            case '-':
                tokenizer.addToken(new OperationToken(OperationType.MINUS));
                break;
            case '*':
                tokenizer.addToken(new OperationToken(OperationType.MUL));
                break;
            case '/':
                tokenizer.addToken(new OperationToken(OperationType.DIV));
                break;
            case ' ':
                break;
            default:
                if (Character.isDigit(c)) {
                    TokenizerState number = new Number(tokenizer);
                    tokenizer.changeState(number);
                    number.parseSymbol(c);
                } else if (!Character.isWhitespace(c) && c != '$') {
                    TokenizerState error = new Error(tokenizer);
                    tokenizer.changeState(error);
                    error.parseSymbol(c);
                }
        }
    }
}
