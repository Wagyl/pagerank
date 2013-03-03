
/*
 * Résultat d'un pagerank.
 */

public class Result {

	private FVect result;
	private int count;
	private float diff;
	private float zap;
	
	public Result(FVect result, int count, float diff, float zap) {
		this.result = result;
		this.count = count;
		this.diff = diff;
		this.zap = zap;
	}
	
	public void print() {
		System.out.println("Résultat du pagerank : ");
		System.out.println("Facteur zap = " +zap);
		System.out.println("Après " + count + " itérations, convergence d'une précision de " +diff + ".");
		System.out.println();
		System.out.println(result.shortString());
		System.out.println();
	}
	
}
