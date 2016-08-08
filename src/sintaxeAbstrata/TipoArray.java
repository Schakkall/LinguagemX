package sintaxeAbstrata;

import java.util.List;

public class TipoArray extends TipoBase {
	public List<Exp> expList;
	
	public TipoArray(TBase base, List<Exp> expList) {
		super(base);
		this.expList = expList;
	}
}
