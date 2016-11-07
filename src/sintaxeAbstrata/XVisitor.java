package sintaxeAbstrata;

import semantico.TArray;
import semantico.TBase;

public interface XVisitor {
	public Object visitBinExp(BinExp binExp);
	public Object visitBlocoExp(BlocoExp blocoExp);
	public Object visitChamadaExp(ChamadaExp chamadaExp);
	public Object visitCom(Com com);
	public Object visitASSIGN(ASSIGN comandoAtribuicao);
	public Object visitCHAMADA(CHAMADA comandoChamada);
	public Object visitIF(IF comandoIf);
	public Object visitWHILE(WHILE comandoWhile);
	public Object visitCons(Cons cons);
	public Object visitConsComp(ConsComp consComp);
	public Object visitConsExt(ConsExt consExt);
	public Object visitDC(DC dC);
	public Object visitDecCons(DecCons decCons);
	public Object visitDecVar(DecVar decVar);
	public Object visitDV(DV dV);
	public Object visitFuncao(Funcao funcao);
	public Object visitIndexada(Indexada indexada);
	public Object visitLiteralBool(LiteralBool literalBool);
	public Object visitLiteralInt(LiteralInt literalInt);
	public Object visitMenos(Menos menos);
	public Object visitNao(Nao nao);
	public Object visitParBaseCopia(ParBaseCopia parBaseCopia);
	public Object visitParBaseRef(ParBaseRef parBaseRef);
	public Object visitParArrayCopia(ParArrayCopia parArrayCopia);
	public Object visitParArrayRef(ParArrayRef parArrayRef);
	public Object visitProcedimento(Procedimento procedimento);
	public Object visitPrograma(Programa programa);
	public Object visitTipoBase(TipoBase tipoBase);
	public Object visitVarExp(VarExp varExp);
	public Object visitVarInic(VarInic varInic);
	public Object visitVarInicComp(VarInicComp varInicComp);
	public Object visitVarInicExt(VarInicExt inicExt);
	public Object visitVarNaoInic(VarNaoInic varNaoInic);
	public Object visitSimples(Simples simples);
	public Object visitBLOCO(BLOCO bloco);
	public Object visitTipoArray(TipoArray tipoArray);
	public Object visitIntToReal(IntToReal intToReal);

	default semantico.TBase tipoSemantico(sintaxeAbstrata.TBase b) {
		switch (b) {
		case BOOL:
			return TBase.BOOL;
		case INT:
			return TBase.INT;
		case REAL:
			return TBase.REAL;
		default:
			return null;
		}
	}
	
	default semantico.ITSemantico tipoSemantico(sintaxeAbstrata.Tipo b) {
		if (b instanceof sintaxeAbstrata.TipoBase)
			return this.tipoSemantico(((sintaxeAbstrata.TipoBase) b).base);
		else
			return new TArray(this.tipoSemantico(((sintaxeAbstrata.TipoArray) b).base), ((sintaxeAbstrata.TipoArray) b).expList.size());
	}
	

}
