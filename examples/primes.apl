main() = primes(100)

primes(n) = _primes(n, 2, [])

_primes(n, i, arr) = if i>n then
                            arr
                        else 
                            if _isPrime(i)=true then
                                _primes(n, i+1, i->arr[length(arr)])
                            else 
                                _primes(n, i+1, arr)
                            endif
                        endif

_isPrime(i) = __isPrime(i,2)

__isPrime(i,x) = if x^2 > i then 
                    true
                 else 
                    if i%x = 0 then 
                        false
                    else 
                        __isPrime(i,x+1)
                    endif
                endif

                
                
