package AbstractSyntaxTree;

import java.util.List;

public class VarInicExt extends DVar {
	public List<Exp> expList;
	
	public VarInicExt(Tipo tipo, String id, List<Exp> expList) {
		super(tipo, id);
		this.expList = expList;
	}
}
