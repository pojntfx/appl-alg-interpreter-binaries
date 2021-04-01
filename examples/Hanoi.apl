
main() = hanoi(3)
#----------------

hanoi(n) = _hanoi(n, 'Quelle', 'Ziel', 'Hilfe') 

_hanoi(n, q, z, h) =

		if n = 1 then
			q + ' -> ' + z + 'NEWLINE'
		else
			_hanoi(n-1, q, h, z) + 
			_hanoi(1, q, z, h) + 
			_hanoi(n-1, h, z, q) 
		endif