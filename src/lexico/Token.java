package lexico;

import java_cup.runtime.Symbol;

public class Token extends Symbol{
    public int tipo;
    public SourcePosition pos;
    public Object conteudo;	
	
    public String nome(){
    	return iXSymbols.terminalNames[this.tipo];
    }
    
    public Token(int tipo, int linha, int coluna, Object conteudo) {
    	super(tipo, conteudo);
        this.tipo = tipo;
        this.pos = new SourcePosition(linha, coluna);
        this.conteudo = conteudo;
    }

    public String toString() {
        return "Token: " + (this.conteudo == null ? this.nome()  : this.conteudo ) + " , " + "Tipo: " + this.nome() + " , " + "Pos: " +this.pos;
    }    

}
