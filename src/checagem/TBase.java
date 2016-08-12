package checagem;

public enum TBase implements ITipoSemantico {
	INT,
	BOOL,
	REAL;
	
	public boolean equals(ITipoSemantico b){
		return (this == (TBase)b);
	}
}
