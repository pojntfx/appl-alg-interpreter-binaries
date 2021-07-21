main() = count(5)

count(n) = _count(n, 0, [])

_count(n, i, list) = 
                    if length(list) = n then
                        list
                    else 
                        _count(n, i+1, i+1 -> list[length(list)])
                    endif 