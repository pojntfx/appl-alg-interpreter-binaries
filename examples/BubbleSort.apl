use 'appl_algs/helpers/list.apl'

main() = bubblesort([5, 550, 725, 883, 712, 633, 854, 162, 533, 654, 214, 413, 393, 383, 688, 960, 84])

bubblesort(arr) = _bubblesort(arr,0,length(arr)-1)

# Paare (i,i+1) 

_bubblesort(arr,i,j) =  if j=0 then 
                            arr
                        else
                            if i<j then
                                if arr[i]>arr[i+1] then
                                    _bubblesort(change(arr,i,i+1),i+1,j)
                                else
                                    _bubblesort(arr,i+1,j)
                                endif
                            else
                                _bubblesort(arr,0,j-1)
                            endif
                        endif
                        

