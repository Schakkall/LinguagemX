package ambiente;

import java.util.ArrayList;
import java.util.LinkedList;

public class AmbienteVarCons {
	private final int SIZE = 256;
	private AmarracaoVarCons tabela[] = new AmarracaoVarCons[SIZE];
	private LinkedList<ArrayList<String>> escopo = new LinkedList<ArrayList<String>>(); 
	
	private final int getPosSimbolo(String s){
		Simbolo sim = Simbolo.getSimbolo(s);
		return sim.hashcode() % this.SIZE;
	}
	
	public void put(String s, VinculavelVarCons amarracao){
		if (!this.escopo.isEmpty())
			this.escopo.getFirst().add(s);

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
		for(AmarracaoVarCons a = tabela[index]; a != null; a = a.proximo) 
			if (s.equals(a.id)){
				// Joga o anterior para o GC
				a = a.proximo;
				break;
			}
	}
	
	public void openScope(){
		this.escopo.addFirst(new ArrayList<String>());
	}
	
	public void closeScope(){
		if (!this.escopo.isEmpty()){
			for (String id : this.escopo.getFirst())
				this.pop(id);
			this.escopo.removeFirst();
		}
	}
}