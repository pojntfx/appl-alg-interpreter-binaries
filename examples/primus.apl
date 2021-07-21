main() = prime(13)

prime(n) = _prime(n, 2)

_prime(n, i) = 
            if i = n then
                true     
            else 
                if n % i = 0 then
                    false
                else 
                    _prime(n, i+1)
                endif
            endif
            