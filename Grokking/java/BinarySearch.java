import java.util.*;
import java.lang.*;
class BinarySearch{
	public static <T> int binarySearch(int[] list, int search){
		int high = 0;
		int low = list.length-1;
		int mid =0;
		while(high > low){
			mid = (high+low)/2;
			if(list[mid]==search){
				return mid;
			}
			else if(list[mid]<search){
				high = mid-1;
			}
			else{
				low = mid+1;
			}
		}
		return -1* mid;
	}
	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{2,4,8,9,12},13));
	}
}