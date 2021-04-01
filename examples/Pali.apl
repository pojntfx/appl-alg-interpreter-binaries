main() = pali('dieliebeistsiegerregeistsiebeileid')

pali(s)  =  _pali(s,0)

_pali(s,i)  =  if i > length(s)/2 then
                  true
               else
                  if s[i] != s[length(s)-i-1] then
                     false
                  else
                     _pali(s,i+1)
                  endif
               endif
                  