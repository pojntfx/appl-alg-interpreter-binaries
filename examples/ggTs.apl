main() = ggt(12,8) 

ggt(a, b) = 
            if b = 0 then 
                a
            else 
                if a < b then 
                    ggt(a,b%a)
                else 
                    ggt(b,a%b)
                endif
            endif


