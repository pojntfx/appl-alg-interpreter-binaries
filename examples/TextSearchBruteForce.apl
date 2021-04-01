

main() = search('Hallo Welt !!', 'lxo')

search(t,m) = _search(t,m,0,0)

_search(t,m,i,j) = if i > length(t) - length(m) then
                      'NOT_FOUND'
                   else
                      if m[j] != t[i+j] then
                         _search(t,m,i+1,0)
                      else
                         if j = length(m)-1 then
                            i
                         else
                            _search(t,m,i,j+1)
                         endif
                      endif
                   endif