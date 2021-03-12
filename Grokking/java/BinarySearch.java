import java.util.*;
import java.lang.*;
class BinarySearch{
	public static int binarySearch(int[] list, int search){
		int high = list.length-1;
		int low =0 ;
		int mid =0;
		while(!(low>high)){
			mid = (high+low)/2;
			if(list[mid]==search){
				return mid;
			}
			else if(list[mid]<search){
				low = mid+1;
			}
			else{
				high = mid-1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{2,4,8,9,12},12));
	}
}