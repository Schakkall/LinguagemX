package main;

import java.io.FileReader;
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
			}
			
			System.out.println(ast.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}
