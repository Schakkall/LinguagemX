package sintaxeAbstrata;

import java.util.List;

public class Funcao extends Dec {
	public String id;
	public List<Parametro> params;
	public Exp exp;
	public Tipo tipo;
	
	public Funcao(Tipo tipo, String id, List<Parametro> params, Exp exp){
		this.tipo = tipo;
		this.id = id;
		this.params = params;
		this.exp = exp;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitFuncao(this);
	}
}
