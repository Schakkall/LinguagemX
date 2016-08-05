package AbstractSyntaxTree;

public class Indexada extends Var {
	public Var var;
	public Exp index;
	
	public Indexada(Var var, Exp index) {
		this.var = var;
		this.index = index;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitIndexada(this);
	}
}
