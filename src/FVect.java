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
	
	/* Distance entre deux vecteurs */
	
	public float norm(FVect other) throws IncompatibleSize {
		int size = this.size();
		if (other.size() != size) {
			throw new IncompatibleSize();
		}
		float diff = 0;
		float tmp;
		for (int i = 0; i < size; i++) {
			tmp = this.get(i) - other.get(i);
			diff += tmp * tmp;
		}
		return (float) Math.sqrt(diff);
	}
	
}
