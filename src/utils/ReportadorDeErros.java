package utils;

public class ReportadorDeErros {
	  int numErrors;

	  public int getNumErrors() {
		return numErrors;
	  }

	  public boolean isAllOk(){
		  return numErrors == 0;
	  }  

	  public ReportadorDeErros() {
	    numErrors = 0;
	  }

	  public void reportError(String mensagem, String tokenName) {
	    System.out.print ("ERRO: ");

	    for (int p = 0; p < mensagem.length(); p++)
	    if (mensagem.charAt(p) == '%')
	      System.out.print(tokenName);
	    else
	      System.out.print(mensagem.charAt(p));
	    
	    System.out.println();
	    numErrors++;
	  }

}
