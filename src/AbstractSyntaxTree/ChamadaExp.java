package AbstractSyntaxTree;

import java.util.List;

public class ChamadaExp extends Exp {
	public String id;
	public List<Exp> expList;
	
	public ChamadaExp(String id, List<Exp> expList){
		this.id = id;
		this.expList = expList;
	}	
	
	public Object accept(XVisitor visitor) {
		return visitor.visitChamadaExp(this);
	}
}
