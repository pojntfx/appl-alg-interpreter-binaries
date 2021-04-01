
main() = ln(2.7182818284)

#exp(2)



#----------------------

ln(x) = ln_(x,0,0,0) 

ln_(x, s, d, res) =
	
	if 10^(-s+1) < 1E-15 then 
	   res
	else
		if exp(res + d*10^-s) > x then
			ln_(x,s+1,0,res+(d-1)*10^-s)
		else
			ln_(x,s,d+1,res) 
		endif
	endif
	
exp(x) = _exp(x,0,0)

_exp(x,i,res) =    if i=12 then
                      res
                   else
                      _exp(x,i+1,res + (x^i)/fact(i))
                   endif
                   
fact(n) =   if n=0 then
               1
            else
               n*fact(n-1)
            endif      





	