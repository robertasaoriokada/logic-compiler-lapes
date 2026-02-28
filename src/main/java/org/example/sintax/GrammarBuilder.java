package org.example.sintax;

import java.util.Arrays;

import org.example.enums.Token;

public class GrammarBuilder {
    public static CFG buildGrammar(CFG grammar) {
        Symbol expr = new Symbol("expr", false);
        Symbol biImplyExpr = new Symbol("biImplyExpr", false);
        Symbol implyExpr = new Symbol("implyExpr", false);
        Symbol xorExpr = new Symbol("xorExpr", false);
        Symbol orExpr = new Symbol("orExpr", false);
        Symbol andExpr = new Symbol("andExpr", false);
        Symbol notExpr = new Symbol("notExpr", false);
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
        ProductionRule r = new ProductionRule(expr, Arrays.asList(biImplyExpr));

        // expr (↔) — associatividade à direita - tanto faz a ordem pois o valor lógico será o mesmo
        ProductionRule r1a = new ProductionRule(biImplyExpr, Arrays.asList(implyExpr));
        ProductionRule r1b = new ProductionRule(biImplyExpr, Arrays.asList(implyExpr, biCond, biImplyExpr));

        // term (→) — associatividade à direita
        ProductionRule r2a = new ProductionRule(implyExpr, Arrays.asList(xorExpr));
        ProductionRule r2b = new ProductionRule(implyExpr, Arrays.asList(xorExpr, cond, implyExpr));

        // factor (⊕) — associatividade à esquerda
        ProductionRule r3a = new ProductionRule(xorExpr, Arrays.asList(orExpr));    
        ProductionRule r3b = new ProductionRule(xorExpr, Arrays.asList(orExpr, excOr, xorExpr));

        // product (∨) — associatividade à esquerda
        ProductionRule r4a = new ProductionRule(orExpr, Arrays.asList(andExpr));
        ProductionRule r4b = new ProductionRule(orExpr, Arrays.asList(andExpr, or, orExpr));

        // alpha (∧) — associatividade à esquerda
        ProductionRule r5a = new ProductionRule(andExpr, Arrays.asList(notExpr));
        ProductionRule r5b = new ProductionRule(andExpr, Arrays.asList(notExpr, and, andExpr));

        // finale — negação e primários
        ProductionRule r6a  = new ProductionRule(notExpr, Arrays.asList(negative, notExpr)); // ¬F
        ProductionRule r6b  = new ProductionRule(notExpr, Arrays.asList(finale)); // ¬F

        ProductionRule r7  = new ProductionRule(finale, Arrays.asList(lParen, expr, rParen)); // (E)
        ProductionRule r8  = new ProductionRule(finale, Arrays.asList(id)); // p, q, r...

        grammar.nonTerminalSymbols.add(expr);
        grammar.nonTerminalSymbols.add(biImplyExpr);
        grammar.nonTerminalSymbols.add(implyExpr);
        grammar.nonTerminalSymbols.add(xorExpr);
        grammar.nonTerminalSymbols.add(orExpr);
        grammar.nonTerminalSymbols.add(andExpr);
        grammar.nonTerminalSymbols.add(notExpr);
        grammar.nonTerminalSymbols.add(finale);

        grammar.terminalSymbols.add(id);
        grammar.terminalSymbols.add(rParen);
        grammar.terminalSymbols.add(lParen);
        grammar.terminalSymbols.add(negative);
        grammar.terminalSymbols.add(and);
        grammar.terminalSymbols.add(or);
        grammar.terminalSymbols.add(cond);
        grammar.terminalSymbols.add(biCond);
        grammar.terminalSymbols.add(excOr);

        grammar.rules.add(r);
        grammar.rules.add(r1a);
        grammar.rules.add(r1b);
        grammar.rules.add(r2a);
        grammar.rules.add(r2b);
        grammar.rules.add(r3a);
        grammar.rules.add(r3b);
        grammar.rules.add(r4a);
        grammar.rules.add(r4b);
        grammar.rules.add(r5a);
        grammar.rules.add(r5b);
        grammar.rules.add(r6a);
        grammar.rules.add(r6b);
        grammar.rules.add(r7);
        grammar.rules.add(r8);

        return grammar;
    }
}
