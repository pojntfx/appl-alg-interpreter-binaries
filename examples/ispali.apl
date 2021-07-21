main() = ispali('aa')


ispali(word) = _ispali(word, 1)

_ispali(word, index) =  
                    if length(word) != 1 then
                        if length(word) != 0 then 
                            if word[index-1] != word[length(word)-index] then
                                false
                            else 
                                if index > length(word)//2 then
                                    true
                                else 
                                    _ispali(word,index+1)
                                endif
                            endif
                        else 
                            false
                        endif
                    else 
                        true
                    endif
                       
            