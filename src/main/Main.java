package main;

/**
 * Tradutor da Linguagem X
 * @author José Eurique Cardoso Ribeiro Junior
 * @author Leonardo Santos de Jesus
 */

//TODO Parser: Corrigir geração de sintaxe abstrata para as declarações em forma de listas no blocos (Declarações globais estão ok)
//TODO Parser: Corrigir sintaxe do tipo de retorno das funções; Nas declarações de funções, o tipo de retorno deve poder ser TArray
//TODO Interpreter: Implementar passagem de parâmetros por referência 


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
