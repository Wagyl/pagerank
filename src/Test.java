import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	private Graph graph;
	private FMatrix stochastic;

	public Test(String file, boolean verbose) {
		try {
			this.graph = new Graph();
			if (verbose)
			System.out.println("Parsing : graph construction...");
			this.graph.build((new MainTest()).loadFile(file));
			if (verbose)
				System.out.println("Parsing completed : graph built.");
			compute_stochastic(verbose);
		} catch (FileNotFoundException e1) {
			System.err.println("File not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void compute_stochastic(boolean verbose) {
		if (verbose)
			System.out.println("Stochastic computation...");
		this.stochastic = this.graph.stoch();
		if (verbose)
			System.out.println("Stochastic computation done.");
	}
	
	
	public void pagerank(float zap, int count) {
		int size = graph.size() +1;
		float value = (float) 1 / size;
		FVect z0 = new FVect(size, value);
		System.out.println("Pagerank computation : " + count + " itérations...");
		z0 = Graph.zapPagerank(stochastic, z0, count, zap);
		System.out.println("Computation done.");
		
		System.out.println(z0.shortString());	
	}

}
