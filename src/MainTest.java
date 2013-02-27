import java.io.FileNotFoundException;
import java.io.IOException;
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

	public static void mainparser() {
		try {
			GraphParserDeprecated parsed = new GraphParserDeprecated(
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

		Vect<Float> vect = new Vect<Float>(new Float[] {fl(1), fl(3), fl(0), fl(1)});
		Vect<Float> r = m.multT(vect);
		//System.out.println(r.toString());

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

		Vect<Float> z = new Vect<Float>(4, fl(0.25));
		Vect<Float> z1 = new Vect<Float>(4, fl(0));
		z1.set(0,  fl(1));
		z = GraphMatrix.pagerank(graph.stoch(), z, 1004);
		System.out.println(z.toString());

		/*
		 * Test fichier Stanford, à compléter
		 */
		// mainparser();

		/*
		 * Test méthode parse de Graph.
		 */

		System.out.println("Parsing...");
		try {
			Graph maingraph = new Graph();
			maingraph.build((new MainTest()).loadFile("web-Stanford.txt"));

			System.out.println("Parsing done.");

			System.out.println("Stochastique computation...");
			FMatrix mainstoch = maingraph.stoch();
			System.out.println("Stochastique computation done.");

			System.out.println("Result :");
			System.out.println(mainstoch.get(1, 6548) + "    "
					+ mainstoch.get(1, 6547));
			System.out.println(mainstoch.get(71616, 165189) + "    " +mainstoch.get(71616, 141370) 
					+ "   " +mainstoch.get(71616, 165181));
			
			int size = maingraph.size() +1;
			float value = (float) 1 / size;
			System.out.println("size : " +size);
			System.out.println("value : " +value);
			Vect<Float> z0 = new Vect<Float>(size, value);

			System.out.println("size : " +mainstoch.size());
			int count = 100;
			System.out.println("Calcul de pagerank : " + count + " itérations...");
			z0 = Graph.pagerank(mainstoch, z0, count);
			System.out.println("Calcul terminé.");
			
			System.out.println(z0.get(2));			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
