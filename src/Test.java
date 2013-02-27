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
	
	
	public void pagerank(float zap, Vect<Float> z0, int count) {
		
	}

}
