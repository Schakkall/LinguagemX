package ambiente;

import java.util.ArrayList;
import java.util.LinkedList;

public class AmbienteVarCons {
	private final int SIZE = 256;
	private AmarracaoVarCons tabela[] = new AmarracaoVarCons[SIZE];
	private LinkedList<ArrayList<String>> plihaEscopo = new LinkedList<ArrayList<String>>(); 
	
	private final int getPosSimbolo(String s){
		Simbolo sim = Simbolo.getSimbolo(s);
		return sim.hashcode() % this.SIZE;
	}
	
	public void put(String s, VinculavelVarCons amarracao){
		if (!this.plihaEscopo.isEmpty())
			this.plihaEscopo.getFirst().add(s);

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
		this.plihaEscopo.addFirst(new ArrayList<String>());
	}
	
	public void closeScope(){
		if (!this.plihaEscopo.isEmpty()){
			for (String id : this.plihaEscopo.getFirst())
				this.pop(id);
			this.plihaEscopo.removeFirst();
		}
	}
}