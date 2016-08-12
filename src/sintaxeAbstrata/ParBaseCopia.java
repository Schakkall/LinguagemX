package sintaxeAbstrata;

public class ParBaseCopia extends Parametro {
	public ParBaseCopia(TBase tipo, String id) {
		super(tipo, id);
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitParBaseCopia(this);
	}
}
