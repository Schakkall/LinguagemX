package ambiente;

public class AmbienteVarCons {
	final int SIZE = 256;
	AmarracaoVarCons tabela[] = new AmarracaoVarCons[SIZE];
	
	public void put(String s, VinculavelVarCons amarracao){
		Simbolo sim = Simbolo.getSimbolo(s);
		int index = sim.hashcode() % this.SIZE;
		tabela[index] = new AmarracaoVarCons(s, amarracao, tabela[index]);
	}
	
	public VinculavelVarCons lookup(String s) {
		Simbolo sim = Simbolo.getSimbolo(s);
		int index = sim.hashcode() % this.SIZE;
		for(AmarracaoVarCons a = tabela[index]; a != null; a = a.proximo) 
			if (s.equals(a.id))
				return a.amarracao;
		return null;
	}
	
	public void pop(String s){
		Simbolo sim = Simbolo.getSimbolo(s);
		int index = sim.hashcode() % this.SIZE;
		tabela[index] = tabela[index].proximo;// Joga o anterior para o GC
	}
}