package checagem;

public class TArray implements ITSemantico {
	public TBase tipo;
	public int dim;
	
	public TArray(TBase tipo, int dim){
		this.tipo = tipo;
		this.dim = dim;
	}
	
	public boolean equals(ITSemantico a){
		return (a instanceof TArray) ? (tipo.equals(((TArray)a).tipo) &&  this.dim==((TArray)a).dim) : false;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder(dim);
		for(int i = 0; i < dim; i++){
			sb.append("[]");
		}
		return tipo.toString() + sb.toString();
	}
	
	public static boolean isListNum(ITSemantico a) {
		if (a instanceof TArray)
			return TBase.isNum(((TArray)a).tipo);
		else
			return false;
	}
	
	public static boolean isListBool(ITSemantico a) {
		if (a instanceof TArray)
			return TBase.isBool(((TArray)a).tipo);
		else
			return false;
	}	
}
