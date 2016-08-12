package checagem;

public class TArray implements ITipoSemantico {
	public TBase tipo;
	public int size;
	
	public TArray(TBase tipo, int size){
		this.tipo = tipo;
		this.size = size;
	}
	
	public boolean equals(ITipoSemantico a){
		return (a instanceof TArray) ? (tipo.equals(((TArray)a).tipo)) : tipo.equals(a);
	}
	
	public String toString(){
		return tipo.toString() + "[" + size + "]";
	}

}
