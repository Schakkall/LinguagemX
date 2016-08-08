package sintaxeAbstrata;

public class Cons extends DCons {
	public Exp cons;
	
	public Cons(Tipo tipo, String id, Exp cons) {
		super(tipo, id);
		this.cons = cons;
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitCons(this);
	}
}
