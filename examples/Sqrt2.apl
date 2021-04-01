main() = sqrt2(2,30,[1,2])

sqrt(n,prec) = _sqrt(n,prec,0,0,0)

_sqrt(n,prec,result,stelle,i) = if stelle=prec then
                                   result
                                else
                                   if (result + i*10^(-stelle))^2 > n then
                                      _sqrt(n, prec, result + (i-1)*10^(-stelle), stelle+1, 0)
                                   else
                                      _sqrt(n, prec, result, stelle, i+1)
                                   endif
                                endif
                                      

sqrt2(n,prec,interval) = _sqrt2(n,prec,interval,1)                                  # prec = Anzahl Schritte

_sqrt2(n,prec,interval,i) = if i=prec then
						       interval
						    else
						       if ((interval[0]+interval[1])/2)^2 > n then
						          _sqrt2(n,prec,    (interval[0]+interval[1]) / 2 -> interval[1]    , i+1)
						       else 
						          _sqrt2(n,prec,    (interval[0]+interval[1]) / 2 -> interval[0]    , i+1)
						       endif
						    endif 
						    
						    
						    
						    
						    
						    
						    
						    
						                          