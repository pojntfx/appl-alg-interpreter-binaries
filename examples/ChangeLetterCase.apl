
main() = change('Hallo Welt, wie geht es dir?') 

change(s) = _change(s, '', 0)

_change(s, res, i) = if i=length(s) then res
                     else 
                     	_change(s, change_single_letter(s[i])->res[length(res)], i+1)
                     endif

change_single_letter(c) = _change_single_letter(lowers(), uppers(), c)

_change_single_letter(low, up, c) = if binsearch(c, low)=-1 and binsearch(c, up)=-1 then c
     								else
										if binsearch(c, up)=-1 then
											up[binsearch(c, low)]
										else
											low[binsearch(c, up)]
										endif
									endif
								
lowers() = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
uppers() = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']

binsearch(n, a) = _binsearch(n, a, 0, length(a)-1)
_binsearch(n, a, u, o) =
 
   if u=o then if n=a[u] then u
   	           else -1
               endif 
   else
   	  if o-u=1 then if n=a[u] then u
   	                else if n=a[o] then o
   	                     else -1
   	                     endif
   	                endif
   	  else if a[(o+u)//2] > n then
              _binsearch(n, a, u, (o+u)//2)
           else
              _binsearch(n, a, (o+u)//2, o )
           endif
      endif
   endif      

