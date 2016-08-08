package sintaxeAbstrata;

public abstract class DVar extends ASA {
	public Tipo tipo;
	public String id;
	
	public DVar(Tipo tipo, String id) {
		this.tipo = tipo;
		this.id   = id;
	}
}
