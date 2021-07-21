main() = bin(5) 

bin(n) = _bin(5, '')

_bin(n, str) = 
            if n = 0 then
                str 
            else 
                if n % 2 = 0 then
                    _bin(n//2, '0' + str) 
                else
                    _bin(n//2, '1' + str)
                endif
            endif
              

