package sintaxeAbstrata;

public class DecCons extends Dec {
	public DVar dvar;
	
	public DecCons(DVar dvar) {
		this.dvar = dvar;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitDecCons(this);
	}
}
