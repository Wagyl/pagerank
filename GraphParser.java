import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class GraphParser {

	private int max;
	private CSVReader reader;
	private List<Peer> resList;
	private String source;

	public GraphParser(String source) throws FileNotFoundException {
		this.max = 0;
		this.source = source;
		this.reader = new CSVReader(new FileReader(source));
		this.resList = new ArrayList<Peer>();
	}

	/* Parse le fichier ligne par ligne en virant les commentaires */
	public void work() throws IOException {
		String[] nextLine;
		int count = 1;

		System.out.println("Parsing de : " + this.source);
		while ((nextLine = reader.readNext()) != null) {
			if (!nextLine[0].startsWith("#"))
				try {
					this.resList.add(parseLine(nextLine[0]));
				} catch (BadLineFormatException e) {
					System.err.println("Ligne ignorée " + count
							+ " - Bad Format");
				}
			count++;
		}
		System.out.println("Parsing terminé");
	}

	/* Parse une ligne pour en faire des token */
	private Peer parseLine(String line) throws BadLineFormatException {
		StringTokenizer st = new StringTokenizer(line);
		if (st.countTokens() != 2)
			throw new BadLineFormatException();

		Peer res = new Peer();
		for (int i = 0; i < 2; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp > this.max)
				max = tmp;
			res.add(tmp);
		}
		return res;
	}

	/* Récupérer le max */
	public int getMax() {
		return this.max;
	}

	/* Récupérer la liste */
	public List<Peer> getList() {
		return this.resList;
	}
}
