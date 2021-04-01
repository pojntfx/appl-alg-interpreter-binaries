
# Eindeutige Primfaktorzerlegung
# ------------------------------


main() =  pfd(1712347795)


pfd(x) = _pfd(x, x + ' = ', findFactor(x))

_pfd(x,result,f) = if f = 1 then
                    result + x
                 else
                    _pfd(x/f, result + f + ' * ', findFactor(x/f))
                 endif


findFactor(x) = _findFactor(x,2)

_findFactor(x,t) = if t*t > x then
                      1
                   else
                      if x%t = 0 then
                         t
                      else
                         _findFactor(x,t+1)
                      endif
                   endif















toString(nb) = _toString(nb, '')

_toString(nb, result) = if nb = 0 and result = '' then
                           '0'
                        else if nb = 0 then
                                result
                             else
                                _toString( nb // 10,                                                    digit(nb%10) + result )
                             endif
                        endif

digit(x) = if x = 0 then '0'
           else if x = 1 then '1'
                else if x = 2 then '2'
                     else if x = 3 then '3'
                          else if x = 4 then '4'
           else if x = 5 then '5'
           else if x = 6 then '6'
           else if x = 7 then '7'
           else if x = 8 then '8'
           else if x = 9 then '9'
           else 'Fehler'
           endif endif endif endif endif endif endif endif endif endif


















  #  element -> list[index]








