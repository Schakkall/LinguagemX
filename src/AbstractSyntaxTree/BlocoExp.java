package AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

public class BlocoExp extends Exp {
	public Exp exp;
	public List<DCons> consList; 

	public BlocoExp(){
		this.consList = new ArrayList<DCons>();
	} 	
	
	public BlocoExp(List<DCons> consList){
		this.consList = consList;
	} 
	
	public void AddExp(DCons consList) {
		this.consList.add(consList);
	}
}
