package sintaxeAbstrata;

public class VarExp extends Exp {
	public Var var;
	
	public VarExp(Var var) {
		this.var = var;
	}
	
	public Object accept(XVisitor visitor) {
		return visitor.visitVarExp(this);
	}
}
