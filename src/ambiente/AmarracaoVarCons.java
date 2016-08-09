package ambiente;

public class AmarracaoVarCons {
	public String id;
	public VinculavelVarCons amarracao;
	public AmarracaoVarCons proximo;
	
	public AmarracaoVarCons(String id, VinculavelVarCons amarracao, AmarracaoVarCons proximo) {
		this.id = id;
		this.amarracao = amarracao;
		this.proximo = proximo;
	}
}
