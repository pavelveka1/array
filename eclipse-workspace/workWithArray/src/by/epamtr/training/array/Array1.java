package by.epamtr.training.array;

public class Array1 {

	public static void transformArrayAndPrintMaxElement(double[] array) {
		if (array == null) {
			throw new ValidationException("passed argument is null");
		}
		int sizeNewArray = array.length / 2;
		if (array.length % 2 != 0) {
			sizeNewArray++;
		}
		double[] sumArray = new double[sizeNewArray];

		for (int i = 0; i < sizeNewArray; i++) {
			sumArray[i] = array[i] + array[array.length - 1 - i];
		}
		searchAndPrintMaxElementy(sumArray);
	}

	private static void searchAndPrintMaxElementy(double[] arrayForSearch) {
		double max = 0;
		for (int i = 0; i < arrayForSearch.length; i++) {
			if (max < arrayForSearch[i]) {
				max = arrayForSearch[i];
			}
		}
		System.out.println(max);
	}

}
