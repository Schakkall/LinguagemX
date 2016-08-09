package ambiente;

import java.util.HashMap;
import java.util.Map;

public class AmbienteFunProc {
	Map<String, VinculavelFunProc> ambiente = 
			new HashMap<String, VinculavelFunProc>();
	
	public void put(String id, VinculavelFunProc amarracao){
		this.ambiente.put(id, amarracao);
	}
	
	public VinculavelFunProc get(String id){
		return this.get(id);
	}
}
