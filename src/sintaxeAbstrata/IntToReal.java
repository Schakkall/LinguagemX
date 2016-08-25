package sintaxeAbstrata;

public class IntToReal extends Exp {
	public Exp exp;

	public IntToReal(Exp exp){
		this.exp = exp;
	}
	
	public Object accept(XVisitor visitor) {
		return visitor.visitIntToReal(this);
	}
	
}
