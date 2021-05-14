class Solution {
    public int reverse(int x) {
        if (x == 0) return 0;
        int sign = 1;
        if (x < 0) 
        {
            sign = -1;
            x = x*sign;
        }
        int result = 0;
        while(x > 0)
        {
            if (result * 10L + x%10 >= Integer.MAX_VALUE) return 0;
            result = result * 10 + x%10;
            x=x/10;
        }
        result = result * sign;
        return result;
    }
}
