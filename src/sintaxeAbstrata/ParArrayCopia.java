package sintaxeAbstrata;

public class ParArrayCopia extends ParBaseCopia {
	public Integer dim; 
	
	public ParArrayCopia(Tipo tipo, String id, Integer dim) {
		super(tipo, id);
		this.dim = dim;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitParArrayCopia(this);
	}
}
