import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

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

	public static void mainparser() {
		try {
			GraphParser parsed = new GraphParser(
					(new MainTest()).loadFile("web-Stanford.txt"));
			parsed.work();
			GraphMatrix graphStanford = new GraphMatrix(parsed.getMax());
			int max = parsed.getMax();
			int i = 0;
			for (Peer p : parsed.getList()) {
				graphStanford.addArc(p.fst(), p.snd());
				if (i % 10000 == 0)
					System.out.print(" ... " + (i * 100 / max) + "%   ");
				i++;
			}
			System.out.println();
			System.out.println("Graph construit");
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		float[] tab = new float[] { 0, 3, 5, 8, 1, 0, 2, 0, 0, 0, 0, 0, 0, 3,
				0, 0 };

		FMatrix m = new FMatrix(translate(tab), 4, 4);

		/*
		 * m.print();
		 * 
		 * for (int i = 0; i < 4; i++) { for (int j = 0; j < 4; j++) {
		 * System.out.print(m.get(i, j) + " "); } System.out.println(""); }
		 */

		List<Float> vect = translate(new float[] { 1, 3, 0, 1 });
		// print(vect);

		List<Float> r = m.multT(vect);
		// print(r);

		/*
		 * FMatrix mm = new FMatrix(4); mm.set(0, 1, new Float(3)); mm.set(0, 2,
		 * new Float(5)); mm.set(0, 3, new Float(8)); mm.set(1, 0, new
		 * Float(1)); mm.set(1, 2, new Float(2)); mm.set(3, 1, new Float(3));
		 * 
		 * mm.print();
		 */

		GraphMatrix graph = new GraphMatrix(4);
		graph.addArc(0, 1);
		graph.addArc(1, 0);
		graph.addArc(1, 2);
		graph.addArc(2, 3);
		graph.addArc(3, 0);

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

		List<Float> z = translate(new float[] { new Float(0.25),
				new Float(0.25), new Float(0.25), new Float(0.25) });
		List<Float> z0 = translate(new float[] { new Float(1), 0, 0, 0 });
		z = GraphMatrix.pagerank(graph.stoch(), z0, 1004);
		print(z);

		/*
		 * Test fichier Stanford, à compléter
		 */
		// mainparser();

		/*
		 * Test méthode parse de Graph.
		 */

		Graph maingraph = new Graph();
		
		System.out.println("Parsing...");
		try {
			maingraph.parse((new MainTest()).loadFile("web-Stanford.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Parsing done.");
		
		System.out.println("Stochastique computation...");
		FMatrix mainstoch = maingraph.stoch();
		System.out.println("Stochastique computation done.");
		
		System.out.println("Result :");
		System.out.println(mainstoch.get(1, 6548) + "    " +mainstoch.get(1, 6547));
	}
}
