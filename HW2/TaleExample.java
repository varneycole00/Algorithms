package algs.hw2;

import java.io.IOException;

public class TaleExample {
	public static void main(String[] args) throws IOException {

		for (String s : new TaleOfTwoCitiesExtractor(1)) {
			System.out.println(s);
		}

	}
}
