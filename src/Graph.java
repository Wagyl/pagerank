import java.io.IOException;
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

	public void build(String source) throws IOException {
		(new GraphParser(this)).parseFile(source);
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
			} else
				L.add(L.get(node));
		}

		FMatrix stoch = new FMatrix(L, C, I);
		return stoch;
	}

	public FVect pagerank0(int count) {
		FVect z = new FVect(this.size(), new Float(0));
		z.set(0, new Float(1));
		return Graph.pagerank(this.stoch(), z, count);
	}
	
	public FVect pagerank1(int count) {
		Float value = new Float((float) 1 / this.size());
		FVect z = new FVect(this.size(), value);
		return Graph.pagerank(this.stoch(), z, count);
	}

	public static FVect pagerank(FMatrix m, FVect z, int count) {
		FVect tmp = z;
		for (int i = 0; i < count; i++) {
			try {
				tmp = m.multT(tmp);
			} catch (IncompatibleSize e) {
				e.printStackTrace();
				return null;
			}
		}
		return tmp;
	}
	
	public static FVect zapPagerank(FMatrix m, FVect z, int count, float zap) {
		FVect tmp = z;
		float size = z.size();
		for (int i = 0; i < count; i++) {
			try {
				tmp = m.multT(tmp);
				tmp.op_mul(1 - zap);
				tmp.op_add(zap / size);
			} catch (IncompatibleSize e) {
				e.printStackTrace();
				return null;
			}
		}
		return tmp;
		
	}
}
