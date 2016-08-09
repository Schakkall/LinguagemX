package checagem;

public enum TBase implements ITipoSemantico {
	INT,
	BOOL,
	REAL;
	
	public boolean equals(TBase b){
		return (this == b);
	}
}
