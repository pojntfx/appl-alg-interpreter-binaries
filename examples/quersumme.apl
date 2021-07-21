main() = quer([1,2,3,4,5,6,7,8,9])

quer(arr) = _quer(arr, 0, 0)

_quer(arr, res, i) = if i = length(arr) then
                        res
                     else 
                        _quer(arr, res+arr[i], i+1) 
                     endif