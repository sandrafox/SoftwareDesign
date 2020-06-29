package ru.lisitsyna.softwaredesign.calculator.token;

import ru.lisitsyna.softwaredesign.calculator.TokenVisitor;

public interface Token {
    void accept(TokenVisitor visitor);
}
