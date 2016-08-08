package sintaxeAbstrata;

public class Nao extends UnaExp {
	public Exp exp;
	
	public Nao(Exp exp){
		this.exp = exp;
	} 
	
	public Object accept (XVisitor visitor){
		return visitor.visitNao(this);
	}
}
