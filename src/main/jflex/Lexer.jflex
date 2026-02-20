%%
%public
%class Lexer
%unicode
%type TokenInfo

%{
package org.example.lexer;

import org.example.enums.Token;
%}

LETTER = [a-zA-Z]
DIGIT  = [0-9]
ID     = {LETTER}({LETTER}|{DIGIT})*

%%

"(" { return new TokenInfo(Token.LPAREN, yytext()); }
")" { return new TokenInfo(Token.RPAREN, yytext()); }

"~" { return new TokenInfo(Token.NEGATIVE, yytext()); }
"\u00AC" { return new TokenInfo(Token.NEGATIVE, yytext()); }
"\u2227" { return new TokenInfo(Token.AND, yytext()); }
"\u2228" { return new TokenInfo(Token.OR, yytext()); }
"\u2192" { return new TokenInfo(Token.COND, yytext()); }
"\u2194" { return new TokenInfo(Token.BICOND, yytext()); }
"\u2295" { return new TokenInfo(Token.EXCOR, yytext()); }

{ID} { return new TokenInfo(Token.ID, yytext()); }

[ \t\r\n]+ { }

. { throw new RuntimeException("Símbolo inválido: " + yytext()); }