package algs.hw1.arraysearch;

/**
 * Design a subclass that extends this class to complete this assignment.
 * 
 * Do not copy this class into your USERID.hw1 package. Do not modify this class.
 * 
 * A Nested array is a two-dimensional, lower-triangular square (N-by-N) array of unique integers.
 * The properties of a DiagonalArray is as follows:
 * 
 *   1. The smallest value is in the upper left cell A[0][0]
 *   2. The lower triangular array is decomposed into nested triangles. All other values are 0.
 *   3. The outermost triangle, T0, is composed of 3*(n-1) integers in increasing order in clockwise
 *      fashion. That is, running diagonally "south-east", then "west" horizontally, then "north"
 *      vertically. The next inner triangle, T1, has 3*(n-2) integers.
 *   4. There are k=floor(n/2) nested triangles. All values in triangle Ti are smaller than all values
 *      in Triangle Ti+1.
 *      
 *  Here is a sample:
 *  
 *       1  0  0  0  0  0  0  
 *      18  2  0  0  0  0  0
 *      17 19  3  0  0  0  0
 *      16 27 20  4  0  0  0
 *      15 26 28 21  5  0  0
 *      14 25 24 23 22  6  0
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class NestedArraySearch extends ArraySearch {

	public NestedArraySearch(int[][] a) {
		super(a);
	}

	/** Small example to use. Do not modify these values. */
	public static final int[][] sample = new int[][] {
		 { 1,  0,  0,  0,  0,  0,  0},
		 {18,  2,  0,  0,  0,  0,  0},
		 {17, 19,  3,  0,  0,  0,  0},
		 {16, 27, 20,  4,  0,  0,  0},
		 {15, 26, 28, 21,  5,  0,  0},
		 {14, 25, 24, 23, 22,  6,  0},
		 {13, 12, 11, 10,  9,  8,  7}
	};

	/**
	 * Create a sample NestedArray.
	 * 
	 * @param n   Size of desired 2d array
	 * @return    A valid NestedArray of size nxn
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n][n];
		
		int val = n-1;
		if (n == 1) { val++; }  // make sure for n=1 it is positive
		int numT = 1+(n-1)/3;
		int len = n;
		int r = 0;
		for (int t = 0; t < numT; t++) {
			for (int d = t; d < len; d++) {
				a[r][d] = val; val += (n-1); r++;
			}
			r--;
			for (int w = len-2; w >= t; w--) {
				a[r][w] = val; val += (n-1);
			}
			r--;
			for (int no = len-2; no > t; no--) {
				a[r][t] = val; val += (n-1); r--; 
			}
			r += 2;   // add 1 to go back to norm, then +2 for next triangle
			len -= 2; // smaller triangles as we go inwards
		}
		
		return a;
	}
	
	/** 
	 * Ensures the array conforms to the problem specification:
	 * 
	 * Throws Exception if a is not NestedArray or contains duplicate values, as per homework instructions.
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
		int numT = 1+(n-1)/3;

		int len = n;
		int r = 0;
		for (int t = 0; t < numT; t++) {
			for (int d = t; d < len; d++) {
				if (val == 0) { 
					val = a[r][d]; r++;
				} else {
					if (a[r][d] <= val) {
						throw new IllegalStateException ("Array is not NestedArray [" + r + "," + d + "]");
					}
					val = a[r][d];
					r++;
				}
				
			}
			r--;
			for (int w = len-2; w >= t; w--) {
				if (a[r][w] <= val) {
					throw new IllegalStateException ("Array is not NestedArray [" + r + "," + w + "]");
				}
				val = a[r][w];
			}
			r--;
			for (int no = len-2; no > t; no--) {
				if (a[r][t] <= val) {
					throw new IllegalStateException ("Array is not NestedArray [" + r + "," + t + "]");
				}
				val = a[r][t];
				r--;
			}
			r += 2;   // add 1 to go back to norm, then +2 for next triangle
			len -= 2; // smaller triangles as we go inwards
		}
		
	}
}
