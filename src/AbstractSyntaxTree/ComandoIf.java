package AbstractSyntaxTree;

public class ComandoIf extends Comando {
	public Exp condicao;
	public Comando comandoEntao, comandoSenao;
	
	public ComandoIf(Exp condicao, Comando comandoEntao) {
		this.condicao = condicao;
		this.comandoEntao  = comandoEntao;
		this.comandoSenao  = null;
	}
	
	public ComandoIf(Exp condicao, Comando comandoEntao, Comando comandoSenao) {
		this.condicao = condicao;
		this.comandoEntao  = comandoEntao;
		this.comandoSenao  = comandoSenao;
	}	
}
