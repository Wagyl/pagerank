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
			if (verbose) {
				System.out.println("Parsing completed : graph built.");
				System.out.println("Nodes : " +(this.graph.size() + 1) +  "     Edges : " + this.graph.bindsCount());
			}
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
	
	
	public void pagerank(float zap, int count, float epsilon, int z0) {
		int size = graph.size() +1;
		FVect z;
		if (z0 >= 0 && z0 < size) {
			z = new FVect(size, (float) 0);
			z.set(z0, (float) 1);
		}
		else {
			float value = (float) 1 / size;
			z = new FVect(size, value);
		}
		System.out.println("Pagerank computation : " + count + " itérations...");
		Result result = Graph.zapPagerank(stochastic, z, count, epsilon, zap);
		System.out.println("Computation done.");
		
		System.out.println();
		result.print();
	}

}
