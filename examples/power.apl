main() = power(2, 1)

power(x, p) = _power(x, p, 1, 1)

_power(x, p, i, res) =
                    if i > p then
                        res
                    else
                        _power(x, p, i+1, res*x)
                    endif


