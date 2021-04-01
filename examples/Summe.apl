
main() = sum( 10 )

#-----------------------

sum(N) = _sum(N,0,0)

_sum(N,s,i) = if i>N then
                 s
              else
                 _sum(N,s+i,i+1)
              endif      





