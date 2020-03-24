package java.ru.lisitsyna.softwaredesign.calculator.token;

import java.ru.lisitsyna.softwaredesign.calculator.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
