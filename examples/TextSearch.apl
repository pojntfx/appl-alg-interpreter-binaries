
main() = search('Hallo Welt !!!','lt !')
#--------------------------------------

search(t, m) = _search(t, m, 0, 0) 

_search(t, m, i, j) =
	
	if i > length(t)-length(m) then -1
	else
		if j=length(m) then i
		else
			if t[i+j] != m[j] then
				_search(t, m, i+1, 0)
			else
				_search(t, m, i, j+1)
			endif 
		endif
	endif