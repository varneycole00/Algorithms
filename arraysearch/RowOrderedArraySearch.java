package algs.hw1.arraysearch;

/**
 * Design a subclass that extends this class to complete this assignment.
 * 
 * You should not copy this class into your USERID.hw1 package. You should make no modifications
 * to this class.
 * 
 * A RowOrderedArray is a two-dimensional, lower-triangular square (N-by-N) array of unique integers.
 * The properties of a RowOrderedArray is as follows:
 * 
 *   1. The array is a lower-triangular array where all values above the main diagonal are zero.
 *   2. Each row contains ascending values from left to right.
 *   3. Each of the values in row 0 <= k < (n-1) are smaller than the values in row (k+1).
 *      
 *  Here is a sample:
 *  
 *       1  0  0  0  0
 *      13 23  0  0  0
 *      35 36 37  0  0
 *      48 52 54 55  0
 *      63 72 77 78 79
 *      
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class RowOrderedArraySearch extends ArraySearch {

	public RowOrderedArraySearch(int[][] a) {
		super(a);
	}

	/** Small example to use. Do not modify these values. */
	public static final int[][] sample = new int[][] {
		{ 1,  0,  0,  0,  0},
		{ 13, 23, 0,  0,  0},
		{ 35, 36, 37, 0,  0},
		{ 48, 52, 54, 55, 0},
		{ 63, 72, 77, 78, 79}
	};
	
	/** larger example to use. Do not modify these values. */
	static final int[][] big = new int[][] { 
		 { 1,  0,  0,  0,  0,  0,  0},
		 { 8,  9,  0,  0,  0,  0,  0},
		 {15, 16, 17,  0,  0,  0,  0},
		 {22, 23, 24, 25,  0,  0,  0},
		 {29, 30, 31, 32, 33,  0,  0},
		 {36, 37, 38, 39, 40, 41,  0},
		 {43, 44, 45, 46, 47, 48, 49},
	};

	/**
	 * Create a sample RowOrdered, lower-triangular 2D Array.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid RowOrderedArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		int val = 1;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c <= r; c++) {
				a[r][c] = val;  val += (n-1); 
			}
		}
		
		return a;
	}
	
	
	/** 
	 * Ensures the array conforms to the problem specification:
	 * 
	 * Throws Exception if a is not Row Ordered Array or contains duplicate values, as per homework instructions.
	 * 
	 * @param a    array to be investigated
	 * @throws IllegalStateException if the array doesn't have proper structure.
	 */
	protected void checkProperty(int[][] a) {
		int n = a.length;
		int r = 0;
		int val = a[r][0]-1;
		for (int k = 1; k <n; k++) {
			for (int c = 0; c <= r; c++) {
				if (a[r][c] <= val) {
					throw new IllegalStateException ("Array is not RowOrdered [" + r + "," + c + "]");
				}
				val = a[r][c];
			}
			for (int c = r+1; c < n; c++) {
				if (a[r][c] != 0) {
					throw new IllegalArgumentException ("Values above Diagonal must be zero:" + a[r][c]);
				}
			}
			r++;
		}
	}
}
