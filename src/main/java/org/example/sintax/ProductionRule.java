package org.example.sintax;

import java.util.List;

public class ProductionRule {
    private Symbol leftHandSide;
    private List<Symbol> rightHandSide;

    public ProductionRule(Symbol lhs, List<Symbol> rhs) {
        this.leftHandSide = lhs;
        this.rightHandSide = rhs;
    }

    public Symbol getLeftHandSide() {
        return leftHandSide;
    }

    public void setLeftHandSide(Symbol leftHandSide) {
        this.leftHandSide = leftHandSide;
    }

    public List<Symbol> getRightHandSide() {
        return rightHandSide;
    }

    public void setRightHandSide(List<Symbol> rightHandSide) {
        this.rightHandSide = rightHandSide;
    }
}
