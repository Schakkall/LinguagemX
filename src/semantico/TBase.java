package semantico;

public enum TBase implements ITSemantico {
	INT, BOOL, REAL;

	public boolean equals(ITSemantico b) {
		return (b instanceof TBase) ? this == (TBase) b : false;
	}

	public static boolean isNum(ITSemantico b) {
		if (b instanceof TBase)
			return (b.equals(TBase.INT) || b.equals(TBase.REAL)) ? true : false;
		else
			return false;
	}

	public static boolean isBool(ITSemantico b) {
		if (b instanceof TBase)
			return (b.equals(TBase.BOOL)) ? true : false;
		else
			return false;
	}

	public static boolean isInt(ITSemantico b) {
		if (b instanceof TBase)
			return (b.equals(TBase.INT)) ? true : false;
		else
			return false;
	}

	public static boolean isReal(ITSemantico b) {
		if (b instanceof TBase)
			return (b.equals(TBase.REAL)) ? true : false;
		else
			return false;
	}	
	
}
