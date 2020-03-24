package java.ru.lisitsyna.softwaredesign.calculator;

import java.ru.lisitsyna.softwaredesign.calculator.parser.ParserVisitor;
import java.ru.lisitsyna.softwaredesign.calculator.print.PrintVisitor;
import java.ru.lisitsyna.softwaredesign.calculator.token.Tokenizer;

public class App {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer("3 + 4");
        PrintVisitor printVisitor = new PrintVisitor();
        ParserVisitor parserVisitor = new ParserVisitor();
        printVisitor.print(parserVisitor.toPostfix(tokenizer.parse()));
    }
}
