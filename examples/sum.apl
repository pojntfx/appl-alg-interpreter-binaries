main() = sum(10)

sum(n) = _sum(n, 0) 
                                   
_sum(n, result) = 
                 if n = 0 then
                     result 
                 else 
                     _sum(n-1, result + n)
                 endif