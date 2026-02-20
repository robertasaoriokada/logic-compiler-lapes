package org.example.lexer;

import org.example.enums.Token;

public class TokenInfo {
    private Token type;
    private String lexeme;

    public TokenInfo(Token type, String lexeme) {
            this.type = type;
            this.lexeme = lexeme;
    }

    public Token getType() {
        return type;
    }

    public void setType(Token type) {
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
}


