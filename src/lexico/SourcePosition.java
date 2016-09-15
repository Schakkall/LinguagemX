package lexico;

public class SourcePosition {
	public int row, col;
	
	public SourcePosition(){
		this.row = 0;
		this.col = 0;
	}
	
	public SourcePosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public String toString(){
		return "[" + this.row + "," + this.col + "]";
	}
}
