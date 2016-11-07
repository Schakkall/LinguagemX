package interpretacao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excecoes.*;
import sintaxeAbstrata.*;
import semantico.TBase;
import utils.RegistradorDeErros;

public class XInterpreter implements XVisitor{
	
	public final String entryPoint = "main";
	public Memoria mem = new Memoria();
	Map<String, ISubrotina> subrotinas = new HashMap<String, ISubrotina>();
	RegistradorDeErros reporter = new RegistradorDeErros();
	
	public Object visitBinExp(BinExp binExp) {
		Value vEsq = (Value) binExp.esqExp.accept(this);
		Value vDir = (Value) binExp.dirExp.accept(this);
		Value vRes;
		
		switch (binExp.op) {
		case DIV:
			vRes = new IntVal((IntVal.getIntVal(vEsq) / IntVal.getIntVal(vDir)));
			break;
		case E:
			vRes = new BoolVal(BoolVal.getBoolVal(vEsq) && BoolVal.getBoolVal(vDir));
			break;
		case IGUAL:
			vRes = new BoolVal(IntVal.getIntVal(vEsq) == IntVal.getIntVal(vDir));
			break;
		case MENOR:
			vRes = new BoolVal(IntVal.getIntVal(vEsq) < IntVal.getIntVal(vDir));
			break;
		case MOD:
			vRes = new IntVal(IntVal.getIntVal(vEsq) % IntVal.getIntVal(vDir));
			break;
		case MUL:
			vRes = new IntVal(IntVal.getIntVal(vEsq) * IntVal.getIntVal(vDir));
			break;
		case OU:
			vRes = new BoolVal(BoolVal.getBoolVal(vEsq) || BoolVal.getBoolVal(vDir));
			break;
		case SOM:
			vRes = new IntVal(IntVal.getIntVal(vEsq) + IntVal.getIntVal(vDir));
			break;
		case SUB:
			vRes = new IntVal(IntVal.getIntVal(vEsq) - IntVal.getIntVal(vDir));
			break;
		default:
			vRes = new IntVal(0);
		}
	
		return vRes;
	}

	public Object visitBlocoExp(BlocoExp blocoExp) {
		for (DCons d: blocoExp.consList) {
			d.accept(this);
		}
		return blocoExp.exp.accept(this);
	}

	public Object visitChamadaExp(ChamadaExp chamadaExp) {
		ISubrotina func = subrotinas.get(chamadaExp.id);
		Value vRes = new IntVal(0);
		if (func instanceof Funcao) {
			this.passarParametros(((Funcao) func).params, chamadaExp.expLst);
			vRes = ((Value) ((Funcao) func).accept(this));
		}
		return vRes;
	}

	public Object visitCom(Com com) {
		com.com.accept(this);
		return null;
	}

	public Object visitASSIGN(ASSIGN comandoAtribuicao) {
		mem.setValue(((Endereco) comandoAtribuicao.var.accept(this)), ((Value) comandoAtribuicao.exp.accept(this)));
		return null;
	}

	public Object visitCHAMADA(CHAMADA comandoChamada) {
		ISubrotina proc = subrotinas.get(comandoChamada.id);
		if (proc instanceof Procedimento) {
			this.passarParametros(((Procedimento) proc).params, comandoChamada.expLst);
			((Procedimento) proc).accept(this);
		}
		return null;
	}

	public Object visitIF(IF comandoIf) {
		if (((BoolVal) comandoIf.condicao.accept(this)).b)
			comandoIf.comandoEntao.accept(this);
		else
			if (comandoIf.comandoSenao != null)
				comandoIf.comandoSenao.accept(this);
		return null;
	}

	public Object visitWHILE(WHILE comandoWhile) {
		while (((BoolVal) comandoWhile.condicao.accept(this)).b)
			comandoWhile.comando.accept(this);
		return null;
	}

	public Object visitCons(Cons cons) {
		mem.setValue(cons.endr, (Value) cons.cons.accept(this));
		return null;
	}

	public Object visitConsComp(ConsComp consComp) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitConsExt(ConsExt consExt) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
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
		Integer currentFrameSize = mem.getFrameSize();			
		mem.openFrame();
		mem.setFrameSize(funcao.frameSize());
		Value vRes = (Value) funcao.exp.accept(this);
		mem.setFrameSize(currentFrameSize);			
		mem.closeFrame();
		return vRes;
	}

	public Object visitIndexada(Indexada indexada) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitLiteralBool(LiteralBool literalBool) {
		return new BoolVal(literalBool.bool);
	}

	public Object visitLiteralInt(LiteralInt literalInt) {
		return new IntVal(literalInt.num);
	}

	public Object visitMenos(Menos menos) {
		return new IntVal(((IntVal) menos.exp.accept(this)).i * -1);
	}

	public Object visitNao(Nao nao) {
		return new BoolVal(!((BoolVal) nao.exp.accept(this)).b);
	}

	public Object visitParBaseCopia(ParBaseCopia parBaseCopia) {
		// TODO verificar se realmente esta visita não será necessária
		return null;
	}

	public Object visitParBaseRef(ParBaseRef parBaseRef) {
		// TODO verificar se realmente esta visita não será necessária
		return null;
	}

	public Object visitParArrayCopia(ParArrayCopia parArrayCopia) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitParArrayRef(ParArrayRef parArrayRef) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitProcedimento(Procedimento procedimento) {
		Integer currentFrameSize = mem.getFrameSize();
		mem.openFrame();
		mem.setFrameSize(procedimento.frameSize());
		procedimento.com.accept(this);
		mem.setFrameSize(currentFrameSize);			
		mem.closeFrame();		
		return null;
	}

	public Object visitPrograma(Programa programa) {
		for (Dec d: programa.decList) {
			if (d instanceof ISubrotina) {
				this.subrotinas.put(((ISubrotina)d).getId(), ((ISubrotina)d));
			} else
			if (d instanceof IDeclaracao)
				d.accept(this);
		}
		
		ISubrotina entry = this.subrotinas.get(this.entryPoint);
		if ((entry != null) && (entry instanceof Procedimento))
			((Procedimento) entry).accept(this);
		
		return null;
	}

	public Object visitTipoBase(TipoBase tipoBase) {
		// TODO verificar se realmente esta visita não será necessária
		return null;
	}

	public Object visitVarExp(VarExp varExp) {
		Endereco e = (Endereco) varExp.var.accept(this);
		return mem.getValue(e);
	}

	public Object visitVarInic(VarInic varInic) {
		mem.setValue(varInic.endr, (Value) varInic.ini.accept(this) );
		return null;
	}

	public Object visitVarInicComp(VarInicComp varInicComp) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object visitVarInicExt(VarInicExt inicExt) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitVarNaoInic(VarNaoInic varNaoInic) {
		if (TBase.isBool(this.tipoSemantico(varNaoInic.tipo)))
			mem.setValue(varNaoInic.endr, new BoolVal(false));
		else
		if (TBase.isInt(this.tipoSemantico(varNaoInic.tipo)))
			mem.setValue(varNaoInic.endr, new IntVal(0));
		else
		if (TBase.isReal(this.tipoSemantico(varNaoInic.tipo)))
			mem.setValue(varNaoInic.endr, null);
			
		return null;
	}

	public Object visitSimples(Simples simples) {
		return simples.endr;
	}

	public Object visitBLOCO(BLOCO bloco) {
		for (DVarConsCom comando: bloco.comList) {
			comando.accept(this);
		}
		return null;
	}

	public Object visitTipoArray(TipoArray tipoArray) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object visitIntToReal(IntToReal intToReal) {
		try {
			throw new NotImplemented();
		} catch (NotImplemented e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void passarParametros(List<Parametro> lPar, List<Exp> lExp){
		for (int i = 0; i < lExp.size(); i++) {
			Value v = (Value) lExp.get(i).accept(this);
			Endereco e = lPar.get(i).endr;
			mem.setValue(new Endereco(mem.getFrameSize() + e.getValue(), TEndereco.PILHA), v);
		}
	}
}
