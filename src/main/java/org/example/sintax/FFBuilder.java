package org.example.sintax;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FFBuilder {
    public static Map<Symbol, Set<Symbol>> buildFirst(CFG g) {
        Map<Symbol, Set<Symbol>> first = new HashMap<>();
        for (Symbol t : g.terminalSymbols) {
            Set<Symbol> s = new HashSet<>();
            s.add(t);
            first.put(t, s);
        }

        for (Symbol nt : g.nonTerminalSymbols) {
            first.put(nt, new HashSet<>());
        }

        boolean changed = true;
        while(changed) {
            changed = false;
        
            for (Symbol A: g.nonTerminalSymbols) {
                List<ProductionRule> rulesForA = g.getRulesForNonTerminal(A);
                for (ProductionRule r : rulesForA) {
                    Symbol firstSymbol = r.getRightHandSide().get(0);

                    Set<Symbol> firstOfA = first.get(A);
                    Set<Symbol> firstOfChild = first.get(firstSymbol);

                    int sizeBefore = firstOfA.size();
                    firstOfA.addAll(firstOfChild);
                    
                    if (firstOfA.size() > sizeBefore) {
                        changed = true;
                    }
                }
            }
        }
        return first;
    }
}
