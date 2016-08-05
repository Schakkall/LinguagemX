package AbstractSyntaxTree;

public class VarInic extends DVar {
	public Exp ini;
	
	public VarInic(Tipo tipo, String id, Exp ini) {
		super(tipo, id);
		this.ini = ini;
	}
}
