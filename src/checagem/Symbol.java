package checagem;

import java.util.HashMap;
import java.util.Map;

public class Symbol {
	private String name;
	private int hashCd;
	private static Map<String,Symbol> dict =
			new HashMap<String,Symbol>();
	
	private Symbol (String n) {
		name=n; 
		hashCd = hashcode_(n); 
	}
	
	public String toString() {
		return name; 
	}
	
	public static Symbol symbol(String n) {
		Symbol s = dict.get(n);
		if (s==null) { 
			s = new Symbol(n); 
			dict.put(n,s); 
		}
		return s; 
	}
	
	private int hashcode_(String s) { 
		int h=0;
		for (int i = 0; i < s.length(); i++)
			h= h*65599 + s.charAt(i);
		return h;
	}
	
	public int hashcode () { 
		return hashCd; 
	}
}