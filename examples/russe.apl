main() = russe(45,19)

russe(x,y) = _russe(x,y, y)

_russe(x,y, sum) = if x=0 then
                    sum
                   else 
                    if x//2%2=0 then
                      _russe(x//2, y*2, sum)
                    else 
                      _russe(x//2, y*2, sum+y*2)
                    endif
                   endif