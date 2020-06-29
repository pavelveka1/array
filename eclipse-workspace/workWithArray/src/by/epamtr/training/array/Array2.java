package by.epamtr.training.array;
/**
 * Дана последовательность целых чисел n a1 ,a2 ...an . Образовать новую
 * последовательность, выбросив из исходной те члены, которые равны min(a1 ,a2
 * ,...an )
 */
public class Array2 {

	public static int[] excludeMinElementFromArray(int[] array) {
		if (array == null) {
			throw new ValidationException("passed argument is null");
		}
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (min > array[i]) {
				min = array[i];
				count = 1;
			} else if (min == array[i]) {
				count++;
			}
		}
		int[] arrayNew = new int[array.length - count];
		int indexNewArray = 0;
		for (int j = 0; j < array.length; j++) {
			if (array[j] != min) {
				arrayNew[indexNewArray] = array[j];
				indexNewArray++;
			}
		}
		return arrayNew;
	}
}
