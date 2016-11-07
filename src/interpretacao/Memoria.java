package interpretacao;

import java.util.Arrays;
import java.util.List;

public class Memoria {
	
	//public final int size = Integer.MAX_VALUE - 5;
	public final int size = 100;
	
	public Value[] global = new Value[size];
	public Value[] pilha = new Value[size];
	private int framePointer = 0;
	private int frameSize = 0;
	
	public void openFrame(){
		this.framePointer += this.frameSize;
	}
	
	public void closeFrame(){
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
	
	public Value getGlobalValue(Endereco endr){
		return this.global[endr.getValue()];
	}
	
	public Value getPilhaValue(Endereco endr){
		return this.pilha[endr.getValue()];
	}
	
	public void setGlobalValue(Endereco endr, Value val){
		this.global[endr.getValue()] = val;
	}
	
	public void setPilhaValue(Endereco endr, Value val){
		this.pilha[endr.getValue()] = val;
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
