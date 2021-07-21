main() = rek([1,4,6])

rek(list) = if length(list) = 1 then
                list[0]
            else 
                rek([quersumme(list)])
            endif

quersumme(list) = _quersumme(list, 0, 0)

_quersumme(list, res, i) = 
                        if length(list) = i then
                            res
                        else 
                            _quersumme(list, res + list[i], i+1)
                        endif
                        