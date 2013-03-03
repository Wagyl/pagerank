import java.util.ArrayList;
import java.util.List;


public class Vect<T> {
	
	private List<T> list;
	
	/*
	 * Différents cnstructeurs utiles aux tests.
	 */
	
	/*
	 * Vecteur vide.
	 */
	public Vect() {
		list = new ArrayList<T>();
	}
	
	public Vect(int length) {
		list = new ArrayList<T>(length);
	}
	
	/*
	 * Vecteur de longueur [length] et de valeur par défaut [value].
	 */
	public Vect(int length, T value) {
		list = new ArrayList<T>(length);
		for (int i = 0; i < length; i++)
			list.add(value);
	}
	
	/*
	 * Conversion d'un tableau en vecteur.
	 */
	public Vect(T[] data) {
		list = new ArrayList<T>(data.length);
		for (int i = 0; i < data.length; i++) {
			list.add(data[i]);
		}
	}
	
	/*
	 * Conversion d'une liste en vecteur.
	 */
	public Vect(List<T> l) {
		this.list = l;
	}
	
	/*
	 * Méthodes utilitaires. 
	 */
	
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
	
	/*
	 * Représentation d'un vecteur. 
	 */
	public String toString() {
		String buffer = "[ ";
		for(T elt : list) 
			buffer += elt.toString() + " ";
		buffer += "]";
		return buffer;
	}
	
	
	/*
	 * Retourne une représentation courte du vecteur :
	 * si la taille du vecteur est supérieur à 20, ne montre que les
	 * 10 premiers et 10 denriers éléments.
	 */
	public String shortString() {
		int size = size();
		if (size > 20) {
			String buffer = "[ ";
			for(int i = 0; i < 10; i++)
				buffer += list.get(i).toString() + " ";
			buffer += "  ...  ";
			for(int i = size - 10; i < size; i++)
				buffer += (list.get(i)).toString() + " ";
			buffer += "]";
			return buffer;
		}
		else
			return toString();
	}

}
