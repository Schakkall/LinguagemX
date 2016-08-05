package AbstractSyntaxTree;

public class DC extends DVarConsCom {
	public DCons dcons;
	
	public DC(DCons dcons){
		this.dcons = dcons;
	}
	public Object accept(XVisitor visitor){
		return visitor.visitDC(this);
	}
}

