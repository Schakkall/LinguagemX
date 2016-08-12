package sintaxeAbstrata;

public class ASSIGN extends Comando {
	public Var var;
	public Exp exp;
	
	public ASSIGN(Var var, Exp exp) {
		this.var = var;
		this.exp = exp;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitASSIGN(this);
	}
}
