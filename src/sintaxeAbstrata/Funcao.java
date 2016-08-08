package sintaxeAbstrata;

import java.util.List;

public class Funcao extends Dec {
	public String id;
	public List<Parametro> params;
	public Exp exp;
	
	public Funcao(String id, List<Parametro> params, Exp exp){
		this.id = id;
		this.params = params;
		this.exp = exp;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitFuncao(this);
	}
}
