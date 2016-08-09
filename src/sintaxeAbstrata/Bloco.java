package sintaxeAbstrata;

import java.util.ArrayList;
import java.util.List;

public class Bloco extends Comando {
	public List<DVarConsCom> comList; 

	public Bloco(){
		this.comList = new ArrayList<DVarConsCom>();
	} 	
	
	public Bloco(List<DVarConsCom> comList){
		this.comList = comList;
	} 
	
	public void AddDVarConsCom(DVarConsCom comList) {
		this.comList.add(comList);
	}

	public Object accept(XVisitor visitor) {
		return visitor.visitBloco(this);
	}	
}
