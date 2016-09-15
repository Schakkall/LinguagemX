package main;

import java.util.ArrayList;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import semantico.XChecker;
import lexico.*;
import sintaxeAbstrata.*;


public class Main {

	// Programa da sequencia de Fibonnaci ate 500
	static Programa programa1() {
	    /*
	     * 
		 * procedure fibonnaci(int n, var int res){ 
		 * 		var int vFib1 := 1; 
		 * 		var int vFib2 := 0; 
		 * 		var int vAuxF := 0; 
		 * 		while (vFib2 < n) { 
		 * 			vAuxF := vFib1 + vFib2; 
		 * 			vFib1 := vFib2; 
		 * 			vFib2 := vAuxF;
		 * 		}
		 * 		res := vFib2;
		 * }
		 * 
		 * procedure main(){
		 * 		var int[10][10][10] x;
		 * 		var boolean res;
		 * 		fibonnaci(500, res);
		 * 		x[5][5][5] = res;
		 * }
		 * 
		 * 
		 * */

		List<DVarConsCom> decComFibo = new ArrayList<>();
		decComFibo.add(new DV(new VarInic(new TipoBase(TBase.INT), "vAuxF", new LiteralInt(0))));
		decComFibo.add(new DV(new VarInic(new TipoBase(TBase.INT), "vFib1", new LiteralInt(1))));
		decComFibo.add(new DV(new VarInic(new TipoBase(TBase.INT), "vFib2", new LiteralInt(0))));
		//decComFibo.add(new DC(new Cons(new TipoBase(TBase.INT), "vFib2", new LiteralInt(0))));

		List<DVarConsCom> comandosLoop = new ArrayList<>();
		comandosLoop.add(new Com(new ASSIGN(new Simples("vAuxF"),
				new BinExp(BinOp.SOM, new VarExp(new Simples("vFib1")), new VarExp(new Simples("vFib2"))))));
		comandosLoop.add(new Com(new ASSIGN(new Simples("vFib1"), new VarExp(new Simples("vFib2")))));
		comandosLoop.add(new Com(new ASSIGN(new Simples("vFib2"), new VarExp(new Simples("vAuxF")))));

		decComFibo.add( 
				new Com(new WHILE(new BinExp(BinOp.MENOR, new VarExp(new Simples("vFib2")), new LiteralInt(500)),
						new BLOCO(comandosLoop))));
						
		BLOCO corpoFibo = new BLOCO(decComFibo);

		List<Parametro> parListFibo = new ArrayList<>();
		parListFibo.add(new ParBaseCopia(TBase.INT, "n"));
		parListFibo.add(new ParBaseRef(TBase.INT, "res"));

		Procedimento fibonnaci = new Procedimento("fibonacci", parListFibo, corpoFibo);

		List<DVarConsCom> decComMain = new ArrayList<>();
		
		decComMain.add(new DV(new VarInic(new TipoBase(TBase.INT), "res", new LiteralInt(0))));
		
		List<Exp> expList = new ArrayList<>();
		expList.add(new LiteralInt(500));
		//expList.add(new LiteralInt(50));
		expList.add(new VarExp(new Simples("res")));

		decComMain.add(new Com(new CHAMADA("fibonacci", expList)));
	
		List<Exp> arrayExp = new ArrayList<>();
		arrayExp.add(new LiteralInt(10));
		arrayExp.add(new LiteralInt(10));
		arrayExp.add(new LiteralInt(10));
		arrayExp.add(new LiteralInt(10));
		decComMain.add(new DV( new VarNaoInic(new TipoArray(TBase.INT, arrayExp), "x")));
		
		
		
		decComMain.add(new Com(
							new ASSIGN(
									//new Indexada(
											new Indexada(
													new Indexada( 
															new Indexada(
																	new Simples("x"), 
																	new LiteralInt(5)), 
															new LiteralInt(5)), 
													new LiteralInt(5)),		
									//		new LiteralInt(5)), 
									new VarExp(//new Simples("res")
											new Indexada(
											new Indexada(
													new Indexada( 
															new Indexada(
																	new Simples("x"), 
																	new LiteralInt(5)), 
															new LiteralInt(5)),
													new LiteralInt(5)),
												new LiteralInt(5))
											))));
		

		BLOCO corpoMain = new BLOCO(decComMain);

		List<Parametro> parListMain = new ArrayList<>();

		Procedimento main = new Procedimento("main", parListMain, corpoMain);

		List<Dec> dList = new ArrayList<>();
		//dList.add(main);
		dList.add(fibonnaci);
		dList.add(main);

		return new Programa(dList);
	}

	Programa programa2() {
		/*
		 * function int quadradoP(int a, int b) { var int temp; a := 2; b := 3;
		 * temp := (a*a) - 2*(a*b) + (b*b); }
		 * 
		 */

		// declara parametros
		List<Parametro> paramList = new ArrayList<Parametro>();
		paramList.add(new ParBaseCopia(TBase.INT, "a"));
		paramList.add(new ParBaseCopia(TBase.INT, "b"));

		// declaraÁ„o de variaveis
		List<DVarConsCom> cmdList = new ArrayList<DVarConsCom>();
		cmdList.add(new DV(new VarNaoInic(new TipoBase(TBase.INT), "temp")));

		// atribui√ß√£o aos parametros
		cmdList.add(new Com(new ASSIGN(new Simples("a"), new LiteralInt(2))));
		cmdList.add(new Com(new ASSIGN(new Simples("b"), new LiteralInt(3))));

		// monta express√µes
		Exp exp1 = new BinExp(BinOp.MUL, new VarExp(new Simples("a")), new VarExp(new Simples("a")));
		Exp exp2 = new BinExp(BinOp.MUL, new VarExp(new Simples("b")), new VarExp(new Simples("b")));
		Exp exp3 = new BinExp(BinOp.MUL, new VarExp(new Simples("a")), new VarExp(new Simples("b")));
		Exp exp4 = new BinExp(BinOp.MUL, new LiteralInt(2), exp3);

		Exp exp5 = new BinExp(BinOp.SUB, exp1, exp4);
		Exp exp6 = new BinExp(BinOp.SOM, exp5, exp2);

		List<Dec> decList = new ArrayList<Dec>();
		decList.add(new Funcao(new TipoBase(TBase.INT), "quadradoP", paramList, exp6));

		return new Programa(decList);

	}
	
    public static void analiseLexica() {
        try {
            XLexer l = new XLexer(
                    new FileReader("p.txt"));
            while (true) {
                try {
                    Token t = l.yylex();
                    if (t == null) break;
                    System.out.println(t.toString());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        }
    }	

	public static void main(String[] args) {

		analiseLexica();
		/*
		XChecker c = new XChecker();
		c.visitPrograma(programa1());
		c.reporter.imprimirRelatorio();
		*/

	}
}
