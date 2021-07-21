main() = reverse('Hallo') 

reverse(word) = _reverse(word, '', length(word)-1)

_reverse(word, arr, index) = 
                        if length(arr) = length(word) then
                            arr
                        else 
                            _reverse(word, word[index] -> arr[length(arr)], index-1)
                        endif
                        
