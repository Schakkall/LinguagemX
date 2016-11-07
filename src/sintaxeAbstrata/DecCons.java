package sintaxeAbstrata;

public class DecCons extends Dec implements IDeclaracao{
	public DCons dcons;
	
	public DecCons(DCons dcons) {
		this.dcons = dcons;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitDecCons(this);
	}
}
