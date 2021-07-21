main() = search('Jakob', 'Jakob')

search(str, snippet) = _search(str, snippet, 0)

_search(str, snippet, start) = if check(str, snippet, start, length(str)) then
                                    true
                               else 
                                    if start<length(str)-length(snippet) then
                                        _search(str, snippet, start+1)
                                    else 
                                        false
                                    endif
                               endif

check(str, snippet, start, finish) = _check(str, snippet, start, true, 0)


_check(str, snippet, start, found, index_snippet) = if index_snippet=length(snippet) then
                                                                found
                                                            else 
                                                                if str[start + index_snippet] = snippet[index_snippet] then
                                                                    _check(str, snippet, start, true, index_snippet+1)
                                                                else 
                                                                    _check(str, snippet, start, false, index_snippet+1)
                                                                endif
                                                            endif