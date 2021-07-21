main() = binarySearch(6, [1,2,4,6])

binarySearch(x, A) = _binarySearch(x, A, 0, length(A)-1)

_binarySearch(x, A, start_i, end_i) =  
                                    if start_i > end_i then
                                        -1
                                    else 
                                      if x = A[(start_i + end_i)//2] then
                                        (start_i + end_i)//2
                                      else 
                                        if x < A[(start_i + end_i)//2] then
                                            _binarySearch(x, A, start_i, (start_i + end_i)//2-1)
                                        else 
                                            _binarySearch(x, A, (start_i + end_i)//2+1, end_i)
                                        endif
                                      endif
                                    endif