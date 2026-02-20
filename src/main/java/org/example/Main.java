package org.example;

import org.example.lexer.Lexer;
import org.example.lexer.TokenInfo;

import java.io.IOException;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = "(~A ∧ B) → C";

        Lexer lexer = new Lexer(new StringReader(input));

        TokenInfo token;

        while ((token = lexer.yylex()) != null) {
            System.out.println(
                    "Tipo: " + token.getType() +
                            " | Lexema: " + token.getLexeme()
            );
        }

    }
}