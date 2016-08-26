package checagem;

public class XTypeErrors {
	public static final int
		EXP_BINARIA_INVALIDA = 0,
		EXP_MENOS_INVALIDA = 1,
		EXP_NAO_INVALIDA = 1,
		
		CHAMADA_NUM_PARAM = 2,
		CHAMADA_PARAM_REF_NAO_VAR = 3,
		CHAMADA_PARAM_REF_CONS = 4,
		CHAMADA_PARAM_ERRO_TIPOS = 5;
		
		
		
	public static String obterDescricao(int idErro){
		if ((idErro >= 0) && (idErro <= errorTable.length-1))
			return errorTable[idErro];
		else
			return "Erro desconhecido";
	}
	
	private static final String[] errorTable = new String[]{
			"A operação %s não se aplica aos tipos %s e %s",
			"A operação %s não se aplica ao tipo %s",
			
			"O número de parametros reais difere do número de parâmetros formais na chamada de %s",
			"Passagem de parâmetros por referência exige uma variável",
			"Passagem de parâmetros por referência exige uma variável",
			
	};
		
}
