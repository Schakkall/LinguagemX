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

	public Object visitBinExp(BinExp binExp) {
		ITSemantico tEsq = (ITSemantico) binExp.esqExp.accept(this);
		ITSemantico tDir = (ITSemantico) binExp.dirExp.accept(this);

		if (TBase.isBool(tEsq) && TBase.isBool(tDir))
			if (SemanOper.isBoolOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
				return TBase.BOOL;
			}
		else if (TBase.isInt(tEsq) && TBase.isInt(tDir))
			if (SemanOper.isNumOper(binExp.op))
				return TBase.INT;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
				return TBase.INT;
			}
		else if (TBase.isReal(tEsq) && TBase.isReal(tDir))
			if (SemanOper.isNumOper(binExp.op) && binExp.op != BinOp.MOD)
				return TBase.REAL;
			else if (SemanOper.isRelaOper(binExp.op))
				return TBase.BOOL;
			else {
				reporter.reportarErro(
						"BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
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
						"BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
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
						"BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
				return TBase.REAL;
			}
		} else {
			reporter.reportarErro("BinExp: Operacao " + binExp.op + " não se aplica a tipos " + tEsq + " e " + tDir);
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
		VinculavelFunProc vinculo = ambienteSubRotinas.lookup(chamadaExp.id);
		ITSemantico t = TBase.INT;

		if (vinculo != null) {
			t = vinculo.retorno.tipo;

			if (chamadaExp.expLst.size() != vinculo.params.size()) {
				reporter.reportarErro("Chamada: Número de paramêtros difere do declarado");
			} else
				for (int i = 0; i < chamadaExp.expLst.size(); i++) {
					ITSemantico t1 = (ITSemantico) chamadaExp.expLst.get(i).accept(this);
					ITSemantico t2 = vinculo.params.get(i).tipo;

					if (TBase.isInt(t1) && (TBase.isReal(t2))) {
						chamadaExp.expLst.set(i, new IntToReal(chamadaExp.expLst.get(i)));
						t1 = TBase.REAL;
					}

					if (vinculo.params.get(i).isRef)
						if (!VarExp.isVarExp(chamadaExp.expLst.get(i)))
							if (!vinculo.params.get(i).isCons)
								reporter.reportarErro(
										"Chamada: Passagem de parâmetros por referência exige uma variável");
							else
								reporter.reportarErro(
										"Chamada: Uma constante não pode ser passada por referência para uma subrotina");

					if (!t2.equals(t1))
						reporter.reportarErro("Chamada: Tipo do parâmetro real difere do tipo do parâmetro formal");
				}
		} else
			reporter.reportarErro("Chamada: Subrotina não declarada");

		return t;
	}

	public Object visitCom(Com com) {
		com.com.accept(this);
		return null;
	}

	public Object visitASSIGN(ASSIGN comandoAtribuicao) {
		ITSemantico t1 = (ITSemantico) comandoAtribuicao.var.accept(this);
		ITSemantico t2 = (ITSemantico) comandoAtribuicao.exp.accept(this);

		if (ambienteVarCons.isDeclarada(comandoAtribuicao.var.getId())) {
			if (ambienteVarCons.lookup(comandoAtribuicao.var.getId()).isCons)
				reporter.reportarErro("Atribuição: Impossível atribuir novo valor a uma constante");
			else {
				if (TBase.isReal(t1) && TBase.isInt(t2))
					t2 = this.toReal(comandoAtribuicao.exp);

				if (!t1.equals(t2))
					reporter.reportarErro(
							"Atribuição: Atribuir um " + t2 + " a um " + t1 + " não é uma operação válida");
			}
		}
		return null;
	}

	public Object visitCHAMADA(CHAMADA comandoChamada) {
		VinculavelFunProc vinculo = ambienteSubRotinas.lookup(comandoChamada.id);

		if (vinculo != null)
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
							if (!vinculo.params.get(i).isCons)
								reporter.reportarErro(
										"Chamada: Passagem de parâmetros por referência exige uma variável");
							else
								reporter.reportarErro(
										"Chamada: Uma constante não pode ser passada por referência para uma subrotina");

					if (!t2.equals(t1))
						reporter.reportarErro("Chamada: Tipo do parâmetro real difere do tipo do parâmetro formal");
				}
		else
			reporter.reportarErro("Chamada: Subrotina não declarada");

		return null;
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
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(cons.id))
			reporter.reportarErro("Cons: Identificador já declarado");
		else {
			ITSemantico t1 = (ITSemantico) cons.tipo.accept(this);
			ITSemantico t2 = (ITSemantico) cons.cons.accept(this);

			if ((TBase.isReal(t1)) && (TBase.isInt(t2)))
				this.toReal(cons.cons);

			if (!t1.equals(t2))
				reporter.reportarErro("Cons: A constante não pode ser incializado com " + t2);

			ambienteVarCons.put(cons.id, new VinculavelVarCons(t1, false));
		}
		return null;
	}

	public Object visitConsComp(ConsComp consComp) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(consComp.id))
			reporter.reportarErro("ConsComp: Identificador já declarado");
		else {
			ITSemantico tipo = (ITSemantico) consComp.tipo.accept(this);
			ambienteVarCons.put(consComp.id, new VinculavelVarCons(tipo, true));
		}
		return null;
	}

	public Object visitConsExt(ConsExt consExt) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(consExt.id))
			reporter.reportarErro("ConsExt: Identificador já declarado");
		else {
			ITSemantico t1 = (ITSemantico) consExt.tipo.accept(this);

			int i = 0;
			ITSemantico t2;
			for (Exp exp : consExt.expList) {
				t2 = (ITSemantico) exp.accept(this);
				if ((TBase.isReal(t1)) && (TBase.isInt(t2)))
					this.toReal(exp);
				if (!t1.equals(t2))
					reporter.reportarErro("ConsExt: O " + i + "º elemento da lista não corresponde ao tipo" + t1);
				i++;
			}

			ambienteVarCons.put(consExt.id, new VinculavelVarCons(t1, false));

		}
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

		for (Parametro par : funcao.params) {
			par.accept(this);
		}

		ITSemantico t1 = (ITSemantico) funcao.exp.accept(this);
		ITSemantico t2 = (ITSemantico) funcao.tipo.accept(this);

		if (TBase.isInt(t1) && TBase.isReal(t2))
			t1 = this.toReal(funcao.exp);

		if (!t2.equals(t1))
			reporter.reportarErro("Funcao: Tipo da expressão não é compatível com o tipo de retorno da função");

		ambienteVarCons.closeScope();
		return t2;
	}

	public Object visitIndexada(Indexada indexada) {
		ITSemantico t1 = (ITSemantico) indexada.var.accept(this);
		if (t1 instanceof TBase)
			reporter.reportarErro("Idexada: Uma variável TipoBase não pode ser indexada");
		else {
			TArray t1AsTArray = ((TArray) t1).clone();

			if (!t1AsTArray.evoluirIndexacao()) {
				reporter.reportarErro("Idexada: Tentando acessar a " + t1AsTArray.level + "ª dimensão de um array de "
						+ t1AsTArray.dim + " dimensões");
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
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(parBaseCopia.id))
			reporter.reportarErro("ParBaseCopia: Identificador já declarado");
		else
			ambienteVarCons.put(parBaseCopia.id, new VinculavelParam(tipoSemantico(parBaseCopia.tipo), false));
		return null;
	}

	public Object visitParBaseRef(ParBaseRef parBaseRef) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(parBaseRef.id))
			reporter.reportarErro("ParBaseRef: Identificador já declarado");
		else
			ambienteVarCons.put(parBaseRef.id, new VinculavelParam(tipoSemantico(parBaseRef.tipo), true));
		return null;
	}

	public Object visitParArrayCopia(ParArrayCopia parArrayCopia) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(parArrayCopia.id))
			reporter.reportarErro("ParArrayCopia: Identificador já declarado");
		else
			ambienteVarCons.put(parArrayCopia.id,
					new VinculavelParam(new TArray(tipoSemantico(parArrayCopia.tipo), parArrayCopia.dim), false));
		return null;
	}

	public Object visitParArrayRef(ParArrayRef parArrayRef) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(parArrayRef.id))
			reporter.reportarErro("parArrayRef: Identificador já declarado");
		else
			ambienteVarCons.put(parArrayRef.id,
					new VinculavelParam(new TArray(tipoSemantico(parArrayRef.tipo), parArrayRef.dim), true));
		return null;
	}

	public Object visitProcedimento(Procedimento procedimento) {
		ambienteVarCons.openScope();

		for (Parametro par : procedimento.params) {
			par.accept(this);
		}

		procedimento.com.accept(this);
		ambienteVarCons.closeScope();
		return null;
	}

	public Object visitPrograma(Programa programa) {
		for (Dec d : programa.decList) {
			this.registrarSeSubRotina(d);
		}

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
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(varInic.id))
			reporter.reportarErro("VarInic: Identificador já declarado");
		else {
			ITSemantico t1 = (ITSemantico) varInic.tipo.accept(this);
			ITSemantico t2 = (ITSemantico) varInic.ini.accept(this);

			if ((TBase.isReal(t1)) && (TBase.isInt(t2)))
				this.toReal(varInic.ini);

			if (!t1.equals(t2))
				reporter.reportarErro("VarInic: A variável não pode ser inicializado com " + t2);

			ambienteVarCons.put(varInic.id, new VinculavelVarCons(t1, false));
		}
		return null;
	}

	public Object visitVarInicComp(VarInicComp varInicComp) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(varInicComp.id))
			reporter.reportarErro("VarInicComp: Identificador já declarado");
		else {
			ITSemantico tipo = (ITSemantico) varInicComp.tipo.accept(this);
			ambienteVarCons.put(varInicComp.id, new VinculavelVarCons(tipo, false));
		}
		return null;
	}

	public Object visitVarInicExt(VarInicExt varInicExt) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(varInicExt.id))
			reporter.reportarErro("VarInicExt: Identificador já declarado");
		else {
			ITSemantico t1 = (ITSemantico) varInicExt.tipo.accept(this);

			int i = 0;
			ITSemantico t2;
			for (Exp exp : varInicExt.expList) {
				t2 = (ITSemantico) exp.accept(this);
				if ((TBase.isReal(t1)) && (TBase.isInt(t2)))
					this.toReal(exp);
				if (!t1.equals(t2))
					reporter.reportarErro("VarInic: O " + i + "º elemento da lista não corresponde ao tipo" + t1);
				;
				i++;
			}

			ambienteVarCons.put(varInicExt.id, new VinculavelVarCons(t1, false));
		}
		return null;
	}

	public Object visitVarNaoInic(VarNaoInic varNaoInic) {
		if (this.ambienteVarCons.isDeclaradaNesteEscopo(varNaoInic.id))
			reporter.reportarErro("VarNaoInic: Identificador já declarado");
		else {
			ITSemantico tipo = (ITSemantico) varNaoInic.tipo.accept(this);
			ambienteVarCons.put(varNaoInic.id, new VinculavelVarCons(tipo, false));
		}
		return null;
	}

	public Object visitSimples(Simples simples) {
		VinculavelVarCons vinculo = ambienteVarCons.lookup(simples.id);
		ITSemantico t = (vinculo != null) ? vinculo.tipo : null;

		if (t == null) {
			reporter.reportarErro("Simples: Identificador " + simples.id + " não declarado");
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

	private void registrarSeSubRotina(Dec d) {
		List<VinculavelParam> parLst = new ArrayList<VinculavelParam>();
		if (d instanceof Procedimento) {
			Procedimento proc = (Procedimento) d;
			for (Parametro par : proc.params) {
				if ((par instanceof ParBaseRef) || (par instanceof ParArrayRef))
					parLst.add(new VinculavelParam(tipoSemantico(par.tipo), true));
				else
					parLst.add(new VinculavelParam(tipoSemantico(par.tipo), false));
			}
			ambienteSubRotinas.put(proc.id, new VinculavelFunProc(parLst));
		} else if (d instanceof Funcao) {
			Funcao func = (Funcao) d;
			for (Parametro par : func.params) {
				if ((par instanceof ParBaseRef) || (par instanceof ParArrayRef))
					parLst.add(new VinculavelParam(tipoSemantico(par.tipo), true));
				else
					parLst.add(new VinculavelParam(tipoSemantico(par.tipo), false));
			}
			ambienteSubRotinas.put(func.id,
					new VinculavelFunProc(parLst, new VinculavelVarCons((ITSemantico) func.tipo.accept(this), true)));
		}
	}
}
