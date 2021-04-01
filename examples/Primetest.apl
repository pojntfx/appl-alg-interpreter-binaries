main() = isPrime(38)

isPrime(t) = _isPrime(t,2)
_isPrime(t,i) = if  i^2 > t  then
                   true
                else
                   if  t%i = 0  then
                       false
                   else
                       _isPrime(t,i+1)
                   endif
                endif            