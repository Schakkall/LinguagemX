package ambiente;

import java.util.List;

public class VinculavelFunProc extends Vinculavel {
	public boolean isFunc;
	public List<VinculavelParam> params;
	public VinculavelVarCons retorno;
	
	public VinculavelFunProc(List<VinculavelParam> params, VinculavelVarCons retorno) {
		this.isFunc = true;
		this.params = params;
		this.retorno = retorno;
	}
	
	public VinculavelFunProc(List<VinculavelParam> params) {
		this.isFunc = false;
		this.params = params;
		this.retorno = null;
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
