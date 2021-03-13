class SelectionSort{
	public static void selectionSort(Integer[] list){
		int min, tmp;
		for (int i =0;i<list.length-1 ;i++ ) {
			min = i;
			for (int j= i+1;j<list.length ; j++) {
				if(list[j]<list[min])
					min = j;
			}
			tmp = list[i];
			list[i] = list[min];
			list[min] = tmp;
		}
	}
	public static <T> String print(T[] list){
		String result="[ ";
		for(T item : list){
			result+=item+", ";
		}
		result= result.substring(0,result.length()-2);
		result+=" ]";
		return result;
	}
	public static void main(String[] args) {
		Integer[] input = {1,3,9,0,1,2};
		System.out.printf("%8s %s\n","before",SelectionSort.<Integer>print(input));
		selectionSort(input);
		System.out.printf("%8s %s\n","after",SelectionSort.<Integer>print(input));
	}
}