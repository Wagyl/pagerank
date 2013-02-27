import java.util.ArrayList;
import java.util.List;


public class Vect<T> {
	
	private List<T> list;
	
	public Vect() {
		list = new ArrayList<T>();
	}
	
	public Vect(int length) {
		list = new ArrayList<T>(length);
	}
	
	public Vect(int length, T value) {
		list = new ArrayList<T>(length);
		for (int i = 0; i < length; i++)
			list.add(value);
	}
	
	public Vect(T[] data) {
		list = new ArrayList<T>(data.length);
		for (int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
	}
	
	public Vect(List<T> l) {
		this.list = l;
	}
	
	public int size() {
		return list.size();
	}

	public T get(int index) {
		return list.get(index);
	}
	
	public void set(int index, T elt) {
		list.set(index, elt);
	}
	
	public void add(T elt) {
		list.add(elt);
	}
	
	
	public String toString() {
		String buffer = "[ ";
		for(T elt : list) 
			buffer += elt.toString() + " ";
		buffer += "]";
		return buffer;
	}

}
