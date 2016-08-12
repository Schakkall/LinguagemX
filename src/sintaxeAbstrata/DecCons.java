package sintaxeAbstrata;

public class DecCons extends Dec {
	public DCons dcons;
	
	public DecCons(DCons dcons) {
		this.dcons = dcons;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitDecCons(this);
	}
}
