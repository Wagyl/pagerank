import java.util.List;


public class BMatrix extends Matrix<Boolean> {

	public BMatrix(int size) {
		super(size);
	}
	
	public BMatrix(List<Boolean> matrix, int width, int height) {
		super(matrix, width, height);
	}

	protected Boolean zero() {
		return new Boolean(true);
	}

	protected boolean iszero(Boolean elt) {
		return !elt;
	}

}
