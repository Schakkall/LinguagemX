package interpretacao;

import java.util.Arrays;

public class Memoria {
	
	//public final int MAX_SIZE = Integer.MAX_VALUE - 5;
	public final int MAX_SIZE = 100;
	
	public Value[] global = new Value[MAX_SIZE];
	public Value[] pilha = new Value[MAX_SIZE];
	private int framePointer = 0;
	private int frameSize = 0;
	
	public void newFrame(){
		this.framePointer += this.frameSize;
	}
	
	public void destroyFrame(){
		this.framePointer -= this.frameSize;
	}	
	
	public Value getValue(Endereco endr) {
		if (endr.getTipo() == TEndereco.GLOBAL)
			return this.global[endr.getValue()];
		else 
		if (endr.getTipo() == TEndereco.PILHA)
			return this.pilha[this.framePointer + endr.getValue()];
		else
			return null;
	}
	 
	public void setValue(Endereco endr, Value val) {
		if (endr.getTipo() == TEndereco.GLOBAL)
			this.global[endr.getValue()] = val;
		else 
		if (endr.getTipo() == TEndereco.PILHA)
			this.pilha[this.framePointer + endr.getValue()] = val;
	}
	
	public int getFramePointer() {
		return framePointer;
	}

	public void setFramePointer(int fp) {
		this.framePointer = fp;
	}

	public int getFrameSize() {
		return frameSize;
	}

	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}
	
	public String toString(){
		return "Globais: " + Arrays.asList(this.global)  + "\n" +
			   "Pilha: "+ Arrays.asList(this.pilha) + "\n";
	} 
	
}
