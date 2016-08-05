package AbstractSyntaxTree;

public class ParArrayCopia extends ParBaseCopia {
	public Integer dim; 
	
	public ParArrayCopia(Tipo tipo, String id, Integer dim) {
		super(tipo, id);
		this.dim = dim;
	}
}
