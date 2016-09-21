package lexico;

%%
%public
%class XLexer
%type Token
%line
%column
%unicode

%{
    StringBuilder string = new StringBuilder();

    private Token token(TokenKind tipo) {
        return new Token(tipo, yyline+1, yycolumn+1, null);
    }

    private Token token(TokenKind tipo, Object valor) {
        return new Token(tipo, yyline+1, yycolumn+1, valor);
    }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]


Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

Identifier = [:jletter:][:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+


%%

<YYINITIAL> {

  
  "var"                          { return token(TokenKind.VAR); }
  "bool"                         { return token(TokenKind.BOOL); }
  "cons"                         { return token(TokenKind.CONS); }
  "int"                          { return token(TokenKind.INT); }
  "real"                         { return token(TokenKind.REAL); }
  "if"                           { return token(TokenKind.IF); }
  "then"                         { return token(TokenKind.THEN); }
  "else"                         { return token(TokenKind.ELSE); }
  "while"                        { return token(TokenKind.WHILE); }
  "procedure"                    { return token(TokenKind.PROCEDURE); }
  "function"                     { return token(TokenKind.FUNCTION); }
  
  
  "true"                         { return token(TokenKind.BOOLEAN_LITERAL, true); }
  "false"                        { return token(TokenKind.BOOLEAN_LITERAL, false); }
  
  
  "("                            { return token(TokenKind.LPAREN); }
  ")"                            { return token(TokenKind.RPAREN); }
  "{"                            { return token(TokenKind.LBRACE); }
  "}"                            { return token(TokenKind.RBRACE); }
  "["                            { return token(TokenKind.LBRACK); }
  "]"                            { return token(TokenKind.RBRACK); }
  ";"                            { return token(TokenKind.SEMICOLON); }
  ","                            { return token(TokenKind.COMMA); }
  "."                            { return token(TokenKind.DOT); }
  
  
  ":="                           { return token(TokenKind.ASSIGN); }
  ">"                            { return token(TokenKind.GT); }
  "<"                            { return token(TokenKind.LT); }
  "!"                            { return token(TokenKind.NOT); }
  "="                            { return token(TokenKind.EQ); }
  "+"                            { return token(TokenKind.SUM); }
  "-"                            { return token(TokenKind.SUB); }
  "*"                            { return token(TokenKind.MUL); }
  "/"                            { return token(TokenKind.DIV); }
  "and"                          { return token(TokenKind.AND); }
  "or"                           { return token(TokenKind.OR); }
  "%"                            { return token(TokenKind.MOD); }
  
  
  "-2147483648"                  { return token(TokenKind.INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return token(TokenKind.INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return token(TokenKind.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {FloatLiteral}                 { return token(TokenKind.REAL_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return token(TokenKind.REAL_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return token(TokenKind.REAL_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  
  {Comment}                      { /* ignore */ }

  
  {WhiteSpace}                   { /* ignore */ }

  
  {Identifier}                   { return token(TokenKind.ID, yytext()); }  
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }