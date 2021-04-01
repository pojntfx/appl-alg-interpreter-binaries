
use 'appl_algs/helpers/list.apl'

main() = quicksort([5, 550, 725, 883, 712, 633, 854, 162, 533, 654, 214, 413, 393, 383, 688, 960, 84])

quicksort(a) = _quicksort(a,0,length(a)-1)  

# Sortieren des Array zwischen Index u und o:
_quicksort(a,u,o) = if u>=o then a
                    else
                    	# Zuerst das Array pivotisieren: pivot(a,u,u,o)[0] (innerster call)
                    	# Dann linkes Teilarray sortieren: innerer _quicksort call
                    	# Dann rechtes Teilarray sortieren: äusserer _quicksort call
                    	# Problem: Die Lösung ist zwar korrekt, aber wegen des dreimaligen calls
                    	# von pivot(...) etwas ineffizient. Hier ist QuickSort2 besser.
                    	_quicksort(_quicksort(pivot(a,u,u,o)[0],u,pivot(a,u,u,o)[1]-1),pivot(a,u,u,o)[1]+1,o)
                    endif  	
                    
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




