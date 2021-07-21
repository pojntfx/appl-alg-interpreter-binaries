main() = fibo(3)

fibo(n) = _fibo(n, 0, 1)

_fibo(n,a,b) = if n = 1 then 
                a
               else 
                _fibo(n-1,b,a+b)
               endif
