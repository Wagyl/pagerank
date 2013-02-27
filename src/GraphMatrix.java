
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

}
