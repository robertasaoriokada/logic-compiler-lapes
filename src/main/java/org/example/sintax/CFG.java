package org.example.sintax;

import org.example.enums.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CFG {
    public Set<Symbol> nonTerminalSymbols;
    public Set<Symbol> terminalSymbols;
    public List<ProductionRule> rules;
    public Symbol startSymbol;

    public CFG() {
        nonTerminalSymbols = new HashSet<>();
        terminalSymbols = new HashSet<>();
        rules = new ArrayList<>();
    }

    public List<ProductionRule> getRulesForNonTerminal(Symbol nonTerminal) {
        List<ProductionRule> rulesForSymbol = new ArrayList<>();
        for (ProductionRule r : rules) {
            if (r.getLeftHandSide().equals(nonTerminal)) {
                rulesForSymbol.add(r);
            }
        }
        return rulesForSymbol;
    }

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

}