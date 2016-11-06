package ambiente;

import semantico.ITSemantico;
import interpretacao.Endereco;

public class VinculavelVarCons extends Vinculavel {
	public boolean isCons;
	public ITSemantico tipo;
	public Endereco endr; 
	
	public VinculavelVarCons(ITSemantico tipo, boolean isCons){
		this.isCons = isCons;
		this.tipo   = tipo;
		this.endr = null;
	}
	
	public VinculavelVarCons(ITSemantico tipo, boolean isCons, Endereco endr){
		this.isCons = isCons;
		this.tipo   = tipo;
		this.endr = endr;
	}	
	
	public boolean equals(VinculavelVarCons v){
		return ((this.isCons == v.isCons) && (this.tipo.equals(v.tipo)));
	}
}
