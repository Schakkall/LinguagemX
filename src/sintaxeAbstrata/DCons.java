package sintaxeAbstrata;

public abstract class DCons extends Dec {
	public Tipo tipo;
	public String id;
	
	public DCons(Tipo type, String id) {
		this.tipo = type;
		this.id   = id;
	}
}
