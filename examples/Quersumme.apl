
main() = totalquer(678945, [])

totalquer(n, list) = if n<10 then n->list[length(list)]
               else totalquer(quer(n), n->list[length(list)])
               endif

quer(n) = _quer(n, 0)

_quer(n, res) = if n=0 then res
                else
                   _quer(n//10, res + n%10)
                endif