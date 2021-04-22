/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode result_end = result;
        boolean perenos = false;
        while (l1.next != null && l2.next != null)  {
            
            if (perenos){
                result.val ++;
                perenos = false;
            } 
            if ((result.val + l1.val + l2.val)/10 == 1)     perenos = true;
            result.val = (result.val + l1.val + l2.val)%10;
            
            l1 = l1.next;
            l2 = l2.next;
            ListNode tempNode = new ListNode();
            result.next = tempNode;
            result = tempNode;
        }
        if (l1.next == null && l2.next == null)
        {
            if (perenos){
                result.val ++;
                perenos = false;
            } 
            if ((result.val + l1.val + l2.val)/10 == 1){
                perenos = true;
                result.val = (result.val + l1.val + l2.val)%10;
                ListNode tempNode = new ListNode();
                result.next = tempNode;
                result = tempNode;
            } else result.val = (result.val + l1.val + l2.val)%10;    
            
            
           
        }else if (l1.next == null){
            if (perenos){
                result.val ++;
                perenos = false;
            } 
            if ((result.val + l1.val + l2.val)/10 == 1)     perenos = true;
            result.val = (result.val + l1.val + l2.val)%10;
            
            l2 = l2.next;
            ListNode tempNode = new ListNode();
            result.next = tempNode;
            result = tempNode;
            
            while (l2.next != null)  {
            
                if (perenos){
                    result.val ++;
                    perenos = false;
                } 
                if ((result.val + l2.val)/10 == 1)     perenos = true;
                result.val = (result.val + l2.val)%10;
            
                l2 = l2.next;
                ListNode tempNode1 = new ListNode();
                result.next = tempNode1;
                result = tempNode1;
            }
            
            if (perenos){
                perenos = false;
                if((l2.val + 1)/10 ==1) {
                    result.val = (l2.val + 1)%10;
                    perenos = true;
                    ListNode tempNode1 = new ListNode();
                    result.next = tempNode1;
                    result = tempNode1;
                    
                } else  result.val = (l2.val + 1)%10;
                
            } else{

                result.val = l2.val;
            }
            
        }
        else if (l2.next == null){
            if (perenos){
                result.val ++;
                perenos = false;
            } 
            if ((result.val + l1.val + l2.val)/10 == 1)     perenos = true;
            result.val = (result.val + l1.val + l2.val)%10;
            
            l1 = l1.next;
            ListNode tempNode = new ListNode();
            result.next = tempNode;
            result = tempNode;
            
            while (l1.next != null)  {
            
                if (perenos){
                    result.val ++;
                    perenos = false;
                } 
                if ((result.val + l1.val)/10 == 1)     perenos = true;
                result.val = (result.val + l1.val)%10;
            
                l1 = l1.next;
                ListNode tempNode1 = new ListNode();
                result.next = tempNode1;
                result = tempNode1;
            }
            
            if (perenos){
                perenos = false;
                if((l1.val + 1)/10 ==1) {
                    result.val = (l1.val + 1)%10;
                    perenos = true;
                    ListNode tempNode1 = new ListNode();
                    result.next = tempNode1;
                    result = tempNode1;
                    
                } else  result.val = (l1.val + 1)%10;
                
            } else{

                result.val = l1.val;
            }
        }
       
        if (perenos){
            if((result.val + 1)/10 == 1){
                result.val = (result.val + 1)%10;
            
                ListNode tempNode = new ListNode();
                result.next = tempNode;
                result = tempNode;
                result.val ++;
            } else{
                result.val = (result.val + 1)%10;
            }
                
                
        }

        return result_end;
    }
}
