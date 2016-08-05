package AbstractSyntaxTree;

public class Menos extends UnaExp {
	public Exp exp;
	
	public Menos(Exp exp){
		this.exp = exp;
	}
	public Object accept ( XVisitor visitor){
		return visitor.visitMenos(this);
	}
}
