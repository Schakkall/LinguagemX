package semantico;

import sintaxeAbstrata.BinOp;

public abstract class SemanOper {
	public static boolean isBoolOper(BinOp b) {
		switch (b) {
		case DIV:
		case SOM:
		case MUL:
		case SUB:
			return false;
		case MOD:
			return false;
		case IGUAL:
			return true;
		case MENOR:
			return false;
		case E:
		case OU:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isRelaOper(BinOp b) {
		switch (b) {
		case DIV:
		case SOM:
		case MUL:
		case SUB:
			return false;
		case MOD:
			return false;
		case IGUAL:
			return true;
		case MENOR:
			return true;
		case E:
		case OU:
			return false;
		default:
			return false;
		}
	}
	
	public static boolean isNumOper(BinOp b) {
		switch (b) {
		case DIV:
		case SOM:
		case MUL:
		case SUB:
			return true;
		case MOD:
			return true;
		case IGUAL:
			return false;
		case MENOR:
			return false;
		case E:
		case OU:
			return false;
		default:
			return false;
		}
	}	
}
