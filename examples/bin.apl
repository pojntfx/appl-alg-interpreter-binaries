main() = bin(16)

bin(n) = _bin(n, '')

_bin(n, res) = 
            if n = 0 then
                res
            else
                if n % 2 = 0 then
                    _bin(n//2, '0' + res)
                else
                    _bin(n//2, '1' + res)
                endif
            endif
            

# 4 2 1 

# wenn wir nach dem ersten bit
# wenn wir das zweite mal teile nach dem zweiten
# wir gehen also von rechts nach links 
# beim zweiten mal durch zwei teilen, haben wir dann durch vier geteilt und wissen ob die zahl durch vier teilbar ist