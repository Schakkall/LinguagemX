package AbstractSyntaxTree;

public class Com extends DVarConsCom {
	public Comando com;
	
	public Com(Comando com){
		this.com = com;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitCom(this);
	}
}
