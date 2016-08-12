package sintaxeAbstrata;

public class IF extends Comando {
	public Exp condicao;
	public Comando comandoEntao, comandoSenao;
	
	public IF(Exp condicao, Comando comandoEntao) {
		this.condicao = condicao;
		this.comandoEntao  = comandoEntao;
		this.comandoSenao  = null;
	}
	
	public IF(Exp condicao, Comando comandoEntao, Comando comandoSenao) {
		this.condicao = condicao;
		this.comandoEntao  = comandoEntao;
		this.comandoSenao  = comandoSenao;
	}	
	
	public Object accept(XVisitor visitor) {
		return visitor.visitIF(this);
	}
}
