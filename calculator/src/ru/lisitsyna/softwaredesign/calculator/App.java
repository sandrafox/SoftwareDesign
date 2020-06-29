package ru.lisitsyna.softwaredesign.calculator;

import ru.lisitsyna.softwaredesign.calculator.calc.CalcVisitor;
import ru.lisitsyna.softwaredesign.calculator.parser.ParserVisitor;
import ru.lisitsyna.softwaredesign.calculator.print.PrintVisitor;
import ru.lisitsyna.softwaredesign.calculator.token.Token;
import ru.lisitsyna.softwaredesign.calculator.token.Tokenizer;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            sb.append(in.nextLine());
        }
        Tokenizer tokenizer = new Tokenizer(sb.toString());
        PrintVisitor printVisitor = new PrintVisitor();
        CalcVisitor calcVisitor = new CalcVisitor();
        ParserVisitor parserVisitor = new ParserVisitor();
        List<Token> postfix = parserVisitor.toPostfix(tokenizer.parse());
        printVisitor.print(postfix);
        System.out.print(calcVisitor.calc(postfix));
    }
}
