var int x, y, z := 12;
var bool b := true;

procedure teste2(int p0){  
	p0 := p0 * 10;
}

procedure teste(int p0, int p1, int p2){  
	var int p3 := 5;
	p3 := p3 + 2;
	teste2(p3);
}

function int vezes10(int x)
	x * 10;


procedure main(){  
	var int d0 := 12;
	cons int d1 := vezes10(d0);
	teste(d1 + d0, 10 + 1, 10 + 2);
	var int d2 := d0 * d1 * 10;
	teste(d1 + d0, 10 + 1, 10 + 2);
	var int d3 := -15;

	if (d0 = 11) then
		d0 := d0 - 1;
	else
		if (b and true) then
			d0 := d0 - 2;

	y := d0 + d1 + d2;

	while (!(d0 = 0)) {
		d0 := d0 - 1;
	}
}