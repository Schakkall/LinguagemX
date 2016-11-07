package main;

/**
 * Tradutor da Linguagem X
 * @author Jos� Eurique Cardoso Ribeiro Junior
 * @author Leonardo Santos de Jesus
 */

//TODO Parser: Corrigir gera��o de sintaxe abstrata para as declara��es em forma de listas no blocos (Declara��es globais est�o ok)
//TODO Parser: Corrigir sintaxe do tipo de retorno das fun��es; Nas declara��es de fun��es, o tipo de retorno deve poder ser TArray
//TODO Interpreter: Implementar passagem de par�metros por refer�ncia 


import java.io.FileReader;

import interpretacao.XInterpreter;
import semantico.XChecker;
import lexico.XLexer;
import sintatico.XParser;
import sintaxeAbstrata.Programa;

public class Main {
	
	public static void main(String[] args) {	
		try {
			XLexer l = new XLexer(new FileReader("p.x"));
			XParser p = new XParser(l);
		
			Programa ast = (Programa) p.parse().value;
			
			if (ast != null) {
				XChecker c = new XChecker(); 
				c.visitPrograma(ast);
				c.reporter.imprimirRelatorio();
				
				if (c.reporter.getNumErros() == 0) {
					XInterpreter i = new XInterpreter();
					i.visitPrograma(ast);
					System.out.println(i.mem);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
