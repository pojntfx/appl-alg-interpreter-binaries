main() = divisor(12, 6)

divisor(x, y) = 
                if y = 0 then
                    x
                else 
                    if (x > y) then 
                        divisor(y, x%y)
                    else 
                        divisor(x, y%x)
                    endif
                endif