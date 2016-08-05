package AbstractSyntaxTree;

public class ComandoWhile extends Comando {
	public Exp condicao;
	public Comando comando;
	
	public ComandoWhile(Exp condicao, Comando comando) {
		this.condicao = condicao;
		this.comando  = comando;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitComandoWhile(this);
	}
}
