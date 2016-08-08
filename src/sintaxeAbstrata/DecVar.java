package sintaxeAbstrata;

public class DecVar extends Dec {
	public DVar dvar;
	
	public DecVar(DVar dvar) {
		this.dvar = dvar;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitDecVar(this);
	}
}


