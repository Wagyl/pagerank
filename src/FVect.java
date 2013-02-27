import java.util.List;


public class FVect extends Vect<Float> {

	public FVect() {
		super();
	}
	
	public FVect(int length) {
		super(length);
	}
	
	public FVect(int length, Float value) {
		super(length, value);
	}
	
	public FVect(Float[] data) {
		super(data);
	}
	
	public FVect(List<Float> l) {
		super(l);
	}
	
	/* Multiplication et addition par une constante. */
	
	public void op_add(Float lambda) {
		for (int index = 0; index < size(); index++) {
			set(index, get(index) + lambda);
		}
	}
	
	public void op_mul(Float lambda) {
		for (int index = 0; index < size(); index++) {
			set(index, get(index) * lambda);
		}
	}
	
}
