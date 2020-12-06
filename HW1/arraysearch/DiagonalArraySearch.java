package algs.hw1.arraysearch;

/**
 * Design a subclass that extends this class to complete this assignment.
 * 
 * Do not copy this class into your USERID.hw1 package. Do not modify this class.
 * 
 * A DiagonalArray is a two-dimensional, lower-triangular square (N-by-N) array of unique integers.
 * The properties of a DiagonalArray is as follows:
 * 
 *   1. The smallest value is in the upper left cell A[0][0]
 *   2. The largest value is in the upper right cell A[N-1][0]
 *   3. There are n diagonal bands, each running from a cell in the first column "southeast" 
 *      to the bottom row.
 *      
 *  Here is a sample:
 *  
 *       1  0  0  0  0
 *       6  2  0  0  0
 *      10  7  3  0  0
 *      13 11  8  4  0
 *      15 14 12  9  5
 *
 * As each diagonal moves inwards, the values increase 
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class DiagonalArraySearch extends ArraySearch {

	public DiagonalArraySearch(int[][] a) {
		super(a);
	}

	/** Small example to use. Do not modify these values. */
	public static final int[][] sample = new int[][] {
		 { 1,  0,  0,  0,  0,  0},
		 { 7,  2,  0,  0,  0,  0},
		 {12,  8,  3,  0,  0,  0},
		 {16, 13,  9,  4,  0,  0},
		 {19, 17, 14, 10,  5,  0},
		 {21, 20, 18, 15, 11,  6}
	};

	
	/**
	 * Create a sample DiagonalArray.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid DiagonalArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		int val = n-1;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n-r; c++) {
				a[r+c][c] = val; val += (n-1);
			}
		}
		
		return a;
	}
	
	/** 
	 * Ensures the array conforms to the problem specification:
	 * 
	 * Throws Exception if a is not Diagonal Array or contains duplicate values, as per homework instructions.
	 * 
	 * @param a    array to be investigated
	 */
	protected void checkProperty(int[][] a) { 
		int n = a.length;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c <= r; c++) {
				if (inspect(r,c) <= 0) {
					throw new IllegalArgumentException ("Values must be positive:" + a[r][c]);
				}
			}
			for (int c = r+1; c < n; c++) {
				if (inspect(r,c) != 0) {
					throw new IllegalArgumentException ("Values must be zero above main diagonal:" + a[r][c]);
				}
			}
		}
		
		int val = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n-r; c++) {
				if (val == 0) {
					val = a[0][0];
				} else {
					if (a[r+c][c] < val) {
						throw new IllegalStateException ("Array is not DiagonalArray [" + (r+c) + "," + c + "]");
					}
					val = a[r+c][c];
				}
			}
		}
	}
}
