main() = primes(10)

primes(n) = _primes(n, 2, [])

_primes(n, counter, arr) = if counter > n then
                                arr
                            else
                                if isPrime(counter)=true then
                                    _primes(n, counter+1, counter->arr[length(arr)])
                                else 
                                    _primes(n, counter+1, arr)
                                endif
                            endif



isPrime(n) = _isPrime(n, 2)

_isPrime(n, x) = if x * x > n then
                    true
                else 
                    if n % x = 0 then
                        false
                    else 
                        _isPrime(n, x+1)
                    endif
                endif

