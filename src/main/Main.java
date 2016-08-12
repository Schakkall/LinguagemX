package main;

import java.util.ArrayList;
import java.util.List;
import checagem.XChecker;
import sintaxeAbstrata.*;

public class Main {

	Programa programa1(){
		/*
    	 * function int soma(int x, int y) (x + y)
		 * 
		 * */
		
		List<Parametro> paramList = new ArrayList<Parametro>();
		paramList.add(new ParBaseCopia(TBase.INT, "x"));
		paramList.add(new ParBaseCopia(TBase.INT, "y"));
		
		Exp exp = new BinExp(BinOp.SOM, new VarExp(new Simples("x")) , new VarExp(new Simples("y")));
		
		List<Dec> decList = new ArrayList<Dec>();
		decList.add(new Funcao(new TipoBase(TBase.INT), "soma", paramList, exp));
				
		return new Programa(decList);
	}
	
	static Programa programa2(){
		/*
		 * procedure troca(var int x, var int y) {
		 * 		var int temp;
		 * 		temp := x;
		 * 		x := y;
		 * 		y := (5 + 8) * 9 * temp;
		 * }
		 * 
		 * */
		
		List<Parametro> paramList = new ArrayList<Parametro>();
		paramList.add(new ParBaseRef(TBase.BOOL, "x"));
		paramList.add(new ParBaseRef(TBase.INT, "y"));
		paramList.add(new ParArrayRef(TBase.INT, "z", 5));
		
		List<DVarConsCom> cmdList = new ArrayList<DVarConsCom>();
		cmdList.add(new DV(new VarNaoInic(new TipoBase(TBase.INT), "temp")));
		cmdList.add(new Com(new ASSIGN(new Simples("temp"), new BinExp(BinOp.OU, new VarExp(new Simples("x")), new LiteralBool(false)))));
		//cmdList.add(new Com(new ASSIGN(new Simples("x"), new VarExp(new Simples("y")))));
		cmdList.add(new Com(new ASSIGN(new Indexada(new Simples("z"), new LiteralInt(2)), new VarExp(new Simples("temp")))));
		
		BLOCO bloco = new BLOCO(cmdList);
				
		List<Dec> decList = new ArrayList<Dec>();
		decList.add(new Procedimento("troca", paramList, bloco));
				
		return new Programa(decList);
	}
	
	public static Programa programa3(){
		return null;
	}
	
	
	public static void main(String[] args){
		XChecker checador = new XChecker();
		checador.visitPrograma(programa2());
	}	
}
