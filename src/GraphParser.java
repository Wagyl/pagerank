import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class GraphParser {

	private int max;
	private CSVReader reader;
	private Graph data;

	public GraphParser(Graph data) {
		this.max = 0;
		this.data = data;
	}

	/* Parse le fichier */
	public void parseFile(String source) throws IOException {
		String[] nextLine;
		int count = 1;
		reader = new CSVReader(new FileReader(source), '\n');

		while ((nextLine = reader.readNext()) != null) {
			if (!nextLine[0].startsWith("#"))
				try {
					parseLine(nextLine[0]);
				} catch (BadLineFormatException e) {
					System.err.println("Ligne ignorée " + count
							+ " - Bad Format");
				}
			count++;
		}
		reader.close();
	}

	/* Parse une ligne */
	private void parseLine(String line) throws BadLineFormatException {
		int tmpKey, tmpVal;
		StringTokenizer st = new StringTokenizer(line);

		if (st.countTokens() != 2)
			throw new BadLineFormatException();

		tmpKey = Integer.parseInt(checkStringNumber(st.nextToken()));
		tmpVal = Integer.parseInt(checkStringNumber(st.nextToken()));

		if (tmpKey > max)
			max = tmpKey;

		this.data.add(tmpKey, tmpVal);
	}

	/* Formatage des entiers */
	private String checkStringNumber(String source) {
		if (!source.endsWith(","))
			return source;
		else
			return source.substring(0, source.length() - 1);
	}

	// public static void main(String args[]) {
	// try {
	// GraphParser test = new GraphParser(
	// (new MainTest()).loadFile("web-Stanford.txt"));
	// test.altWork();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
