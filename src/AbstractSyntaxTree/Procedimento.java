package AbstractSyntaxTree;

import java.util.List;

public class Procedimento extends Dec {
	public String id;
	public List<Parametro> params;
	public Comando com;
	
	public Procedimento(String id, List<Parametro> params, Comando com){
		this.id = id;
		this.params = params;
		this.com = com;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitProcedimento(this);
	}
}
