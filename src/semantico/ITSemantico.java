package semantico;

public interface ITSemantico {
	public abstract boolean equals(ITSemantico a);
	
	default boolean compatible(ITSemantico a){
		if (TBase.isBool(this) && TBase.isBool(a))
			return true;
		else
		if (TBase.isNum(this) && TBase.isNum(a))
			return true;
		else
		if (TArray.isListNum(this) && TArray.isListNum(a))
			return false;//Pode fazer sentido ser true no futuro
		else
		if (TArray.isListBool(this) && TArray.isListBool(a))
			return false;//Pode fazer sentido ser true no futuro		
		else
			return false;
	}

}
