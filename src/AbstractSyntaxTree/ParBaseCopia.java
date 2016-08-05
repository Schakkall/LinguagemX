package AbstractSyntaxTree;

public class ParBaseCopia extends Parametro {
	public ParBaseCopia(Tipo tipo, String id) {
		super(tipo, id);
	}
	public Object accept(XVisitor visitor){
		return visitor.visitParBaseCopia(this);
	}
}
