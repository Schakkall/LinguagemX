package sintaxeAbstrata;


public class BinExp extends Exp {
	public Exp esqExp, dirExp;
	public BinOp op;
	
	public BinExp(BinOp op, Exp esq, Exp dir) {
		this.esqExp  = esq;
		this.dirExp  = dir;
		this.op      = op;
	}

	public Object accept(XVisitor visitor) {
		return visitor.visitBinExp(this);
	}
}
