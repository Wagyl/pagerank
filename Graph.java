import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

	private HashMap<Integer, List<Integer>> data;
	private int size;

	public Graph() {
		this.data = new HashMap<Integer, List<Integer>>();
		this.size = 0;
	}

	public void add(Integer node, Integer arc) {
		if (node > size)
			size = node;
		List<Integer> list = data.get(node);
		if (list == null)
			list = new ArrayList<Integer>(1);
		list.add(arc);
		data.put(node, list);
	}
	
	public List<Integer> get(Integer node) {
		return data.get(node);
	}
	
	public int size() {
		return size;
	}

}
