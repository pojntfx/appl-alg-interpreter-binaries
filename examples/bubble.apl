use 'appls/appl_algs/helpers/list.apl'

main() = bubble([4, 3, 2, 5])

bubble(arr) = _bubble(arr, 0, length(arr)-1)

_bubble(arr,i,iterations) =  
                            # stop if we went length(arr) times through the array
                            if iterations=0 then 
                                arr
                            else
                                # only go until the point after which already sorted
                                if i<iterations then
                                    if arr[i]>arr[i+1] then
                                        _bubble(change(arr,i,i+1),i+1,iterations)
                                    else
                                        _bubble(arr,i+1,iterations)
                                    endif
                                else
                                    # go to the beginning again and set the sorted boundary one to the left
                                    _bubble(arr,0,iterations-1)
                                endif
                            endif


            



# [4, 3, 2, 5]

# [3, 4, 2, 5]

# [3, 2, 4, 5]

# [2, 3, 4, 5]