package ambiente;

import checagem.ITipoSemantico;

public class VinculavelVarCons extends Vinculavel {
	public boolean isCons;
	public ITipoSemantico tipo;
	
	public VinculavelVarCons(ITipoSemantico tipo, boolean isCons){
		this.isCons = isCons;
		this.tipo   = tipo;
	}
	
	public boolean equals(VinculavelVarCons v){
		return (/*(this.isCons == v.isCons) &&*/ (this.tipo.equals(v.tipo)));
	}
}
