package algs.hw1.arraysearch;


/**
 * This is a sample of the structure of your solutions.
 * 
 * This is provided to consider how efficient these algorithms are when compared against a 
 * straight-forward binary array search on a 1D array of sorted values. To determine this answer
 * this subclass creates a 2D array with just a single row (i.e., IT IS NOT A SQUARE).
 * 
 * DO NOT MODIFY THIS CLASS. DO NOT COPY INTO YOUR PROJECT.
 */
public abstract class BinaryArraySearch1D extends ArraySearch {

	public BinaryArraySearch1D(int[][] a) {
		super(a); 
	}
	
	/**
	 * Create a sample Unknown Array with all positive values between 1 and n*n*n inclusive.
	 * 
	 * @param n   Size of desired 2d array with a single row
	 * @return    A valid BinaryArray wiht a single row and all n^2 values in there
	 */
	public static final int[][] create(int n) {
		int[][] a = new int[n*(n+1)/2][1];
		
		// n*(n^2-1) = n*(n-1)*(n+1)
		// start at n and go to (n-1)*n*(n+1)/2
		// i.e., 4 to 60  or 3 to 24
		//   delta = 4     delta = 3
		int val = n;
		
		for (int r = 0; r < a.length; r++) {
			a[r][0] = val; val += (n-1);
		}
		return a;
	}
	
	/** 
	 * The only thing that is known is the positive values in the array are all <= n*n*n.
	 * 
	 * @param a    array to be investigated
	 */
	protected void checkProperty(int[][] a) {
		int n = this.length();
		if (a[0].length != 1) {
			throw new IllegalArgumentException ("Special BinaryArraySearch1D must only have a single column.");
		}
		
		for (int r = 0; r < n; r++) {
			if (inspect(r,0) <= 0) {
				throw new IllegalArgumentException ("Values must be positive:" + a[r][0]);
			}
			if (inspect(r,0) > (n-1)*n*(n+1)/2) {
				throw new IllegalArgumentException ("Values must be smaller than " + (n*n*n) + ":" + a[r][0]);
			}
		}
	}
	
	// HACK to be able to access the number of inspections and individual values
	public int getNumChecked() { return numChecked; }

}
