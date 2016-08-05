package AbstractSyntaxTree;

public class ComandoAtribuicao extends Comando {
	public Var var;
	public Exp exp;
	
	public ComandoAtribuicao(Var var, Exp exp) {
		this.var = var;
		this.exp = exp;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitComandoAtribuicao(this);
	}
}
