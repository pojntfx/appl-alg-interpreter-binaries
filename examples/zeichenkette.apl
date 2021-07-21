main() = search('Hallo Welt !!!','lt !')
#--------------------------------------

search(t, m) = _search(t, m, 0, 0) 

_search(t, m, i, j) =
	
    # if i is bigger than the rest then the string does not contain the word
	if i > length(t)-length(m) then -1
	else
        # if the counter of the target is its length we print the index i 
		if j=length(m) then i
		else
            # if the index + word index is not the current j of the word the its no match
			if t[i+j] != m[j] then
                # call search and increase index + 1
				_search(t, m, i+1, 0)
			else
                # check for the next match in the word
				_search(t, m, i, j+1)
			endif 
		endif
	endif
