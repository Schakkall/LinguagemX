package sintaxeAbstrata;

public class TipoBase extends Tipo {
	public TBase base;
	
	public TipoBase(TBase base){
		this.base = base;
	}
	
	public Object accept(XVisitor visitor){
		return visitor.visitTipoBase(this);
	}
}
