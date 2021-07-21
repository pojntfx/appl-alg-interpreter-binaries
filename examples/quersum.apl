main() = quer([7,13,2,1,6,5])

quer(list) = _quer(list, 0, 0)

_quer(list, res, index) =
                        if index = length(list) then   
                            res
                        else 
                            _quer(list, res + list[index], index+1)
                        endif