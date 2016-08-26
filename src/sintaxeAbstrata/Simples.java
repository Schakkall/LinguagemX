package sintaxeAbstrata;

public class Simples extends Var {
	public String id;
	
	public Simples(String id){
		this.id = id;
	}
	
	public Object accept(XVisitor visitor) {
		return visitor.visitSimples(this);
	}
	
	public String getId(){
		return this.id;
	}
}
