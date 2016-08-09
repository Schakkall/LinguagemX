package ambiente;

public class AmbienteVarCons {
	final int SIZE = 256;
	AmarracaoVarCons tabela[] = new AmarracaoVarCons[SIZE];
	
	private int getPosSimbolo(String s){
		Simbolo sim = Simbolo.getSimbolo(s);
		return sim.hashcode() % this.SIZE;
	}
	
	public void put(String s, VinculavelVarCons amarracao){
		int index = this.getPosSimbolo(s);
		tabela[index] = new AmarracaoVarCons(s, amarracao, tabela[index]);
	}
	
	public VinculavelVarCons lookup(String s) {
		int index = this.getPosSimbolo(s);
		for(AmarracaoVarCons a = tabela[index]; a != null; a = a.proximo) 
			if (s.equals(a.id))
				return a.amarracao;
		return null;
	}
	
	public void pop(String s){
		int index = this.getPosSimbolo(s);
		tabela[index] = tabela[index].proximo;// Joga o anterior para o GC
	}
}