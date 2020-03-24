package java.ru.lisitsyna.softwaredesign.calculator.token;

public class Error extends TokenizerState {
    public Error(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parseSymbol(char c) {
        return;
    }
}
