main() = quer([1,2,3])

quer(list) = _quer(list, 0, 0)

_quer(list, sum, i) = 
                    if i = length(list) then
                        sum
                    else
                        _quer(list, sum + list[i], i+1)
                    endif       
                