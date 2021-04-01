
main() = fill([], 1000, 0)

#test(list) = ( 'l' -> list[1,1,0] )

fill(list, n, i) = 	
   if (i=n) then list[3,4]
   else
      fill((i->list[(i//100),((i//10)%10),(i%10)]), n, (i+1))
endif

