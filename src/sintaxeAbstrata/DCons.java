package sintaxeAbstrata;

import interpretacao.Endereco;

public abstract class DCons extends ASA {
	public Tipo tipo;
	public String id;
	public Endereco endr;
	
	public DCons(Tipo type, String id) {
		this.tipo = type;
		this.id   = id;
	}
}
