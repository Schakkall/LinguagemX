package checagem;

import sintaxeAbstrata.*;
import ambiente.AmbienteVarCons;
import ambiente.AmbienteFunProc;

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
		return null;
	}
	
	public Object visitIndexada(Indexada indexada){
		return null;
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
		return null;
	}
	
	public Object visitParBaseRef(ParBaseRef parBaseRef){
		return null;
	}
	
	public Object visitParArrayCopia(ParArrayCopia parArrayCopia){
		return null;
	}
	
	public Object visitParArrayRef(ParArrayRef parArrayRef){
		return null;
	}
	
	public Object visitProcedimento(Procedimento procedimento){
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
		return null;
	}
	
	public Object visitVarInic(VarInic varInic){
		return null;
	}
	
	public Object visitVarInicComp(VarInicComp varInicComp){
		return null;
	}
	
	public Object visitVarInicExt(VarInicExt inicExt){
		return null;
	}
	
	public Object visitVarNaoInic(VarNaoInic varNaoInic){
		return null;
	}

	@Override
	public Object visitSimples(Simples simples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitBloco(Bloco bloco) {
		// TODO Auto-generated method stub
		return null;
	}
}
