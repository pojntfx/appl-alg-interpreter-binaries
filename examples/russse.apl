main() = russe(45, 19)

russe(x, y) = _russe(x, y, 0)

_russe(x, y, sum) = if x = 1 then
                        sum + y
                    else 
                        if x % 2 = 0 then
                            _russe(x//2, y*2, sum)
                        else 
                            _russe(x//2, y*2, sum+y)
                        endif
                    endif