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
	
	public boolean equals(){
		/* TODO Implementar método equals de VinculavelFunProc
		 * Dois VinculavelFunProc x e y são iguais quando:
		 * 1) x.isFunc == y.isFunc
		 * 2) x.tipoRetorno.equals(y.tipoRetorno)
		 * 3) Os elementos de x.params retornam "true" para equals com os elementos da mesma posição em y.params
		 */
		return false;
	}
}
