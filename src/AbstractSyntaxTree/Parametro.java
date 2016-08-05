package AbstractSyntaxTree;

public abstract class Parametro extends ASA {
	public Tipo tipo;
	public String id;
	
	public Parametro(Tipo tipo, String id) {
		this.tipo = tipo;
		this.id   = id;
	}
}
