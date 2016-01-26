
public class ArrayCreation {
	public static void main(String[] args) {

		// Method to create char array #1
		char[] myCharArray1 = { 1, 2, 3 };

		// Method to create char array #2
		char[] myCharArray2 = new char[2];
		myCharArray2[0] = 1;
		myCharArray2[1] = 2;

		// Method to create char array #3
		char[] myCharArray3;

		// Create an array of Int
		int[] myIntArray = new int[5];
		myIntArray[0] = 1;
		myIntArray[1] = 3;
		myIntArray[2] = 5;
		myIntArray[3] = 7;
		myIntArray[4] = 9;

		// Create an array of String
		String[] myStringArray = { "Hello ", "my ", "fabulous ", "World ", "!!! " };

		// Call the method to print the elements of array of String and it's
		// size
		whatSizeIsMyStringArray(myStringArray);
		// Call the method to print the elements of array of Int and it's size
		whatSizeIsMyIntArray(myIntArray);

	}

	// Method to print the elements of array of Int and it's size
	public static void whatSizeIsMyIntArray(int[] a) {
		// FOR loop to go though the element of array of Int
		for (int i = 0; i < a.length; i++) {
			System.out.println("Index " + i + " of myIntArray is " + a[i]);
		}
		System.out.print("\nThe size of Int array is " + a.length + " elements.\n\n");
	}

	// Method to print the elements of array of String and it's size
	public static void whatSizeIsMyStringArray(String[] b) {
		// FOR loop to go though the element of array of String
		for (int i = 0; i < b.length; i++) {
			System.out.println("Index " + i + " of myStringArray is " + b[i]);
		}
		System.out.print("\nThe size of String array is " + b.length + " elements.\n\n");
	}
}
