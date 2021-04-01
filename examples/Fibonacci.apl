

## Berechnet n-te Fibonacci-Zahl

main() = fibo(495837)
#--------------------

fibo(n) = second(f(n))

second(pair) = pair[1]

next(pair) = [pair[1], pair[0]+pair[1]]

f(n) = if n = 0 then [0,0]
       else if n = 1 then [0,1]
            else next(f(n-1))
            endif
       endif
