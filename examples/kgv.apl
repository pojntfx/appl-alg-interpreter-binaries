main() = kgv(13, 18)

kgv(a, b) = _kgv(a, b, 1)

_kgv(a, b, i) = 
    if (a * i) % b = 0 then 
        a * i
    else 
        _kgv(a, b, i+1)
    endif