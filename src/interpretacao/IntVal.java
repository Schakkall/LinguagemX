package interpretacao;

public class IntVal extends Value {
	public Integer i = 0;
	
	public IntVal(Integer i){
		this.i = i;
	}
	
	public static Integer getIntVal(Value val){
		return (val instanceof IntVal) ? ((IntVal) val).i : 0; 
	}
	
	public String toString(){
		return this.i.toString();
	}
}
