import java.util.List;

public class FMatrix extends Matrix<Float> {

	private Float zero = new Float(0);

	protected Float zero () { return zero; }
	protected boolean iszero (Float elt) { return (elt == 0); }
	
	public FMatrix(int size) {
		super(size);
	}
	
	public FMatrix(List<Integer> L, List<Float> C, List<Integer> I) {
		super(L, C, I);
	}
	
	public FMatrix(List<Float> matrix, int width, int height) {
		super(matrix, width, height);
	}
	
	/*
	 * Multiplication par un vecteur.
	 */
	public FVect mult_naive(Vect<Float> l) throws IncompatibleSize {
		if (l.size() != this.size())
			throw new IncompatibleSize();
		FVect res = new FVect();
		int max = l.size();
		float tmp = 0;
		for (int i = 0; i < l.size(); i++) {
			tmp = 0;
			for (int j = 0; j < max; j++)
				tmp += l.get(j) * this.get(i, j);
			res.add(tmp);
		}
		return res;
	}
	
	public FVect mult(Vect<Float> l) throws IncompatibleSize {
		if (l.size() != this.size())
			throw new IncompatibleSize();
		FVect res = new FVect();
		float tmp;
		for (int row = 0; row < l.size(); row++) {
			tmp = 0;
			int i0 = this.L.get(row);
			int max = this.L.get(row+1);
			for (int j = i0; j < max; j++) {
				int col = this.I.get(j);
				tmp += l.get(col) * this.C.get(j);
			}
			res.add(tmp);
		}
		return res;
	}

	/*
	 * Multiplication transposée
	 */
	public FVect multT(FVect vect) throws IncompatibleSize {
		int size = this.L.size() - 1;
		if (vect.size() != size)
			throw new IncompatibleSize();

		FVect res = new FVect(size, new Float(0));

		for (int row = 0; row < size; row++) {

			int i0 = this.L.get(row);
			int max = this.L.get(row + 1);

			for (int i = i0; i < max; i++) {
				int col = this.I.get(i);
				float tmp = res.get(col) + this.C.get(i) * vect.get(row);
				res.set(col, tmp);
			}
		}

		return res;
	}
}
