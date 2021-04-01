

## Wandelt Integerzahl in Binärdarstellung um

main() = int2bin(147)
#--------------------

int2bin(input) = _int2bin(input, 0, '')

_int2bin(input, i, res) = if input < 1 then res
                          else
                             if input%2 = 0 then
                                _int2bin(input//2, i+1, '0' + res)
                             else                                
                                _int2bin(input//2, i+1, '1' + res)
                             endif
                          endif