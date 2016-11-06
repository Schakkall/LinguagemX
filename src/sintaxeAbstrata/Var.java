package sintaxeAbstrata;

import interpretacao.Endereco;

public abstract class Var extends ASA {
	public Endereco endr;
	
	public void setEndr(Endereco endr){
		this.endr = endr;
	}
	public abstract String getId();
}	
