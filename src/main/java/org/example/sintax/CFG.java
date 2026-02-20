package org.example.sintax;

import org.example.enums.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CFG {
    public Set<Symbol> nonTerminalSymbols;
    public Set<Symbol> terminalSymbols;
    public List<ProductionRule> rules;
    public Symbol startSymbol;

    public CFG(Set<Symbol> nonTerminals, Set<Symbol> terminals, List<ProductionRule> rules, Symbol startSymbol) {
        this.nonTerminalSymbols = nonTerminals;
        this.terminalSymbols = terminals;
        this.rules = rules;
        this.startSymbol = startSymbol;
    }

    Symbol expr = new Symbol("expr", false);
    Symbol term = new Symbol("term", false);
    Symbol factor = new Symbol("factor", false);
    Symbol product = new Symbol("product", false);
    Symbol alpha = new Symbol("alpha", false);
    Symbol finale = new Symbol("finale", false);

    Symbol id = new Symbol(Token.ID.name(), true);
    Symbol rParen = new Symbol(Token.RPAREN.name(), true);
    Symbol lParen = new Symbol(Token.LPAREN.name(), true);
    Symbol negative = new Symbol(Token.NEGATIVE.name(), true);

    Symbol and = new Symbol(Token.AND.name(), true);
    Symbol or = new Symbol(Token.OR.name(), true);
    Symbol cond = new Symbol(Token.COND.name(), true);
    Symbol biCond = new Symbol(Token.BICOND.name(), true);
    Symbol excOr = new Symbol(Token.EXCOR.name(), true);

    // Regras (cada alternativa é um ProductionRule separado):

    // expr (↔) — associatividade à esquerda
    ProductionRule r1a = new ProductionRule(expr, Arrays.asList(term));
    ProductionRule r1b = new ProductionRule(expr, Arrays.asList(expr, biCond, term));

    // term (→) — associatividade à direita
    ProductionRule r2a = new ProductionRule(term, Arrays.asList(factor));
    ProductionRule r2b = new ProductionRule(term, Arrays.asList(factor, cond, term));

    // factor (⊕) — associatividade à esquerda
    ProductionRule r3a = new ProductionRule(factor, Arrays.asList(product));
    ProductionRule r3b = new ProductionRule(factor, Arrays.asList(factor, excOr, product));

    // product (∨) — associatividade à esquerda
    ProductionRule r4a = new ProductionRule(product, Arrays.asList(alpha));
    ProductionRule r4b = new ProductionRule(product, Arrays.asList(product, or, alpha));

    // alpha (∧) — associatividade à esquerda
    ProductionRule r5a = new ProductionRule(alpha, Arrays.asList(finale));
    ProductionRule r5b = new ProductionRule(alpha, Arrays.asList(alpha, and, finale));

    // finale — negação e primários
    ProductionRule r6  = new ProductionRule(finale, Arrays.asList(negative, finale)); // ¬F
    ProductionRule r7  = new ProductionRule(finale, Arrays.asList(lParen, expr, rParen)); // (E)
    ProductionRule r8  = new ProductionRule(finale, Arrays.asList(id)); // p, q, r...

}
