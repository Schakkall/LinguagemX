package ambiente;

import java.util.HashMap;
import java.util.Map;

public class AmbienteFunProc {
	private Map<String, VinculavelFunProc> ambiente = 
			new HashMap<String, VinculavelFunProc>();
	
	public void put(String id, VinculavelFunProc amarracao){
		this.ambiente.put(id, amarracao);
	}
	
	public VinculavelFunProc lookup(String id){
		return this.ambiente.get(id);
	}
	
	public boolean isDeclared(String s){
		return (this.lookup(s) != null);
	}
}
