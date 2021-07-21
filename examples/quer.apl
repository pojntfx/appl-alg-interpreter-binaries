main() = quer(234234, 0)

quer(number, sum) = if number = 0 then
                        sum
                    else 
                        quer(number//10, sum + number%10)
                    endif

                    
