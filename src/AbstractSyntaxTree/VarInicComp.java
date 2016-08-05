package AbstractSyntaxTree;

import java.util.List;

public class VarInicComp extends DVar {
	public List<String> idList;
	public Exp exp;
	
	public VarInicComp(Tipo tipo, String id, List<String> idList, Exp exp) {
		super(tipo, id);
		this.exp = exp;
		this.idList = idList;
	}
}
