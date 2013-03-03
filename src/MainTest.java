import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static Float fl(int i) {
		return new Float(i);
	}
	public static Float fl(double d) {
		return new Float(d);
	}
	
	public static List<Float> translate(float[] tab) {
		List<Float> res = new ArrayList<Float>(tab.length);
		for (int i = 0; i < tab.length; i++) {
			res.add(tab[i]);
		}
		return res;
	}

	public static void print(List<Float> l) {
		for (int i = 0; i < l.size(); i++)
			System.out.print(l.get(i) + " ");
		System.out.println();
	}
	
	

	public String loadFile(String addr) {
		return this.getClass().getResource(addr).getPath();
	}

	public static void main(String[] args) {

		/*
		 * Test méthode parse de Graph.
		 */

		(new Test("web-Stanford.txt", true)).pagerank(fl(0), 100);

	}
}
