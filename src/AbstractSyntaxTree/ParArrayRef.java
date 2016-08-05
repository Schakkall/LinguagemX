package AbstractSyntaxTree;

public class ParArrayRef extends ParBaseRef {
	public Integer dim; 

	public ParArrayRef(Tipo tipo, String id, Integer dim) {
		super(tipo, id);
		this.dim = dim;
	}
}
