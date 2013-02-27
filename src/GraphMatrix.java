
/*
 * Graphe représenté comme matrice de booléen.
 */
public class GraphMatrix extends BMatrix {
	
	public GraphMatrix (int size) {
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
	
	
	public Vect<Float> pagerank0(int count) {
		Vect<Float> z = new Vect<Float>(this.size(), new Float(0));
		z.set(0, new Float(1));
		return GraphMatrix.pagerank(this.stoch(), z, count);
	}

	public static Vect<Float> pagerank(FMatrix m, Vect<Float> z, int count) {
		Vect<Float> tmp = z;
		for (int i = 0; i < count; i++) {
			tmp = m.multT(tmp);
		}
		return tmp;
	}
	
	

}
