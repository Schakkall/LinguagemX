package interpretacao;

public class Endereco {
	private Integer value;
	private TEndereco tipo;

	public Endereco(Integer value){
		this.value = value;
		this.setTipo(TEndereco.GLOBAL);
	}
	
	public Endereco(Integer value, TEndereco tipo){
		this.value = value;
		this.setTipo(tipo);
	}	
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public TEndereco getTipo() {
		return tipo;
	}

	public void setTipo(TEndereco tipo) {
		this.tipo = tipo;
	}
	
	public String toString(){
		return this.value + " " + this.tipo;
	}
}
