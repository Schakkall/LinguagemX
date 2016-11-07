package interpretacao;

public class Alocador {
	private Integer pos = 0;
	
	private static Boolean def = true;

	private static Alocador pilha = new Alocador();
	private static Alocador global = new Alocador();
	
	private Alocador(){}
	
	public static Alocador pilha(){
		return pilha;
	}
	
	public static Alocador global(){
		return global;
	}
	
	public Endereco next(){
		//System.out.println((def) ? new Endereco(this.pos, TEndereco.GLOBAL) : new Endereco(this.pos, TEndereco.PILHA));		
		return (def) ? new Endereco(this.pos++, TEndereco.GLOBAL) : new Endereco(this.pos++, TEndereco.PILHA);
	}
	
	public Endereco current(){
		//System.out.println((def) ? new Endereco(this.pos, TEndereco.GLOBAL) : new Endereco(this.pos, TEndereco.PILHA));
		return (def) ? new Endereco(this.pos, TEndereco.GLOBAL) : new Endereco(this.pos, TEndereco.PILHA);
	}

	
	public void reset(){
		this.pos = 0;
	}

	public static Alocador getDefault() {
		return def ? global : pilha;
	}

	public static void setDefaultAsPilha() {
		def = false;
	}
	
	public static void setDefaultAsGlobal() {
		def = true;
	}
}
