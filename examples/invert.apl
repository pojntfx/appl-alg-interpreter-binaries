main() = invert('jakob')

invert(str) = _invert(str, '', 0)

_invert(str, res, i) =  
                        if length(str) = i then
                            res
                        else 
                            _invert(str, str[i] + res, i+1)
                        endif