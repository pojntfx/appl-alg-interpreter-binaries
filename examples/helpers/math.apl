
min(a,b) = if a < b then a else b endif
max(a,b) = if a < b then b else a endif

ln(x) = ln_series(x-1,1,0)

ln_series(x,i,res) =    if i=12 then     # Reihe für ln(1+x)
                      res
                   else
                      ln_series(x,i+1,res - (-1)^i*(x^i)/fact(i))
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

