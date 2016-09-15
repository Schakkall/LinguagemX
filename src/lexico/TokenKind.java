package lexico;

public enum TokenKind {
	VAR,
	CONS,
	IF,
	THEN,
	ELSE,
	WHILE,
	PROCEDURE,
	FUNCTION,
	
	REAL,
	BOOL,
	INT,
	
	BOOL_LITERAL,
	INT_LITERAL,
	REAL_LITERAL,
	
	LPAREN,
	RPAREN,
	LBRACE,
	RBRACE,
	LBRACK,
	RBRACK,
	SEMICOLON,
	COMMA,
	DOT,
	
	ASSIGN,
	
	GT,
	LT,
	EQ,
	
	SUM,
	SUB,
	DIV,
	MUL,
	MOD,
	
	NOT,
	AND,
	OR,
	
	ID,	
	EOF,
	
	NONE;
	
	public String toString(){
		return (this == NONE) ? "error" : super.toString();
	}
	
}
