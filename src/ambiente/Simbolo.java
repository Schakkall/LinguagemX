package ambiente;

import java.util.HashMap;
import java.util.Map;

public class Simbolo {
	private String id;
	private int hashCd;
	
	private static Map<String,Simbolo> dict =
			new HashMap<String,Simbolo>();

	private int hashcode_(String s) {
		int h = 0;
		for (int i = 0; i < s.length(); i++)
			h = h * (65599) + s.charAt(i);
		return h;
	}
	
	private Simbolo (String n) {
		this.id=n; 
		this.hashCd = this.hashcode_(n); 
	}
	
	public String toString() {
		return this.id; 
	}
	
	public static Simbolo getSimbolo(String n) {
		Simbolo s = dict.get(n);
		if (s==null) { 
			s = new Simbolo(n); 
			dict.put(n,s); 
		}
		return s; 
	}
	
	public int hashcode() { 
		return hashCd; 
	}	
}
