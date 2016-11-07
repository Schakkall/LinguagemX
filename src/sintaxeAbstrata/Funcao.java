package sintaxeAbstrata;

import java.util.List;

public class Funcao extends Dec implements ISubrotina {
	public String id;
	public List<Parametro> params;
	public Exp exp;
	public Tipo tipo;
	private int frameSize = 0; 
	
	public Funcao(Tipo tipo, String id, List<Parametro> params, Exp exp){
		this.tipo = tipo;
		this.id = id;
		this.params = params;
		this.exp = exp;
	}

	public Object accept(XVisitor visitor){
		return visitor.visitFuncao(this);
	}
	
	public int frameSize() {
		return this.frameSize;
	}
	
	public void setFrameSize(int size) {
		this.frameSize = size;
	}

	public String getId() {
		return this.id;
	}
}
