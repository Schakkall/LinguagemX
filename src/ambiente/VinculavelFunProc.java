package ambiente;

import java.util.List;

public class VinculavelFunProc extends Vinculavel {
	public boolean isFunc;
	public List<VinculavelVarCons> params;
	public VinculavelVarCons tipoRetorno;
	
	public VinculavelFunProc(List<VinculavelVarCons> params, VinculavelVarCons tipoRetorno) {
		this.isFunc = true;
		this.params = params;
		this.tipoRetorno = tipoRetorno;
	}
	
	public VinculavelFunProc(List<VinculavelVarCons> params) {
		this.isFunc = false;
		this.params = params;
		this.tipoRetorno = null;
	}
}
