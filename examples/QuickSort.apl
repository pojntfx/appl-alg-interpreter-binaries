
main() = quicksort([5, 550, 725, 883, 712, 633, 854, 162, 533, 654, 214, 413, 393, 383, 688, 960, 84])

quicksort(a) = _quicksort(a,0,length(a)-1)  

# Sortieren des Array zwischen Index u und o:
_quicksort(a,u,o) = if u >= o then a
                    else pivot(a,u,u,o,u,o)
                    endif

pivot(a,p,u,o,u_mem,o_mem) =   
                 # Pivotisierung des Array zwischen Index u und o mit Pivotelement a[p].
                 # Die Parameter u_mem und o_mem dienen dazu, sich die Anfangsgrenzen
                 # für den rekursiven Aufruf von _quicksort zu merken. 
                 if u < p then
                    if a[u] <= a[p] then pivot(a,p,u+1,o,u_mem,o_mem)
                    else pivot(change(a,u,p),u,u,o,u_mem,o_mem)
                    endif
                 else
                    if p < o then
                       if a[p] <= a[o] then pivot(a,p,u,o-1,u_mem,o_mem)
                       else pivot(change(a,p,o),o,u,o,u_mem,o_mem)
                       endif
                    else 
                       # Pivotisierung fertig: a[i]<=a[p] für i<p  und  a[i]>=a[p] für i>p,
                       # jetzt den linken (u_mem bis p-1) und danach den rechten (p+1 bis o_mem)
                       # Teil sortieren:
                       _quicksort(_quicksort(a,u_mem,p-1), p+1, o_mem)	
                    endif    
                 endif        

# Austausch der Elemente mit Index u und o im Array a:
change(a,u,o) = _change(a,u,o,a[u])
_change(a,u,o,tmp) = __change(a[o]->a[u],o,tmp)
__change(a,o,tmp) = tmp->a[o]


