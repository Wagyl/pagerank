import java.util.ArrayList;
import java.util.List;


public class Graph extends BMatrix {
	
	public Graph (int size) {
		super(size);
	}
	
	public void addArc (int i, int j) {
		this.set(i, j, new Boolean(true));
	}
	
	public FMatrix stoch () {
		FMatrix stoch = new FMatrix(this.size());
		
		for (int row = 0; row < L.size() - 1; row++) {
			int deg = L.get(row+1) - L.get(row);
			float value = ((float) 1.0) / ((float) deg);
			for (int index = L.get(row) ; index < L.get(row + 1); index++) {
				 if (C.get(index)) {
					 stoch.set(row, I.get(index), value);
				 }
			}
		}
		
		return stoch;
	}
	
	
	public List<Float> pagerank0(int count) {
		List<Float> z = new ArrayList<Float>(this.size());
		z.add(new Float(1));
		for (int i = 1; i < this.size(); i++) 
			z.add(new Float(0));
		return Graph.pagerank(this.stoch(), z, count);
	}

	public static List<Float> pagerank(FMatrix m, List<Float> z, int count) {
		List<Float> tmp = z;
		for (int i = 0; i < count; i++) {
			tmp = m.multT(tmp);
		}
		return tmp;
	}
	
	

}
