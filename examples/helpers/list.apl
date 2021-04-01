# Austausch der Elemente mit Index u und o im Array a:

add(l,i) = i -> l[length(l)]

change(a,u,o) = _change(a,u,o,a[u])
_change(a,u,o,tmp) = __change(a[o]->a[u],o,tmp)
__change(a,o,tmp) = tmp->a[o]


# Listenreihenfolge umkehren

revert(list) = _revert(list, length(list)-1, [])
_revert(list, i, res) = if i<0 then
                           res
                        else
                           _revert(list, i-1, list[i] -> res[length(list)-1-i])
                        endif
                            