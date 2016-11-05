package sintaxeAbstrata;

import java.util.ArrayList;
import java.util.List;

public class BlocoExp extends Exp {
	public Exp exp;
	public List<DCons> consList; 

	public BlocoExp(){
		this.consList = new ArrayList<DCons>();
	} 	
	
	public BlocoExp(List<DCons> consList, Exp exp){
		this.consList = consList;
		this.exp = exp;
	} 
	
	public void AddExp(DCons consList) {
		this.consList.add(consList);
	}
	public Object accept(XVisitor visitor) {
		return visitor.visitBlocoExp(this);
	}
}
