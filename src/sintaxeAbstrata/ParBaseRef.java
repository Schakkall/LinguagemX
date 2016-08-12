package sintaxeAbstrata;

public class ParBaseRef extends Parametro {
	public ParBaseRef(TBase tipo, String id) {
		super(tipo, id);
	}
	
	public Object accept(XVisitor visitor) {
		return visitor.visitParBaseRef(this);
	}
}
