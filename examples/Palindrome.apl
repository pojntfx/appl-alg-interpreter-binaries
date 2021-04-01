
main() = isPalindrom('hannah')

isPalindrom(s) = if s = reverse(s) then s + ' is a palindrome.'
                 else s + ' is not a palindrome.'
                 endif


reverse(s) = _reverse(s,length(s)-1,0,'')

_reverse(s,i,j,res) = 
    
    if i=-1 then res
    else _reverse(s,i-1,j+1,s[i] -> res[j])
    endif 