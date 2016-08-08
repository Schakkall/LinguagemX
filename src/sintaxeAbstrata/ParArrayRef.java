package sintaxeAbstrata;

public class ParArrayRef extends ParBaseRef {
	public Integer dim; 

	public ParArrayRef(Tipo tipo, String id, Integer dim) {
		super(tipo, id);
		this.dim = dim;
	}
	
	public Object accept(XVisitor visitor) {
		return visitor.visitParArrayRef(this);
	}
}
