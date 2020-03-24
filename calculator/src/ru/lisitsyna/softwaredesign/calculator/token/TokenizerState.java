package java.ru.lisitsyna.softwaredesign.calculator.token;

public abstract class TokenizerState {
    protected Tokenizer tokenizer;

    public TokenizerState(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void parseSymbol(char c);
}
