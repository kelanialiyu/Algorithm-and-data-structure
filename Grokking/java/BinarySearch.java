class BinarySearch{
	public static <T> int binarySearch(T[] list, T search){
		int high = 0;
		int low = input.length-1;
		while(high > low){
			mid = (high+low)/2
			if(list[mid].equal(search)==0){
				return mid;
			}
			else if(list[mid].equal(search)<1){
				high = mid-1;
			}
			else{
				low = mid+1;
			}
		}
		return -1* mid;
	}
	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{2,4,8,9,12},13))
	}
}