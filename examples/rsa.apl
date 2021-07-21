main() = rsa(3, 13, 2) 

rsa(p, q, m) = decrypt(d(phi(p,q), e(p,q)), n(p,q), encrypt(e(p,q), n(p,q), m))

encrypt(e, n, m) = m^e % n

decrypt(d, n, c) = c^d % n

d(phi, e) = _d(phi, e, 1)

_d(phi, e, k) = 
                if (k * phi + 1) % e = 0 then
                    (k * phi + 1) / e
                else 
                    _d(phi, e, k+1) 
                endif

phi(p, q) = (p-1) * (q-1)

n(p,q) = p*q

e(p,q) = _e(p, q, 2)

_e(p, q, i) =
            if phi(p,q) % i != 0 then
                i
            else 
                _e(p,q, i+1) 
            endif