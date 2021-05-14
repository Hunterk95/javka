class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
            int newplace=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i] >nums[newplace-1]) {
                nums[newplace] =nums[i] ;
                newplace++;
            } 
        }
        return newplace;
    }
}
