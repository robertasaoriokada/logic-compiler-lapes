package org.example;

import org.example.lexer.Lexer;
import org.example.lexer.TokenInfo;
import org.example.sintax.CFG;
import org.example.sintax.FFBuilder;
import org.example.sintax.GrammarBuilder;
import org.example.sintax.Symbol;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;

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


        CFG grammar = new CFG();
        grammar = GrammarBuilder.buildGrammar(grammar);
        Map<Symbol, Set<Symbol>> primeiro = FFBuilder.buildFirst(grammar);

        // Vamos imprimir para conferir
        for (Symbol nt : grammar.nonTerminalSymbols) {
            System.out.print("FIRST(" + nt.getName() + ") = { ");
            for (Symbol s : primeiro.get(nt)) {
                System.out.print(s.getName() + " ");
            }
            System.out.println("}");
        }

    }
}