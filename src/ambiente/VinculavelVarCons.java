package ambiente;

import semantico.ITSemantico;

public class VinculavelVarCons extends Vinculavel {
	public boolean isCons;
	public ITSemantico tipo;
	
	public VinculavelVarCons(ITSemantico tipo, boolean isCons){
		this.isCons = isCons;
		this.tipo   = tipo;
	}
	
	public boolean equals(VinculavelVarCons v){
		return ((this.isCons == v.isCons) && (this.tipo.equals(v.tipo)));
	}
}
