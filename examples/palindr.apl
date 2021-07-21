main() = pali('lagerregal')

pali(str) = _pali(str, 0)

_pali(str, i) = 
                if length(str) = i then
                    true 
                else 
                    if str[i] != str[length(str)-1-i] then
                      false
                    else
                        _pali(str, i+1)
                    endif
                endif

