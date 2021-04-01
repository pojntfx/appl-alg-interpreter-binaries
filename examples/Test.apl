
( 5 + 3 )






Hallo \n



main() = length(primeList(1600))

primeList(N)  =  _primeList(N,[],2)

_primeList(N,list,zahl)  =  if zahl > N then
                               list
                            else
                               if isPrime(zahl) then
                                  _primeList(N, zahl->list[length(list)] , zahl+1)  
                               else 
                                  _primeList(N, list , zahl+1)
                               endif
                            endif   



isPrime(n) = if n=2 then 
                true
             else
                _isPrime(n,2)
             endif   

_isPrime(n,t) =  if t^2 > n then
                    true
                 else
                    if n%t = 0 then
                       false
                    else
                       _isPrime(n,t+1)
                    endif
                 endif      


