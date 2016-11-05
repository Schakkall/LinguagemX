procedure fibonacci(int n, var int res){ 
	var int vFib1 := 1; 
	var int	vFib2 := 0; 
	var int vAuxF := 0; 
	while (vFib2 < n) { 
		vAuxF := vFib1 + vFib2; 
		vFib1 := vFib2; 
		vFib2 := vAuxF; 
	}
	res := vFib2; 
}

function int teste(var int[][][] q)
	q[0][0][0] + 5;

 
procedure main(){  
	var int res;
	fibonacci(2, res);
	
	var int a, b, c := 8, d := 5;	
	var int[5] z := [1, 2, 3, 4, 5];
	var int[10][10][10] x;
	var bool[2] b := [false, true], a, c, d; 
	x[5][5][5] := teste(x);
	
	if (b[0]) then
		if (b[1]) then
			fibonacci(3, res);
		else
			fibonacci(4, res);			
			
	if (x[0][0][0] = z[0]) then {
 		x[5][5][5] := teste(x);
 		x[5][5][5] := teste(x);
 	}
 	else
 		x[5][5][5] := teste(x);
	 		
}
