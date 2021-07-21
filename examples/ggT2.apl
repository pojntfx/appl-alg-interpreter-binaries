main() = ggT(8,12)

ggT(x,y) = if x=0 then
               y
           else 
               if x>y then
                   ggT(x%y,y)
               else 
                   ggT(y,x)
               endif
           endif