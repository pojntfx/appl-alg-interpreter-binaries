main() = quer([5, 12, 2])

quer(n) = 
        if length(n) = 1 then
            n[0]
        else 
            quer(toArray(quersumme(n)))
        endif

quersumme(n) = _quersumme(n, 0, 0)

_quersumme(n, res, i) = 
                        if length(n) = i then
                            res
                        else 
                            _quersumme(n, res + n[i], i+1)
                        endif

toArray(n) = _toArray(n, [])

_toArray(n, res) =
                if n = 0 then 
                    res
                else 
                    _toArray(n//10, n % 10 + res)
                endif
