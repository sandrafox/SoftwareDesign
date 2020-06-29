package ru.lisitsyna.softwaredesign.calculator.token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private TokenizerState state;
    private String input;
    private List<Token> tokens;

    public Tokenizer(String input) {
        this.state = new Start(this);
        this.input = input;
    }

    public void changeState(TokenizerState state) {
        this.state = state;
    }

    public List<Token> parse() {
        tokens = new ArrayList<>();
        int i;
        for (i = 0; i < input.length() && state.getClass() != Error.class; i++) {
            parseSymbol(input.charAt(i));
        }
        if (state.getClass() == Error.class) {
            throw new IllegalStateException("Sorry, but we have incorrect symbol at position " + (i + 1));
        }
        parseSymbol('$');
        return tokens;
    }

    private void parseSymbol(char c) {
        state.parseSymbol(c);
    }

    public void addToken(Token token) {
        tokens.add(token);
    }
}
