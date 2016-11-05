package utils;

import java.util.ArrayList;
import java.util.List;

public class RegistradorDeErros {
	  List<String> erros = new ArrayList<String>();
	  

	  public int getNumErros() {
		return this.erros.size();
	  }

	  public boolean isAllOk(){
		  return this.erros.isEmpty();
	  }  

	  public void reportarErro(String mensagem) {
	    erros.add(mensagem);
	  }
	  
	  public void imprimirRelatorio() {
		  for (String erro: erros)  
			  System.out.println(erro);
	  }	  

}
