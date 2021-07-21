main() = quer(1234)

quer(n) = _quer(n, 0)

_quer(n, sum) =  
                if n = 0 then 
                    sum
                else 
                    _quer(n/10, sum+n%10)
                endif
                