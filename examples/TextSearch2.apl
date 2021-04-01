main() = find('Hallo Welt', 'Welten')  # -> 6

find(t,m) = _find(t,m,0,0)    #  _find(t,m,i,j): i t-index, j m-index

_find(t,m,i,j) = if i > length(t) - length(m) then
                    'NOT FOUND'
                 else
                    if t[i+j] != m[j] then
                       _find(t,m,i+1,0)
                    else
                       if j = length(m) - 1 then
                          'FOUND AT ' + i
                       else
                          _find(t,m,i,j+1)
                       endif
                    endif
                 endif                