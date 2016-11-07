package sintaxeAbstrata;

import interpretacao.Endereco;

public abstract class Parametro extends ASA {
	public TBase tipo;
	public String id;
	public Endereco endr;
	
	public Parametro(TBase tipo, String id) {
		this.tipo = tipo;
		this.id   = id;
	}
}
