package sintaxeAbstrata;

public class LiteralInt extends Literal {
	public Integer num;
	
	public LiteralInt(String sInt){
		this.num = Integer.parseInt(sInt);
	}

	public LiteralInt(Integer i){
		this.num = i;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitLiteralInt(this);
	}
}
