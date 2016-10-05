package lexico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

%%
%public
%class XLexer
%type Token
%implements iXSymbols
%cup
%line
%column
%unicode

%{
    StringBuilder string = new StringBuilder();

    private Token token(int tipo) {
        return new Token(tipo, yyline+1, yycolumn+1, null);
    }

    private Token token(int tipo, Object valor) {
        return new Token(tipo, yyline+1, yycolumn+1, valor);
    }
    
    public static void enumerarTokens(String fileName){
		try {
			XLexer lex = new XLexer(new FileReader(fileName));
			while (true) {
				try {
					Token t = lex.next_token();
					if (t == null)
						break;
					System.out.println(t.toString());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		  
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

  
  "var"                          { return token(VAR); }
  "bool"                         { return token(BOOL); }
  "cons"                         { return token(CONS); }
  "int"                          { return token(INT); }
  "real"                         { return token(REAL); }
  "if"                           { return token(IF); }
  "then"                         { return token(THEN); }
  "else"                         { return token(ELSE); }
  "while"                        { return token(WHILE); }
  "procedure"                    { return token(PROCEDURE); }
  "function"                     { return token(FUNCTION); }
  
  
  "true"                         { return token(BOOL_LITERAL, true); }
  "false"                        { return token(BOOL_LITERAL, false); }
  
  
  "("                            { return token(LPAREN); }
  ")"                            { return token(RPAREN); }
  "{"                            { return token(LBRACE); }
  "}"                            { return token(RBRACE); }
  "["                            { return token(LBRACK); }
  "]"                            { return token(RBRACK); }
  ";"                            { return token(SEMICOLON); }
  ","                            { return token(COMMA); }
  
  
  ":="                           { return token(ASSIGN); }
  ">"                            { return token(GT); }
  "<"                            { return token(LT); }
  "!"                            { return token(NOT); }
  "="                            { return token(EQ); }
  "+"                            { return token(SUM); }
  "-"                            { return token(SUB); }
  "*"                            { return token(MUL); }
  "/"                            { return token(DIV); }
  "and"                          { return token(AND); }
  "or"                           { return token(OR); }
  "%"                            { return token(MOD); }
  
  
  
  {DecIntegerLiteral}            { return token(INT_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return token(INT_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {FloatLiteral}                 { return token(REAL_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return token(REAL_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return token(REAL_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  
  {Comment}                      { /* ignore */ }

  
  {WhiteSpace}                   { /* ignore */ }

  
  {Identifier}                   { return token(ID, yytext()); }  
}

[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
                                                              
<<EOF>> { return token(EOF); }                                                              