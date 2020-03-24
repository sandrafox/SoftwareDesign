package java.ru.lisitsyna.softwaredesign.calculator.token;

public class Number extends TokenizerState {
    private StringBuilder sb = new StringBuilder();

    public Number(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void parseSymbol(char c) {
        if (Character.isDigit(c)) {
            sb.append(c);
        } else {
            tokenizer.addToken(new NumberToken(Integer.getInteger(sb.toString())));
            TokenizerState start = new Start(tokenizer);
            tokenizer.changeState(start);
            parseSymbol(c);
        }
    }
}
