package by.epamtr.training.array;
/*. «Суперзамок». Секретный замок для сейфа состоит из 10 расположенных в рад ячеек, 
в которые надо вставить игральные кубики. Но дверь открывается только в том случае,
когда в любых трех соседних ячейках сумма точек на передних гранях кубиков равна 10. 
(Игральный кубик имеет на каждой грани от 1 до 6 точек). Напишите программу,
которая разгадывает код замка при условии, что два кубика уже вставлены в ячейки.
*/
public class Array3 {

	private static int[] solutionArray = new int[10];

	public static int[] getKeyFromLock(int addr1, int addr2, int value1, int value2) {
		validateData(addr1, addr2, value1, value2);
		if (Math.abs(addr1 - addr2) == 1) {
			return getKeyCubesLocatedNear(addr1, addr2, value1, value2);
		} else if (Math.abs(addr1 - addr2) == 2) {
			return getKeyCubesLocatedThroughOnePosition(addr1, addr2, value1, value2);
		} else {
			return getKeyCubesLocatedFar(addr1, addr2, value1, value2);
		}
	}
	
	private static void validateData(int addr1, int addr2, int value1, int value2) {
		if ((addr1 > 9 || addr1 < 0 || addr2 > 9 || addr2 < 0)) {
			throw new ValidationException("index of the address outside the array");
		}
		if (addr1 == addr2) {
			throw new ValidationException("two cubes can't be in one cell");
		}
		if (value1 > 6 || value1 < 1 || value2 > 6 || value2 < 1) {
			throw new ValidationException("Invalid cube value. Сube can contain a value from 1 to 6");
		}
		if(value1+value2>=10) {
			throw new ValidationException("resolution is not exists: sum of two values equals or greater then 10");
		}
	}

	private static int[] getKeyCubesLocatedNear(int addr1, int addr2, int value1, int value2) {
		solutionArray[addr1] = value1;
		solutionArray[addr2] = value2;
		int count = 0;
		if (addr1 < addr2) {
			count = addr2;
			while (count < 9) {
				solutionArray[count + 1] = 10 - (solutionArray[count] + solutionArray[count - 1]);
				count++;
			}
			count = addr1;
			while (count > 0) {
				solutionArray[count - 1] = 10 - (solutionArray[count] + solutionArray[count + 1]);
				count--;
			}
		} else if (addr1 > addr2) {
			count = addr1;
			while (count < 9) {
				solutionArray[count + 1] = 10 - (solutionArray[count] + solutionArray[count - 1]);
				count++;
			}
			count = addr2;
			while (count > 0) {
				solutionArray[count - 1] = 10 - (solutionArray[count] + solutionArray[count + 1]);
				count--;
			}
		}
		return solutionArray;
	}

	private static int[] getKeyCubesLocatedThroughOnePosition(int addr1, int addr2, int value1, int value2) {
		solutionArray[addr1] = value1;
		solutionArray[addr2] = value2;
		if (addr1 < addr2) {
			solutionArray[addr1 + 1] = 10 - (solutionArray[addr1] + solutionArray[addr2]);
			return getKeyCubesLocatedNear(addr1, addr1 + 1, value1, solutionArray[addr1 + 1]);
		} else {
			solutionArray[addr2 + 1] = 10 - (solutionArray[addr1] + solutionArray[addr2]);
			return getKeyCubesLocatedNear(addr2, addr2 + 1, value2, solutionArray[addr2 + 1]);
		}
	}

	private static int[] getKeyCubesLocatedFar(int addr1, int addr2, int value1, int value2) {
		solutionArray[addr1] = value1;
		solutionArray[addr2] = value2;
		if (Math.abs(addr1 - addr2) % 3 == 0) {
			if (value1 == value2) {
				if (addr1 < addr2) {
					solutionArray[addr2 - 1] = (10 - solutionArray[addr2]) / 2;
					return getKeyCubesLocatedNear(addr2, addr2 - 1, value2, solutionArray[addr2 - 1]);
				} else {
					solutionArray[addr1 - 1] = (10 - solutionArray[addr1]) / 2;
					return getKeyCubesLocatedNear(addr1, addr1 - 1, value1, solutionArray[addr1 - 1]);
				}
			}else {
				throw new ValidationException("resolution is not exists!");
			}

		} else if (Math.abs(addr1 - addr2) % 4 == 0) {
			if (addr1 > addr2) {
				solutionArray[addr1 - 1] = value2;
				return getKeyCubesLocatedNear(addr1, addr1 - 1, value1, solutionArray[addr1 - 1]);
			} else {
				solutionArray[addr2 - 1] = value1;
				return getKeyCubesLocatedNear(addr2, addr2 - 1, value2, solutionArray[addr2 - 1]);
			}

		} else if (Math.abs(addr1 - addr2) % 5 == 0) {
			if (addr1 > addr2) {
				solutionArray[addr1 - 2] = value2;
				solutionArray[addr1 - 1] = 10 - (value2 + solutionArray[addr1]);
				return getKeyCubesLocatedNear(addr1, addr1 - 1, value1, solutionArray[addr1 - 1]);
			} else {
				solutionArray[addr2 - 2] = value1;
				solutionArray[addr2 - 1] = 10 - (value1 + solutionArray[addr2]);
				return getKeyCubesLocatedNear(addr2, addr2 - 1, value2, solutionArray[addr2 - 1]);
			}
		}
		return solutionArray;

	}

}
