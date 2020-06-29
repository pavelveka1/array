package by.epamtr.training.array;
/*. В массиве целых чисел с количеством элементов n
 *  найти наиболее часто встречающееся число. 
 *  Если таких чисел несколько, то определить наименьшее из них.
 */
public class Array4 {

	public static int getMostCommonNumber(int[] array) {
		if (array == null) {
			throw new ValidationException("passed argument is null");
		}
		int indexMaxRepeat = 0;
		int[] numberRepeatsEachElementArray = getArrayNumberRepeats(array);
		indexMaxRepeat = getIndexMaxRepeats(numberRepeatsEachElementArray, array);
		return array[indexMaxRepeat];
	}

	private static int[] getArrayNumberRepeats(int[] arrayOriginal) {
		int numberRepeats = 0;
		int[] arrayResult = new int[arrayOriginal.length];
		for (int i = 0; i < arrayOriginal.length; i++) {
			for (int j = 0; j < arrayOriginal.length; j++) {
				if (arrayOriginal[i] == arrayOriginal[j]) {
					numberRepeats++;
				}
			}
			arrayResult[i] = numberRepeats;
			numberRepeats = 0;
		}
		return arrayResult;
	}

	private static int getIndexMaxRepeats(int[] arrayNumberRepeat, int[] arrayOriginal) {
		int maxRepeats = 0;
		int index = 0;
		for (int i = 0; i < arrayNumberRepeat.length - 1; i++) {
			if (arrayNumberRepeat[i] >= maxRepeats) {
				maxRepeats = arrayNumberRepeat[i];
				index = i;
			}
		}
		for (int i = 0; i < arrayNumberRepeat.length; i++) {
			if (maxRepeats == arrayNumberRepeat[i]) {
				if (arrayOriginal[index] <= arrayOriginal[i]) {
					maxRepeats = arrayNumberRepeat[index];
				} else {
					maxRepeats = arrayNumberRepeat[i];
					index = i;
				}
			}
		}
		return index;
	}
}
