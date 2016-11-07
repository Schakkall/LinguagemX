package sintaxeAbstrata;

import java.util.List;

public class Procedimento extends Dec implements ISubrotina {
	public String id;
	public List<Parametro> params;
	public Comando com;
	private int frameSize = 0;
	
	public Procedimento(String id, List<Parametro> params, Comando com){
		this.id = id;
		this.params = params;
		this.com = com;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitProcedimento(this);
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
