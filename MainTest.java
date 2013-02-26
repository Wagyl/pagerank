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
		return this.getClass().getResource("web-Stanford.txt").getPath();
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
		print(r);

		/*
		 * FMatrix mm = new FMatrix(4); mm.set(0, 1, new Float(3)); mm.set(0, 2,
		 * new Float(5)); mm.set(0, 3, new Float(8)); mm.set(1, 0, new
		 * Float(1)); mm.set(1, 2, new Float(2)); mm.set(3, 1, new Float(3));
		 * 
		 * mm.print();
		 */

		Graph graph = new Graph(4);
		graph.addArc(0, 1);
		graph.addArc(1, 0);
		graph.addArc(1, 2);
		graph.addArc(2, 3);
		graph.addArc(3, 0);

		graph.stoch().print();

		System.out.println();

		List<Float> z = translate(new float[] { new Float(0.25),
				new Float(0.25), new Float(0.25), new Float(0.25) });
		List<Float> z0 = translate(new float[] { new Float(1), 0, 0, 0 });
		z = Graph.pagerank(graph.stoch(), z0, 1004);
		print(z);

		/*
		 * Test fichier Stanford, à compléter
		 */
		try {
			GraphParser parsed = new GraphParser(
					(new MainTest()).loadFile("web-Stanford.txt"));
			parsed.work();
			Graph graphStanford = new Graph(parsed.getMax());
			for (Peer p : parsed.getList())
				graphStanford.addArc(p.fst(), p.snd());
			System.out.println("Graph construit");
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
