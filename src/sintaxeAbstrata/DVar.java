package sintaxeAbstrata;

import interpretacao.Endereco;

public abstract class DVar extends ASA {
	public Tipo tipo;
	public String id;
	public Endereco endr;
	
	public DVar(Tipo tipo, String id) {
		this.tipo = tipo;
		this.id   = id;
	}
}
