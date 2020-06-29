package by.epamtr.training.array;

public class Array5 {

	public static int calculateFunction(int[] array) {
		if (array == null) {
			throw new ValidationException("passed argument is null");
		}
		int maxEven = 0;
		int minOdd = Integer.MAX_VALUE;
		for (int i = 0; i < array.length; i++) {
			if ((i + 1) % 2 == 0) {
				if (maxEven < array[i]) {
					maxEven = array[i];
				}
			} else {
				if (minOdd > array[i]) {
					minOdd = array[i];
				}
			}
		}
		return maxEven + minOdd;
	}

}
