package sintaxeAbstrata;

public class DV extends DVarConsCom {
	public DVar dvar;
	
	public DV(DVar dvar){
		this.dvar = dvar;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitDV(this);
	}
}
