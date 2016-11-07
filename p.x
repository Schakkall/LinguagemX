procedure teste(int p0, int p1, int p2){  
	var int p3 := 5;
	p3 := p3 + 2;
}

procedure main(){  
	var int d0 := 11;
	var int d1 := d0 * 10;
	teste(d1 + d0, 10 + 1, 10 + 2);
	var int d2 := d0 * d1 * 10;
	teste(d1 + d0, 10 + 1, 10 + 2);

	if (d0 = 11) then
		d0 := d0 - 1;
	else
		d0 := d0 + 1;

	while (!(d0 = 0)) {
		d0 := d0 - 1;
	}
}