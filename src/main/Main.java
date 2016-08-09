package main;

import java.util.ArrayList;
import java.util.List;
import ambiente.Simbolo;

import sintaxeAbstrata.*;

public class Main {

	Programa programa1(){
		/*
		 * function int soma(int x, int y) (x + y)
		 * 
		 * */
		
		List<Parametro> paramList = new ArrayList<Parametro>();
		paramList.add(new ParBaseCopia(new TipoBase(TBase.INT), "x"));
		paramList.add(new ParBaseCopia(new TipoBase(TBase.INT), "y"));
		
		Exp exp = new BinExp(BinOp.SOM, new VarExp(new Simples("x")) , new VarExp(new Simples("y")));
		
		List<Dec> decList = new ArrayList<Dec>();
		decList.add(new Funcao(new TipoBase(TBase.INT), "soma", paramList, exp));
				
		return new Programa(decList);
	}
	
	Programa programa2(){
		/*
		 * procedure troca(var int x, var int y) {
		 * 		var int temp;
		 * 		temp := x;
		 * 		x := y;
		 * 		y := temp;
		 * }
		 * 
		 * */
		
		List<Parametro> paramList = new ArrayList<Parametro>();
		paramList.add(new ParBaseRef(new TipoBase(TBase.INT), "x"));
		paramList.add(new ParBaseRef(new TipoBase(TBase.INT), "y"));
		
		List<DVarConsCom> cmdList = new ArrayList<DVarConsCom>();
		cmdList.add(new DV(new VarNaoInic(new TipoBase(TBase.INT), "temp")));
		cmdList.add(new Com(new ComandoAtribuicao(new Simples("temp"), new VarExp(new Simples("x")))));
		cmdList.add(new Com(new ComandoAtribuicao(new Simples("x"), new VarExp(new Simples("y")))));
		cmdList.add(new Com(new ComandoAtribuicao(new Simples("y"), new VarExp(new Simples("temp")))));
		
		Bloco bloco = new Bloco(cmdList);
		
		List<Dec> decList = new ArrayList<Dec>();
		decList.add(new Procedimento("troca", paramList, bloco));
				
		return new Programa(decList);
	}
	
	
	public static void main(String[] args){

	}	
}
