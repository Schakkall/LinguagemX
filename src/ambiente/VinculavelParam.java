package ambiente;

import semantico.ITSemantico;

public class VinculavelParam extends VinculavelVarCons {
	public boolean isRef;
	
	public VinculavelParam(ITSemantico tipo, boolean isRef){
		super(tipo, false);
		this.isRef = isRef;
	}
	
	public boolean equals(VinculavelParam v){
		return ((this.isRef == v.isRef) && (this.tipo.equals(v.tipo)));
	}
}
