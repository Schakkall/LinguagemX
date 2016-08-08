package sintaxeAbstrata;

import java.util.List;

public class ConsComp extends DCons {
	public List<String> idList;
	public Exp exp;
		
	public ConsComp(Tipo tipo, String id, List<String> idList, Exp exp) {
		super(tipo, id);
		this.idList = idList;
		this.exp = exp;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitConsComp(this);
	}
}
