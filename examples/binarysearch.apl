main() = binsearch([1,2,3,4,5,7], 7)

binsearch(feld, zahl) = _binsearch(feld, zahl, 0, length(feld)-1)

_binsearch(feld, zahl, start_i, end_i) =
                        if start_i > end_i then
                            –1
                        else
                            if zahl = feld[(start_i+end_i)//2] then
                                (start_i+end_i)//2
                            else
                                if zahl < feld[(start_i+end_i)//2] then
                                    _binsearch(feld, zahl, start_i, (start_i+end_i)//2-1)
                                else
                                    _binsearch(feld, zahl, (start_i+end_i)//2+1, end_i)
                                endif
                            endif
                        endif
