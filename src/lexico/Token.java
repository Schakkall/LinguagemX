package lexico;

public class Token {
    public TokenKind tipo;
    public SourcePosition pos;
    public Object conteudo;	
	
    public Token(TokenKind tipo, int linha, int coluna, Object conteudo) {
        this.tipo = tipo;
        this.pos = new SourcePosition(linha, coluna);
        this.conteudo = conteudo;
    }

    public String toString() {
        return "Token: " + (this.conteudo == null ? this.tipo  : this.conteudo ) + " , " + "Tipo: " + this.tipo + " , " + "Pos: " +this.pos;
    }    

}
