package sintaxeAbstrata;

import java.util.List;

public class ChamadaExp extends Exp {
	public String id;
	public List<Exp> expLst;
	
	public ChamadaExp(String id, List<Exp> expLst){
		this.id = id;
		this.expLst = expLst;
	}	
	
	public Object accept(XVisitor visitor) {
		return visitor.visitChamadaExp(this);
	}
}
