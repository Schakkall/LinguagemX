package sintaxeAbstrata;

public abstract class Parametro extends ASA {
	public TBase tipo;
	public String id;
	
	public Parametro(TBase tipo, String id) {
		this.tipo = tipo;
		this.id   = id;
	}
}
