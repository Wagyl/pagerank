import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			int degree = binds.size();
			float value = ((float) 1) / ((float) degree);
			L.add(L.get(node) + degree);
			for (int arc : binds) {
				C.add(value);
				I.add(arc);
			}
		}

		FMatrix stoch = new FMatrix(L, C, I);
		return stoch;
	}

}
