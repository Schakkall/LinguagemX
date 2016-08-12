package sintaxeAbstrata;

import java.util.List;

public class CHAMADA extends Comando {
	public String id;
	public List<Exp> expLst;
	
	public CHAMADA(String id, List<Exp> expLst){
		this.id = id;
		this.expLst = expLst;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitCHAMADA(this);
	}
}
