import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

public class MainTest {

	public static boolean verbose = true;

	public static Float fl(int i) {
		return new Float(i);
	}

	public static Float fl(double d) {
		return new Float(d);
	}

	public static String loadFile(String addr) {
		return Paths.get(addr).toAbsolutePath().toString();
	}

	public static void main(String[] args) {

		float zap = 0;
		int count = 100;
		float epsilon = 0;
		int z0 = -1;

		if (args.length < 1)
			System.out
					.println("Usage : java MainTest file [zap count epsilon z]");
		else {

			FileReader file;
			try {
				file = new FileReader(loadFile(args[0]));
			} catch (FileNotFoundException e) {
				System.err.println("File not found.");
				return;
			}

			if (args.length > 1)
				zap = Float.parseFloat(args[1]);
			if (args.length > 2)
				count = Integer.parseInt(args[2]);
			if (args.length > 3)
				epsilon = Float.parseFloat(args[3]);
			if (args.length > 4)
				z0 = Integer.parseInt(args[4]);

			(new Test(file, verbose)).pagerank(zap, count, epsilon, z0);

		}
	}
}
