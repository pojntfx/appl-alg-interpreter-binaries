main() = avg([7, 13, 2, 1, 6, 5])

avg(list) = _avg(list, 0, 0)

_avg(list, res, i) = 
                    if length(list) = i then
                        res / length(list)
                    else 
                        _avg(list, res+list[i], i+1)
                    endif
