package semantico;

public class TArray implements ITSemantico, Cloneable {
	public TBase tipo;
	public int dim;
	public int level = 0;
	
	public TArray(TBase tipo, int dim){
		this.tipo = tipo;
		this.dim = dim;
		this.level = 0;
	}
	
	public TArray(TBase tipo, int dim, int level){
		this.tipo = tipo;
		this.dim = dim;
		this.level = level;
	}	
	
	public boolean equals(ITSemantico a){
		if (a instanceof TArray)
			return (tipo.equals(((TArray)a).tipo) &&  this.dimensaoNivelAcesso()==((TArray)a).dimensaoNivelAcesso());
		else
			return (tipo.equals((TBase)a) &&  this.dimensaoNivelAcesso()==0);
	}
	
	public boolean evoluirIndexacao(){
		return (++this.level <= this.dim) ? true : false;
	}
	
	public int dimensaoNivelAcesso(){
		return (dim >= level) ? dim - level : 0;
	}
	
	public boolean isAcessadoComoTipoBase(){
		return dimensaoNivelAcesso() == 0;
	}
	
	public void prepararAcesso(){
		this.level = 0;
	}
		
	public String toString(){
		StringBuilder sb = new StringBuilder(dim);
		for(int i = 0; i < this.dimensaoNivelAcesso(); i++){
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
	
	public TArray clone(){
		try {	
			return (TArray) super.clone();
		} catch	(CloneNotSupportedException e) {
			return null;
		}
	}
}
