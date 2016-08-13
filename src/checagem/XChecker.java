package checagem;

import sintaxeAbstrata.*;
import utils.ReportadorDeErros;
import ambiente.*;

import java.util.ArrayList;
import java.util.List;

public final class XChecker implements XVisitor {
	
	AmbienteVarCons ambienteVarCons    = new AmbienteVarCons();
	AmbienteFunProc ambienteSubRotinas = new AmbienteFunProc(ambienteVarCons);
	ReportadorDeErros reporter = new ReportadorDeErros();
	
	
	public checagem.TBase tipoSemantico(sintaxeAbstrata.TBase b){
		switch (b) {
		case BOOL:
			return checagem.TBase.BOOL;
		case INT:
			return checagem.TBase.INT;
		case REAL:
			return checagem.TBase.REAL;
		default:
			return null;
		}
	}
	
	public Object visitBinExp(BinExp binExp){
		ITipoSemantico t1 = (ITipoSemantico)binExp.esqExp.accept(this);
		ITipoSemantico t2 = (ITipoSemantico)binExp.dirExp.accept(this);
		
		boolean erro = false;
		if (t1.equals(t2))
			switch (binExp.op) {
			case DIV:
			case IGUAL:
			case MENOR:
			case MOD:
			case MUL:
			case SOM:
			case SUB:
				if (t1.equals(checagem.TBase.BOOL))
					erro = true;
				break;

			case OU:
			case E:
				if (!t1.equals(checagem.TBase.BOOL))
					erro = true;			
				break;
			}
		else
			erro = true;
		
		if (erro){
			reporter.reportError("Expressao binária(%) inválida entre " + t1 + " e " + t2, "BinExp("+binExp.op+")");
			//Retornar um inteiro
			return null;
		}	
		else 	
			return t1;
		
	}
	
	public Object visitBlocoExp(BlocoExp blocoExp){
		return blocoExp.exp.accept(this);
	}
	
	public Object visitChamadaExp(ChamadaExp chamadaExp){
		return null;
	}
	
	public Object visitCom(Com com){
		com.com.accept(this);
		return null;
	}
	
	public Object visitASSIGN(ASSIGN comandoAtribuicao){
		ITipoSemantico t1 = (ITipoSemantico)comandoAtribuicao.var.accept(this);
		ITipoSemantico t2 = (ITipoSemantico)comandoAtribuicao.exp.accept(this);
		//Lembrar de fazer a coerção
		if (!(t1.equals(t2))){
			reporter.reportError("Atribuição(%) inválida entre " + t1 + " e " + t2, "ASSIGN");
			return null;
		}
		return null;
	}
	
	public Object visitCHAMADA(CHAMADA comandoChamada){
		//VinculavelFunProc v = (VinculavelFunProc) ambienteSubRotinas.lookup(comandoChamada.id);
		//TODO Implementar checagem de chamadas
		return null;
	}
	
	public Object visitIF(IF comandoIf){
		ITipoSemantico t = (ITipoSemantico)comandoIf.condicao.accept(this);
		if (!(t.equals(checagem.TBase.BOOL)))
			reporter.reportError("Expressão inválida para a condição de um % " + t, "IF");
		comandoIf.comandoEntao.accept(this);
		comandoIf.comandoSenao.accept(this);
		return null;
	}
	
	public Object visitWHILE(WHILE comandoWhile){
		ITipoSemantico t = (ITipoSemantico)comandoWhile.condicao.accept(this);
		if (!(t.equals(checagem.TBase.BOOL)))
			reporter.reportError("Expressão inválida para a condição de um % " + t, "WHILE");
		comandoWhile.comando.accept(this);		
		return null;
	}
	
	public Object visitCons(Cons cons){
		ITipoSemantico tipo = (ITipoSemantico) cons.tipo.accept(this);
		ambienteVarCons.put(cons.id, new VinculavelVarCons(tipo, true));								
		return null;
	}
	
	public Object visitConsComp(ConsComp consComp){
		ITipoSemantico tipo = (ITipoSemantico) consComp.tipo.accept(this);
		ambienteVarCons.put(consComp.id, new VinculavelVarCons(tipo, true));						
		return null;
	}
	
	public Object visitConsExt(ConsExt consExt){
		ITipoSemantico tipo = (ITipoSemantico) consExt.tipo.accept(this);
		ambienteVarCons.put(consExt.id, new VinculavelVarCons(tipo, true));								
		return null;
	}
	
	public Object visitDC(DC dC){
		dC.dcons.accept(this);
		return null;
	}
	
	public Object visitDecCons(DecCons decCons){
		decCons.dcons.accept(this);
		return null;
	}
	
	public Object visitDecVar(DecVar decVar){
		decVar.dvar.accept(this);
		return null;
	}
	
	public Object visitDV(DV dV){
		dV.dvar.accept(this);
		return null;
	}
	
	public Object visitFuncao(Funcao funcao){
		List<VinculavelVarCons> parLst = new ArrayList<VinculavelVarCons>();
		
		for (Parametro par: funcao.params) 
			parLst.add((VinculavelVarCons)par.accept(this));
			
		VinculavelVarCons retorno = new VinculavelVarCons((ITipoSemantico)funcao.tipo.accept(this), true);
		
		ambienteSubRotinas.put(funcao.id, new VinculavelFunProc(parLst, retorno));
		return funcao.exp.accept(this);
	}
	
	public Object visitIndexada(Indexada indexada){
		return indexada.var.accept(this);
	}
	
	public Object visitLiteralBool(LiteralBool literalBool){
		return checagem.TBase.BOOL;
	}
	
	public Object visitLiteralInt(LiteralInt literalInt){
		return checagem.TBase.INT;
	}
	
	public Object visitMenos(Menos menos){
		ITipoSemantico t = (ITipoSemantico) menos.exp.accept(this);
		if (t == TBase.BOOL){
			reporter.reportError("Expressão unária(%) inválida para " + t, "MENOS");
			return null;
		}
		else
			return t;
	}
	
	public Object visitNao(Nao nao){
		ITipoSemantico t = (ITipoSemantico) nao.exp.accept(this);
		if (t == TBase.BOOL)
			return t;
		else{
			reporter.reportError("Expressão unária(%) inválida para " + t, "NAO");
			return null;
		}
	}
	
	public Object visitParBaseCopia(ParBaseCopia parBaseCopia){
		ambienteVarCons.put(parBaseCopia.id, new VinculavelVarCons(tipoSemantico(parBaseCopia.tipo), false));
		return null;
	}
	
	public Object visitParBaseRef(ParBaseRef parBaseRef){
		ambienteVarCons.put(parBaseRef.id, new VinculavelVarCons(tipoSemantico(parBaseRef.tipo), false));		
		return null;
	}
	
	public Object visitParArrayCopia(ParArrayCopia parArrayCopia){
		ambienteVarCons.put(parArrayCopia.id, new VinculavelVarCons(new TArray(tipoSemantico(parArrayCopia.tipo),
				parArrayCopia.dim), false));				
		return null;
	}
	
	public Object visitParArrayRef(ParArrayRef parArrayRef){
		ambienteVarCons.put(parArrayRef.id, new VinculavelVarCons(new TArray(tipoSemantico(parArrayRef.tipo),
				parArrayRef.dim), false));
		return null;
	}
	
	public Object visitProcedimento(Procedimento procedimento){
		//Verifica se existe a declaração
		//Coloacar na tabela de simbolos
		//Abrir escopo
		List<VinculavelVarCons> parLst = new ArrayList<VinculavelVarCons>();
		
		for (Parametro par: procedimento.params) 
			parLst.add((VinculavelVarCons)par.accept(this));
		
		ambienteSubRotinas.put(procedimento.id, new VinculavelFunProc(parLst));
		procedimento.com.accept(this);
		//Fechar escopo
		return null;
	}
	
	public Object visitPrograma(Programa programa){		
		for (Dec d : programa.decList) 
			d.accept(this);		
		return null;
	}
	
	public Object visitTipoBase(TipoBase tipoBase){
		return this.tipoSemantico(tipoBase.base);
	}
	
	public Object visitTipoArray(TipoArray tipoArray) {
		return new TArray(this.tipoSemantico(tipoArray.base), tipoArray.expList.size());
	}	
	
	public Object visitVarExp(VarExp varExp){		
		return varExp.var.accept(this);
	}
	
	public Object visitVarInic(VarInic varInic){
		//Verificar se a varável já foi declarada
		ITipoSemantico tipo = (ITipoSemantico) varInic.tipo.accept(this);
		ambienteVarCons.put(varInic.id, new VinculavelVarCons(tipo, false));				
		return null;
	}
	
	public Object visitVarInicComp(VarInicComp varInicComp){
		ITipoSemantico tipo = (ITipoSemantico) varInicComp.tipo.accept(this);
		ambienteVarCons.put(varInicComp.id, new VinculavelVarCons(tipo, false));		
		return null;
	}
	
	public Object visitVarInicExt(VarInicExt varInicExt){
		ITipoSemantico tipo = (ITipoSemantico) varInicExt.tipo.accept(this);
		ambienteVarCons.put(varInicExt.id, new VinculavelVarCons(tipo, false));
		return null;
	}
	
	public Object visitVarNaoInic(VarNaoInic varNaoInic){
		ITipoSemantico tipo = (ITipoSemantico) varNaoInic.tipo.accept(this);
		ambienteVarCons.put(varNaoInic.id, new VinculavelVarCons(tipo, false));
		return null;
	}

	public Object visitSimples(Simples simples) {
		return ambienteVarCons.lookup(simples.id).tipo;
		//Reportar variável não declarada
	}

	public Object visitBLOCO(BLOCO bloco) {
		//Iniciar bloco
		for (DVarConsCom c : bloco.comList) 
			c.accept(this);
		//Finalizar bloco
		return null;
	}
}
