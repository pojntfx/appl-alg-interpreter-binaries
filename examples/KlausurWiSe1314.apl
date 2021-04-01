#  Stringumkehr:  'Hallo Welt' -->  'tleW ollaH'

main() = bubble([3,7,2,9,4,0,-3,8,12,14,16,-1])

bubble(list) = _bubble(list,length(list),0)

_bubble(list,len,i) = if len=1 then
                         list
                      else
                         if i=len-1 then
                            _bubble(list,len-1,0)
                         else
                            if list[i]>list[i+1] then
                               _bubble(flip(list,i),len,i+1)
                            else
                               _bubble(list,len,i+1)
                            endif
                         endif
                      endif
                      
flip(list,i) = _flip(list,i,list[i])
_flip(list,i,tmp) = tmp -> (list[i+1] -> list[i])[i+1]
 














#sqrt(220000000,6)

sqrt(N,S) = if N<0 then
               'i*' + _sqrt(-N,S,0,0,0)
            else
               _sqrt(N,S,0,0,0)
            endif

# d = digit
# i = Nr. der Nachkommastelle

_sqrt(N,S,d,i,res) = if i>S then
                        res
                     else
                        if (res + d*10^(-i))^2 > N then
                           _sqrt(N,S,0,i+1,res+(d-1)*10^(-i))
                        else
                           _sqrt(N,S,d+1,i,res)
                        endif
                     endif
                       
                        
                        




















#revert('Hallo Welt')

revert(s) = _revert(s, '', length(s)-1)

_revert(s,res,i) = if i=-1 then
                      res
                   else
                      _revert(s, res + s[i], i-1)
                   endif

