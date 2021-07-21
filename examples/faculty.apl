main() = faculty(4)

faculty(a) = _faculty(a, 1)

_faculty(a, res) = 
            if a = 0 then
                res
            else 
                _faculty(a-1, res*a)
            endif

