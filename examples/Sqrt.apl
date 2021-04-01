
main() = sqrt(5, 1E-15)
#----------------------

sqrt(n, e) = sqrt_(n,e,0,0,0) 

sqrt_(n, e, s, d, res) =
	
	if 10^(-s+1) < e then res
	else
		if (res + d*10^-s)^2 > n then
			sqrt_(n,e,s+1,0,res+(d-1)*10^-s)
		else
			sqrt_(n,e,s,d+1,res) 
		endif
	endif