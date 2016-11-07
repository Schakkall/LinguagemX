package interpretacao;

public class BoolVal extends Value {
	public Boolean b = false;
	
	public BoolVal(Boolean b) {
		this.b = b;
	} 
	
	public static Boolean getBoolVal(Value val){
		return (val instanceof BoolVal) ? ((BoolVal) val).b : false; 
	}
	
	public String toString(){
		return this.b.toString();
	}
}
