main() = find([1,5,3,4,5,6], 5)

find(arr, x) = _find(arr, x, 0)

_find(arr, x, i) =
                if i > length(arr) - 1 then
                    -1
                else
                    if arr[i] = x then
                        i
                    else
                        _find(arr, x, i+1)
                    endif
                endif
