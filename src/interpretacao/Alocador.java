package interpretacao;

public class Alocador {
	private Integer pos = 0;

	private static Alocador alocador = new Alocador();
	
	private Alocador(){}
	
	public static Alocador getInstance(){
		return alocador;
	}
	
	public Integer next(){
		return this.pos++;
	}
	
	public void reset(){
		this.pos = 0;
	}
}
