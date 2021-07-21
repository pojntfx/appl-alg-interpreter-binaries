main() = powers(3, 3)

powers(number, n) = _powers(number, n, 1, number) 

_powers(number, n, index, result) = if index = n then
                                result
                            else 
                                _powers(number, n, index+1, result*number)
                            endif