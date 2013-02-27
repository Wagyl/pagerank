import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class Graph {

	/*
	 * À chaque noeud est associée la liste des noeuds auxquels il mène par un
	 * arc du graphe.
	 */
	private HashMap<Integer, List<Integer>> data;
	/* Nombre de noeuds (clés) */
	private int size;
	/* Nombre d'arcs */
	private int bindsCount;

	public Graph() {
		this.data = new HashMap<Integer, List<Integer>>();
		this.size = 0;
		this.bindsCount = 0;
	}

	public void add(Integer node, Integer arc) {
		if (node > size)
			size = node;
		List<Integer> list = data.get(node);
		if (list == null)
			list = new ArrayList<Integer>(1);
		if (!list.contains(arc)) {
			list.add(arc);
			data.put(node, list);
			bindsCount++;
		}
	}

	public List<Integer> get(Integer node) {
		return data.get(node);
	}

	public int size() {
		return size;
	}

	public FMatrix stoch() {
		List<Integer> L = new ArrayList<Integer>(size + 1);
		List<Float> C = new ArrayList<Float>(bindsCount);
		List<Integer> I = new ArrayList<Integer>(bindsCount);

		L.add(0);
		for (int node = 0; node <= size; node++) {
			List<Integer> binds = get(node);
			if (binds != null) {
				int degree = binds.size();
				float value = ((float) 1) / ((float) degree);
				L.add(L.get(node) + degree);
				for (int arc : binds) {
					C.add(value);
					I.add(arc);
				}
			}
			else
				L.add(L.get(node));
		}

		FMatrix stoch = new FMatrix(L, C, I);
		return stoch;
	}

	/* */
	public void parse(String source) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(source));

		String[] nextLine;
		int count = 1;

		System.out.println("Parsing de : " + source);
		while ((nextLine = reader.readNext()) != null) {
			if (!nextLine[0].startsWith("#"))
				try {
					parseLine(nextLine[0]);
				} catch (BadLineFormatException e) {
					System.err.println("Ligne ignorée " + count
							+ " - Bad Format");
				}
			count++;
		}
		System.out.println("Parsing terminé");

		reader.close();
	}

	/* */
	private void parseLine(String line) throws BadLineFormatException {
		int tmpKey, tmpVal;
		StringTokenizer st = new StringTokenizer(line);

		if (st.countTokens() != 2)
			throw new BadLineFormatException();

		tmpKey = Integer.parseInt(st.nextToken());
		tmpVal = Integer.parseInt(st.nextToken());

		this.add(tmpKey, tmpVal);
	}

}
