
main() = binsearch(14, [1, 5, 7, 9, 12, 14, 17, 20])

binsearch(n, a) = _binsearch(n, a, 0, length(a)-1)

_binsearch(n, a, u, o) =
 
   if u=o then if n=a[u] then a[u]
   	             else 'Not found.'
                 endif 
   else
   	  if o-u=1 then if n=a[u] then a[u]
   	                    else if n=a[o] then a[o]
   	                         else 'Not found.'
   	                         endif
   	                    endif
   	  else if a[(o+u)//2] > n then
              _binsearch(n, a, u, (o+u)//2)
           else
              _binsearch(n, a, (o+u)//2, o )
           endif
      endif
   endif      
       