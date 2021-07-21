main() = binary(4)

binary(n) = _binary(n, '')

_binary(n, result) = 
            if n < 1 then 
                result
            else 
                if n % 2 = 0 then
                    _binary(n//2, '0' + result)
                else 
                    _binary(n//2, '1' + result)
                endif
            endif
