package AbstractSyntaxTree;

public class VarNaoInic extends DVar {
	public VarNaoInic(Tipo tipo, String id) {
		super(tipo, id);
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitVarNaoInic(this);
	}
}
