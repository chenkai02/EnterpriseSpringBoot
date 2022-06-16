import java.nio.channels.SelectableChannel;

public class Algorithm {
    //对折算法
    public  int biSearch(int [] array , int a){
        int lo =0;
        int hi =array.length-1;
        int mid;
        while (lo <= hi){
            mid = (lo+hi)/2;
            if (array[mid] == a){
                return mid +1;
            }else if (array[mid] < a) {
                lo = mid + 1;
            }else {
                hi = mid -1;
            }
        }
        return -1;
    }
    //冒泡排序
    public int findKthLargest(int[] nums, int k) {

        for(int i = 0 ; i<nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] < nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return  nums[(nums.length - k)];

    }
}
