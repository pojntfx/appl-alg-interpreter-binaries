main() = russe(45, 19)

russe(a, b) = _russe(a, b, 0)

_russe(a, b, sum) = if a = 1 then
                        sum + b
                    else 
                        if a % 2 = 0 then
                            _russe(a//2, b*2, sum)
                        else 
                            _russe(a//2, b*2, sum+b)
                        endif
                    endif