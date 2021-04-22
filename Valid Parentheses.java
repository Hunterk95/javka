class Solution {
    public boolean isValid(String s) {
        Stack<Integer> stk = new Stack<>() ;
        

        for(char current:s.toCharArray() ) {
            switch(current) {
                    case ('(') :
                    stk.push(1);
                    break;
                    
                    case ('[') :
                    stk.push(2);
                    break;
                    
                    case ('{') :
                    stk.push(3);
                    break;
                    
                    case (')') :
                    if(stk.empty()) return false;
                    if(stk.pop()!= 1 ) return false;
                    break;
                    
                    case (']'):
                    if(stk.empty()) return false;
                    if(stk.pop()!= 2) return false;
                    break;
                    
                    case ('}'):
                    if(stk.empty()) return false;
                    if(stk.pop()!=3) return false;
                    break;
            } 
        } 
        if(stk.empty() ) return true;
        return false;
   }
} 
