package checagem;

import sintaxeAbstrata.*;
import ambiente.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe ainda não foi finalizada
 * @author Raz
 *
 */


public final class XChecker implements XVisitor {
	
	AmbienteFunProc ambienteSubRotinas = new AmbienteFunProc();
	AmbienteVarCons ambienteVarCons    = new AmbienteVarCons();
	
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
		return null;
	}
	
	public Object visitBlocoExp(BlocoExp blocoExp){
		return null;
	}
	
	public Object visitChamadaExp(ChamadaExp chamadaExp){
		return null;
	}
	
	public Object visitCom(Com com){
		return null;
	}
	
	public Object visitComandoAtribuicao(ComandoAtribuicao comandoAtribuicao){
		return null;
	}
	
	public Object visitComandoChamada(ComandoChamada comandoChamada){
		return null;
	}
	
	public Object visitComandoIf(ComandoIf comandoIf){
		return null;
	}
	
	public Object visitComandoWhile(ComandoWhile comandoWhile){
		return null;
	}
	
	public Object visitCons(Cons cons){
		return null;
	}
	
	public Object visitConsComp(ConsComp consComp){
		return null;
	}
	
	public Object visitConsExt(ConsExt consExt){
		return null;
	}
	
	public Object visitDC(DC dC){
		return null;
	}
	
	public Object visitDecCons(DecCons decCons){
		return null;
	}
	
	public Object visitDecVar(DecVar decVar){
		
		return null;
	}
	
	public Object visitDV(DV dV){
		return null;
	}
	
	public Object visitFuncao(Funcao funcao){
		List<VinculavelVarCons> parLst = new ArrayList<VinculavelVarCons>();
		
		for (Parametro par: funcao.params) 
			parLst.add((VinculavelVarCons)par.accept(this));
			
		VinculavelVarCons retorno = new VinculavelVarCons((ITipoSemantico)funcao.tipo.accept(this), true);
		
		ambienteSubRotinas.put(funcao.id, new VinculavelFunProc(parLst, retorno ));
		return retorno;
	}
	
	public Object visitIndexada(Indexada indexada){
		return indexada.var.accept(this);
	}
	
	public Object visitLiteralBool(LiteralBool literalBool){
		return null;
	}
	
	public Object visitLiteralInt(LiteralInt literalInt){
		return null;
	}
	
	public Object visitMenos(Menos menos){
		return null;
	}
	
	public Object visitNao(Nao nao){
		return null;
	}
	
	public Object visitParBaseCopia(ParBaseCopia parBaseCopia){
		ITipoSemantico tipo = (ITipoSemantico) parBaseCopia.tipo.accept(this);
		ambienteVarCons.put(parBaseCopia.id, new VinculavelVarCons(tipo, false));		
		return null;
	}
	
	public Object visitParBaseRef(ParBaseRef parBaseRef){
		ITipoSemantico tipo = (ITipoSemantico) parBaseRef.tipo.accept(this);
		ambienteVarCons.put(parBaseRef.id, new VinculavelVarCons(tipo, false));		
		return null;
	}
	
	public Object visitParArrayCopia(ParArrayCopia parArrayCopia){
		ITipoSemantico tipo = (ITipoSemantico) parArrayCopia.tipo.accept(this);
		ambienteVarCons.put(parArrayCopia.id, new VinculavelVarCons(tipo, false));				
		return null;
	}
	
	public Object visitParArrayRef(ParArrayRef parArrayRef){
		ITipoSemantico tipo = (ITipoSemantico) parArrayRef.tipo.accept(this);
		ambienteVarCons.put(parArrayRef.id, new VinculavelVarCons(tipo, false));				
		return null;
	}
	
	public Object visitProcedimento(Procedimento procedimento){
		List<VinculavelVarCons> parLst = new ArrayList<VinculavelVarCons>();
		
		for (Parametro par: procedimento.params) 
			parLst.add((VinculavelVarCons)par.accept(this));
		
		ambienteSubRotinas.put(procedimento.id, new VinculavelFunProc(parLst));
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
		return varExp.accept(this);
	}
	
	public Object visitVarInic(VarInic varInic){
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
		return ambienteVarCons.lookup(simples.id);
	}

	public Object visitBloco(Bloco bloco) {
		for (DVarConsCom c : bloco.comList) {
			c.accept(this);
		}
		return null;
	}
}
