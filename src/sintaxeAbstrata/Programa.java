package sintaxeAbstrata;

import java.util.ArrayList;
import java.util.List;

import sintaxeAbstrata.Dec;

public class Programa extends ASA {
	public List<Dec> decList; 

	public Programa(){
		this.decList = new ArrayList<Dec>();
	} 	
	
	public Programa(List<Dec> decList){
		this.decList = decList;
	} 
	
	public void IncluirDeclaracao(Dec dec) {
		this.decList.add(dec);
	}	
	
	public Object accept(XVisitor visitor){
		return visitor.visitPrograma(this);
	}
}
