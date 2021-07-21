main() = del('Ha     llo We   lt!   ')


del(str) = _del(str, 0, '') 

_del(str, i, new) = if i = length(str) then
                        new 
                    else 
                        if isWhiteSpace(str[i]) = false then
                            _del(str, i+1, new + str[i])
                        else 
                            _del(str, i+1, new)
                        endif
                    endif

isWhiteSpace(ch) = if ch = ' ' then 
                    true
                   else
                    if ch = '\n' then
                      true 
                    else
                      if ch = '\t' then
                        true
                      else  
                        false 
                      endif
                    endif
                   endif