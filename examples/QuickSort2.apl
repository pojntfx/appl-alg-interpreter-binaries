
main() = quicksort([5, 550, 725, 883, 712, 633, 854, 162, 533, 654, 214, 413, 393, 383, 688, 960, 84])

quicksort(a) = _quicksort(a,0,length(a)-1)  

# Sortieren des Array zwischen Index u und o:
_quicksort(a,u,o) = if u >= o then a
                    else
                    	__quicksort(pivot(a,u,u,o),u,o)
                    endif  	
                    
__quicksort(pivot_arr,u,o) = _quicksort( _quicksort(pivot_arr[0],u,pivot_arr[1]-1) , pivot_arr[1]+1 , o )

pivot(a,p,u,o) = # Pivotisierung des Array zwischen Index u und o mit Pivotelement a[p].
                 # Rückgabe der pivotisierten Liste und des Pivotindex
                 if u < p then
                    if a[u] <= a[p] then pivot(a,p,u+1,o)
                    else pivot(change(a,u,p),u,u,o)
                    endif
                 else
                    if p < o then
                       if a[p] <= a[o] then pivot(a,p,u,o-1)
                       else pivot(change(a,p,o),o,u,o)
                       endif
                    else
                    	[a,p]         # Rückgabe der beiden Resultate in einem Array.
                    endif    
                 endif        

# Austausch der Elemente mit Index u und o im Array a:
change(a,u,o) = _change(a,u,o,a[u])
_change(a,u,o,tmp) = __change(a[o]->a[u],o,tmp)
__change(a,o,tmp) = tmp->a[o]


