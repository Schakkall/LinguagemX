package checagem;

import sintaxeAbstrata.*;
import ambiente.*;
import utils.RegistradorDeErros;

import java.util.ArrayList;
import java.util.List;

public final class XChecker implements XVisitor {

	AmbienteVarCons ambienteVarCons = new AmbienteVarCons();
	AmbienteFunProc ambienteSubRotinas = new AmbienteFunProc();
	public RegistradorDeErros reporter = new RegistradorDeErros();

	private TBase tipoSemantico(sintaxeAbstrata.TBase b) {
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

	private TBase toReal(Exp exp) {
		exp = new IntToReal(exp);
		return TBase.REAL;
	}

	public Object visitBinExp(BinExp binExp) {
		ITSemantico tEsq = (ITSemantico) binExp.esqExp.accept(this);
		ITSemantico tDir = (ITSemantico) binExp.dirExp.accept(this);

		if (TBase.isBool(tEsq) && TBase.isBool(tDir))
			if (SemanOper.isBoolOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
				return TBase.BOOL;
			}
		else if (TBase.isInt(tEsq) && TBase.isInt(tDir))
			if (SemanOper.isNumOper(binExp.op))
				return TBase.INT;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
				return TBase.INT;
			}
		else if (TBase.isReal(tEsq) && TBase.isReal(tDir))
			if (SemanOper.isNumOper(binExp.op) && binExp.op != BinOp.MOD)
				return TBase.REAL;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
				return TBase.REAL;
			}
		else if (TBase.isInt(tEsq) && TBase.isReal(tDir)) {
			tEsq = this.toReal(binExp.esqExp);
			if (SemanOper.isNumOper(binExp.op) && binExp.op != BinOp.MOD)
				return TBase.REAL;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
				return TBase.REAL;
			}
		} else if (TBase.isReal(tEsq) && TBase.isInt(tDir)) {
			tDir = this.toReal(binExp.dirExp);
			if (SemanOper.isNumOper(binExp.op) && binExp.op != BinOp.MOD)
				return TBase.REAL;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
				return TBase.REAL;
			}
		} else {
			reporter.reportarErro(
					"Expressão binária: Operacao " + binExp.op + " inválida entre tipos " + tEsq + " e " + tDir);
			return TBase.INT;
		}
	}

	public Object visitBlocoExp(BlocoExp blocoExp) {
		ambienteVarCons.openScope();
		for (DCons dC : blocoExp.consList) {
			dC.accept(this);
		}
		ITSemantico t = (ITSemantico) blocoExp.exp.accept(this);
		ambienteVarCons.closeScope();
		return t;
	}

	public Object visitChamadaExp(ChamadaExp chamadaExp) {
		return null;
	}

	public Object visitCom(Com com) {
		com.com.accept(this);
		return null;
	}

	public Object visitASSIGN(ASSIGN comandoAtribuicao) {
		ITSemantico t1 = (ITSemantico) comandoAtribuicao.var.accept(this);
		ITSemantico t2 = (ITSemantico) comandoAtribuicao.exp.accept(this);

		// Testar declaração da variável

		if (TBase.isReal(t1) && TBase.isInt(t2))
			t2 = this.toReal(comandoAtribuicao.exp);

		if (!t1.equals(t2)) {
			reporter.reportarErro("Atribuição: Atribuir um " + t2 + " a um " + t1 + " não é uma operação válida");
		}

		return null;
	}

	public Object visitCHAMADA(CHAMADA comandoChamada) {
		VinculavelFunProc vinculo = ambienteSubRotinas.lookup(comandoChamada.id);
		ITSemantico t = ((vinculo != null) && (vinculo.isFunc)) ? vinculo.retorno.tipo : null;

		if (comandoChamada.expLst.size() != vinculo.params.size()) {
			reporter.reportarErro("Chamada: Número de paramêtros difere do declarado");
		} else
			for (int i = 0; i < comandoChamada.expLst.size(); i++) {
				ITSemantico t1 = (ITSemantico) comandoChamada.expLst.get(i).accept(this);
				ITSemantico t2 = vinculo.params.get(i).tipo;

				if (TBase.isInt(t1) && (TBase.isReal(t2))) {
					comandoChamada.expLst.set(i, new IntToReal(comandoChamada.expLst.get(i)));
					t1 = TBase.REAL;
				}

				if (vinculo.params.get(i).isRef)
					if (!VarExp.isVarExp(comandoChamada.expLst.get(i)))
						reporter.reportarErro("Chamada: Passagem de parâmetros por referência exige uma variável");

				if (!t2.equals(t1))
					reporter.reportarErro("Chamada: Tipo do parâmetro real difere do tipo do parâmetro formal");
			}

		return t;
	}

	public Object visitIF(IF comandoIf) {
		ITSemantico t = (ITSemantico) comandoIf.condicao.accept(this);

		if (!TBase.isBool(t))
			reporter.reportarErro("If: " + t + " não é um tipo adequado para a condição de um if");

		comandoIf.comandoEntao.accept(this);
		comandoIf.comandoSenao.accept(this);
		return null;
	}

	public Object visitWHILE(WHILE comandoWhile) {
		ITSemantico t = (ITSemantico) comandoWhile.condicao.accept(this);

		if (!TBase.isBool(t))
			reporter.reportarErro("While: " + t + " não é um tipo adequado para a condição de um while");

		comandoWhile.comando.accept(this);
		return null;
	}

	public Object visitCons(Cons cons) {
		ITSemantico tipo = (ITSemantico) cons.tipo.accept(this);
		ambienteVarCons.put(cons.id, new VinculavelVarCons(tipo, true));
		return null;
	}

	public Object visitConsComp(ConsComp consComp) {
		ITSemantico tipo = (ITSemantico) consComp.tipo.accept(this);
		ambienteVarCons.put(consComp.id, new VinculavelVarCons(tipo, true));
		return null;
	}

	public Object visitConsExt(ConsExt consExt) {
		ITSemantico tipo = (ITSemantico) consExt.tipo.accept(this);
		ambienteVarCons.put(consExt.id, new VinculavelVarCons(tipo, true));
		return null;
	}

	public Object visitDC(DC dC) {
		dC.dcons.accept(this);
		return null;
	}

	public Object visitDecCons(DecCons decCons) {
		decCons.dcons.accept(this);
		return null;
	}

	public Object visitDecVar(DecVar decVar) {
		decVar.dvar.accept(this);
		return null;
	}

	public Object visitDV(DV dV) {
		dV.dvar.accept(this);
		return null;
	}

	public Object visitFuncao(Funcao funcao) {
		ambienteVarCons.openScope();
		List<VinculavelParam> parLst = new ArrayList<VinculavelParam>();

		for (Parametro par : funcao.params) {
			par.accept(this);
			parLst.add((VinculavelParam) ambienteVarCons.lookup(par.id));
		}

		ITSemantico t = (ITSemantico) funcao.exp.accept(this);

		ambienteSubRotinas.put(funcao.id, new VinculavelFunProc(parLst, new VinculavelVarCons(t, true)));
		ambienteVarCons.closeScope();
		return t;
	}

	public Object visitIndexada(Indexada indexada) {
		ITSemantico t1 = (ITSemantico) indexada.var.accept(this);
		if (t1 instanceof TBase)
			reporter.reportarErro("Idexada: Uma variável TipoBase não pode ser indexada");
		else {
			TArray t1AsTArray = ((TArray) t1).clone();//Evitando typecasting

			if (!t1AsTArray.evoluirIndexacao()) {
				reporter.reportarErro("Idexada: Tentando acessar a " + t1AsTArray.level + "ª dimensão de um array de "
						+ t1AsTArray.dim + " dimensões.");
			}
			t1 = t1AsTArray;
		}
		ITSemantico t2 = (ITSemantico) indexada.index.accept(this);
		if (!TBase.isInt(t2))
			reporter.reportarErro("Idexada: " + t2 + "não pode ser um índice dessa variável");
		return t1;
	}

	public Object visitLiteralBool(LiteralBool literalBool) {
		return TBase.BOOL;
	}

	public Object visitLiteralInt(LiteralInt literalInt) {
		return TBase.INT;
	}

	public Object visitMenos(Menos menos) {
		ITSemantico t = (ITSemantico) menos.exp.accept(this);
		
		if (TBase.isNum(t))
			return t;
		else {
			reporter.reportarErro("Expressão unária: Operação de simetria inválida para o tipo " + t);
			return TBase.INT;
		}
	}

	public Object visitNao(Nao nao) {
		ITSemantico t = (ITSemantico) nao.exp.accept(this);

		if (TBase.isBool(t))
			return t;
		else {
			reporter.reportarErro("Expressão unária: Operação de negação inválida para o tipo " + t);
			return TBase.INT;
		}
	}

	public Object visitParBaseCopia(ParBaseCopia parBaseCopia) {
		ambienteVarCons.put(parBaseCopia.id, new VinculavelParam(tipoSemantico(parBaseCopia.tipo), false));
		return null;
	}

	public Object visitParBaseRef(ParBaseRef parBaseRef) {
		ambienteVarCons.put(parBaseRef.id, new VinculavelParam(tipoSemantico(parBaseRef.tipo), true));
		return null;
	}

	public Object visitParArrayCopia(ParArrayCopia parArrayCopia) {
		ambienteVarCons.put(parArrayCopia.id,
				new VinculavelParam(new TArray(tipoSemantico(parArrayCopia.tipo), parArrayCopia.dim), false));
		return null;
	}

	public Object visitParArrayRef(ParArrayRef parArrayRef) {
		ambienteVarCons.put(parArrayRef.id,
				new VinculavelParam(new TArray(tipoSemantico(parArrayRef.tipo), parArrayRef.dim), true));
		return null;
	}

	public Object visitProcedimento(Procedimento procedimento) {
		// Verificar se existe a declaração
		ambienteVarCons.openScope();
		List<VinculavelParam> parLst = new ArrayList<VinculavelParam>();

		for (Parametro par : procedimento.params) {
			par.accept(this);
			parLst.add((VinculavelParam) ambienteVarCons.lookup(par.id));
		}

		ambienteSubRotinas.put(procedimento.id, new VinculavelFunProc(parLst));
		procedimento.com.accept(this);
		ambienteVarCons.closeScope();
		return null;
	}

	public Object visitPrograma(Programa programa) {
		for (Dec d : programa.decList)
			d.accept(this);
		return null;
	}

	public Object visitTipoBase(TipoBase tipoBase) {
		return this.tipoSemantico(tipoBase.base);
	}

	public Object visitTipoArray(TipoArray tipoArray) {
		return new TArray(this.tipoSemantico(tipoArray.base), tipoArray.expList.size());
	}

	public Object visitVarExp(VarExp varExp) {
		return varExp.var.accept(this);
	}

	public Object visitVarInic(VarInic varInic) {
		// Verificar se a varável já foi declarada
		ITSemantico tipo = (ITSemantico) varInic.tipo.accept(this);
		ambienteVarCons.put(varInic.id, new VinculavelVarCons(tipo, false));
		return null;
	}

	public Object visitVarInicComp(VarInicComp varInicComp) {
		ITSemantico tipo = (ITSemantico) varInicComp.tipo.accept(this);
		ambienteVarCons.put(varInicComp.id, new VinculavelVarCons(tipo, false));
		return null;
	}

	public Object visitVarInicExt(VarInicExt varInicExt) {
		ITSemantico tipo = (ITSemantico) varInicExt.tipo.accept(this);
		ambienteVarCons.put(varInicExt.id, new VinculavelVarCons(tipo, false));
		return null;
	}

	public Object visitVarNaoInic(VarNaoInic varNaoInic) {
		ITSemantico tipo = (ITSemantico) varNaoInic.tipo.accept(this);
		ambienteVarCons.put(varNaoInic.id, new VinculavelVarCons(tipo, false));
		return null;
	}

	public Object visitSimples(Simples simples) {
		VinculavelVarCons vinculo = ambienteVarCons.lookup(simples.id);
		ITSemantico t = (vinculo != null) ? vinculo.tipo : null;

		if (t == null) {
			reporter.reportarErro("Simples: identificador " + simples.id + " não declarado");
			t = TBase.INT;
		}

		return t;
	}

	public Object visitBLOCO(BLOCO bloco) {
		ambienteVarCons.openScope();
		for (DVarConsCom c : bloco.comList)
			c.accept(this);
		ambienteVarCons.closeScope();
		return null;
	}

	public Object visitIntToReal(IntToReal intToReal) {
		return TBase.REAL;
	}
}
