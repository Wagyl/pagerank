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

		float[] tab = new float[] { 0, 3, 5, 8, 1, 0, 2, 0, 0, 0, 0, 0, 0, 3,
				0, 0 };

		// FMatrix m = new FMatrix(translate(tab), 4, 4);

		/*
		 * m.print();
		 * 
		 * for (int i = 0; i < 4; i++) { for (int j = 0; j < 4; j++) {
		 * System.out.print(m.get(i, j) + " "); } System.out.println(""); }
		 */


		/*
		 * FMatrix mm = new FMatrix(4); mm.set(0, 1, new Float(3)); mm.set(0, 2,
		 * new Float(5)); mm.set(0, 3, new Float(8)); mm.set(1, 0, new
		 * Float(1)); mm.set(1, 2, new Float(2)); mm.set(3, 1, new Float(3));
		 * 
		 * mm.print();
		 */

		Graph graph = new Graph();
		graph.add(0, 1);
		graph.add(1, 0);
		graph.add(1, 2);
		graph.add(2, 3);
		graph.add(3, 0);

		graph.stoch().print();

		System.out.println();

		/*
		 * Test rapide de Graph (insertion + calcul de la matrice stochastique.
		 */
		/*
		 * Graph graphbis = new Graph(); graphbis.add(0, 1); graphbis.add(1, 0);
		 * graphbis.add(1, 2); graphbis.add(2, 3); graphbis.add(3, 0);
		 * 
		 * graphbis.stoch().print(); System.out.println();
		 */

		FVect z = new FVect(4, fl(0.25));
		FVect z1 = new FVect(4, fl(0));
		z1.set(0,  fl(1));
		z = Graph.pagerank(graph.stoch(), z, 1004);
		System.out.println(z.toString());


		/*
		 * Test méthode parse de Graph.
		 */

		
		(new Test("web-Stanford.txt", true)).pagerank(fl(0), 100);

	}
}
