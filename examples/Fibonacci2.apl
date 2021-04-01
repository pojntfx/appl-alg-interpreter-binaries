

## Berechnet n-te Fibonacci-Zahl

main() = fibo2(7)
#--------------------

fibo2(n) = _fibo2(n,0,1)

_fibo2(n,a,b) = if n=1 then a
                else _fibo2(n-1,b,a+b)
                endif