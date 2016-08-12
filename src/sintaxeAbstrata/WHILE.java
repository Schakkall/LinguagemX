package sintaxeAbstrata;

public class WHILE extends Comando {
	public Exp condicao;
	public Comando comando;
	
	public WHILE(Exp condicao, Comando comando) {
		this.condicao = condicao;
		this.comando  = comando;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitWHILE(this);
	}
}
