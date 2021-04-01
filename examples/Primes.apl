
main() = primes(50)
#------------------

primes(N) = _primes(N, 2, [])

_primes(N, t, p_list) = if t>N then p_list
                        else
                        	if _isPrime(t)=true then
                        		_primes(N, t+1, t->p_list[length(p_list)])
                        	else
                        		_primes(N, t+1, p_list)
                        	endif
                        endif
                        
_isPrime(t) = __isPrime(t, 2)

__isPrime(t, x) = if x^2 > t then true
				  else if t%x = 0 then false
				       else
				          __isPrime(t, x+1)
				       endif
				  endif
