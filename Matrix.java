import java.util.ArrayList;
import java.util.List;

public abstract class Matrix <T> {

	protected List<T> C = new ArrayList<T>();
	protected List<Integer> L;
	protected List<Integer> I = new ArrayList<Integer>();

	protected abstract T zero();
	protected abstract boolean iszero(T elt);
	
	public Matrix(int size) {
		this.L = new ArrayList<Integer>(size + 1);
		for (int i = 0; i <= size; i++) {
			L.add(0);
		}
	}
	
	public int size() {
		return L.size() - 1;
	}

	public Matrix(List<T> matrix, int width, int height) {
		L = new ArrayList<Integer>(height + 1);
		for (int i = 0; i <= height; i++) {
			L.add(0);
		}
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				int index = row * width + column;
				T elt = matrix.get(index);
				if (!iszero(elt)) {
					C.add(elt);
					I.add(column);
					incrI(L, row + 1);
				}
			}
		}
	}
	
	/*
	 * elt ne devrait pas être zero ! 
	 */
	public void set(int row, int column, T elt) {
		int i0 = this.L.get(row);
		int max = this.L.get(row + 1);

		int i = i0;
		boolean present = false;
		for (; i < max; i++) {
			int col = this.I.get(i);
			if (col == column)
				present = true;
			if (col >= column)
				break;
		}
		if (present)
			this.C.set(i, elt);
		else {
			this.I.add(i, column);
			this.C.add(i, elt);
			incrI(this.L, row + 1);
		}
	}

	public void print() {
		System.out.print("L : ");
		for (int i = 0; i < L.size(); i++)
			System.out.print(L.get(i) + " ");
		System.out.println();
		System.out.print("C : ");
		for (int i = 0; i < C.size(); i++)
			System.out.print(C.get(i) + " ");
		System.out.println();
		System.out.print("I : ");
		for (int i = 0; i < I.size(); i++)
			System.out.print(I.get(i) + " ");
		System.out.println();
	}

	protected void incrI(List<Integer> list, int index) {
		for (int i = index; i < list.size(); i++) {
			list.set(i, list.get(i) + 1);
		}
	}

	/*
	 * Retourne l'index de l'élément désiré dans C. Retourne -1 si l'élément
	 * n'est pas dans C (i.e. la case de la matrice contient 0).
	 */
	private int getIndex(int row, int column) {
		int max = L.get(row + 1);
		int i0 = L.get(row);
		for (int i = i0; i < max; i++) {
			if (I.get(i) == column)
				return i;
		}
		return -1;
	}

	/*
	 * Retourne l'élément en (row, column) de la matrice.
	 */
	public T get(int row, int column) {
		int index = getIndex(row, column);
		if (index == -1)
			return zero();
		else
			return C.get(index);
	}
}
