package checagem;

public class TArray implements ITipoSemantico {
	public TBase tipo;
	public int size;
	
	public TArray(TBase tipo, int size){
		this.tipo = tipo;
		this.size = size;
	}
}