
use 'appl_algs/helpers/list.apl'
use 'appl_algs/helpers/math.apl'

main() = match('ABCBC')

# Berechnet die sichere match-Tabelle des Boyer-Moore-Algorithmus
# ---------------------------------------------------------------

match(m) = _match(m, match_unsafe(m), length(m)-1, [])

_match(m, mu_list, i, res) = if i<0 then
                                revert(res)   # res muss von links nach rechts aufgebaut werden 
                             else
                                _match(m, mu_list, i-1, 
                                       min(mu_list[i], length(m) - _max_prefix_as_suffix_in_match(m, i)) -> res[length(m)-1-i])
                             endif

match_unsafe(m) = _match_unsafe(m, suffix(m), 1, [])
_match_unsafe(m, s_list, i, res) = if i>length(m) then 
                                     revert(res)   # res muss von links nach rechts aufgebaut werden
                                  else
                                     _match_unsafe(m, s_list, i+1, _find_in_suffix_list(s_list,i) -> res[length(res)])
                                  endif

suffix(m) = _suffix(m,0,[])
_suffix(m,i,res) = if i=length(m) then 
                        res
                     else
                        _suffix(m, i+1, _max_suffix(m, i) -> res[length(res)])
                     endif
                     
_max_suffix(m,i) = __max_suffix(m,i,0) # wie weit kann ich vom Index i nach links gehen,
                                       # um ein Suffix von m zu haben?
__max_suffix(m,i,j) = if i-j < 0 then
                         j 
                      else
                         if m[length(m)-1-j] != m[i-j] then
                            j
                         else
                            __max_suffix(m,i,j+1)
                         endif
                      endif
                                  
_find_in_suffix_list(s_list,i) = __find_in_suffix_list(s_list,i,length(s_list)-1)
# Findet den (von rechts) ersten Wert = i in der Suffix-Tabelle und gibt gleich
# die zugehörige (unsichere) Verschiebungslänge zurück, die in der match_unsafe-Tabelle
# landet.
__find_in_suffix_list(s_list,i,j) = if j<0 then
                                      length(s_list)
                                   else
                                      if i = s_list[j] then
                                         length(s_list) - 1 - j
                                      else
                                         __find_in_suffix_list(s_list,i,j-1)
                                      endif
                                   endif
                             
_max_prefix_as_suffix_in_match(m, i) = __max_prefix_as_suffix_in_match(m, i, 1, 0)
# Findet die maximale Länge eines Präfixes von m, welches auch Suffix in einer Übereinstimmung
# in m ab dem Index i ist.
__max_prefix_as_suffix_in_match(m, i, j, res) = if j > length(m)-i then
                                                   res
                                                else
                                                   if _prefix_suffix(m, j) then
                                                      __max_prefix_as_suffix_in_match(m, i, j+1, j)
                                                   else
                                                      __max_prefix_as_suffix_in_match(m, i, j+1, res)
                                                   endif
                                                endif

_prefix_suffix(m, i) = __prefix_suffix(m, i, 0) # Ist das Präfix der Länge i auch Suffix von m?
__prefix_suffix(m, i, j) = if i > length(m) then
                              false
                           else
                              if j>=i then
                                 true
                              else 
                                 if m[j] != m[length(m)-i+j] then
                                    false
                                 else
                                    __prefix_suffix(m, i, j+1)
                                 endif
                              endif
                           endif
                                      
                                      
                                      
                                      
                                         