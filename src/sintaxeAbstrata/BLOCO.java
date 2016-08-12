package sintaxeAbstrata;

import java.util.ArrayList;
import java.util.List;

public class BLOCO extends Comando {
	public List<DVarConsCom> comList; 

	public BLOCO(){
		this.comList = new ArrayList<DVarConsCom>();
	} 	
	
	public BLOCO(List<DVarConsCom> comList){
		this.comList = comList;
	} 
	
	public void AddDVarConsCom(DVarConsCom comList) {
		this.comList.add(comList);
	}

	public Object accept(XVisitor visitor) {
		return visitor.visitBLOCO(this);
	}	
}
