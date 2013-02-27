import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import au.com.bytecode.opencsv.CSVReader;

public class GraphParserDeprecated {

	private int max;
	private CSVReader reader;
	private List<Peer> resList;
	private String source;
	private Map<Integer, List<Integer>> resMap;

	public GraphParserDeprecated(String source) throws FileNotFoundException {
		this.max = 0;
		this.source = source;
		this.reader = new CSVReader(new FileReader(source), '\n');
		this.resList = new ArrayList<Peer>();
		this.resMap = new HashMap<Integer, List<Integer>>();
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
					System.err.println("Ligne ignor�e " + count
							+ " - Bad Format");
				}
			count++;
		}
		System.out.println("Parsing termin�");
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

	/* */
	public void altWork() throws IOException {
		String[] nextLine;
		int count = 1;

		System.out.println("Parsing de : " + this.source);
		while ((nextLine = reader.readNext()) != null) {
			if (!nextLine[0].startsWith("#"))
				try {
					altParseLine(nextLine[0]);
				} catch (BadLineFormatException e) {
					System.err.println("Ligne ignorée " + count
							+ " - Bad Format");
				}
			count++;
		}
		System.out.println("Parsing terminé");
	}

	private String checkStringNumber(String source) {
		if (!source.endsWith(","))
			return source;
		else
			return source.substring(0, source.length() - 1);
	}

	/* */
	private void altParseLine(String line) throws BadLineFormatException {
		int tmpKey, tmpVal;
		StringTokenizer st = new StringTokenizer(line);

		if (st.countTokens() != 2)
			throw new BadLineFormatException();

		tmpKey = Integer.parseInt(checkStringNumber(st.nextToken()));
		tmpVal = Integer.parseInt(checkStringNumber(st.nextToken()));

		if (tmpKey > max)
			max = tmpKey;

		majMap(tmpKey, tmpVal);
	}

	/* Mise à jour de la Map */
	private void majMap(int key, int val) {
		List<Integer> tmpList = this.resMap.get(key);

		if (tmpList == null) {
			tmpList = new ArrayList<Integer>();
			tmpList.add(val);
			this.resMap.put(key, tmpList);
		} else
			tmpList.add(val);
	}

	/* R�cup�rer le max */
	public int getMax() {
		return this.max;
	}

	/* R�cup�rer la liste */
	public List<Peer> getList() {
		return this.resList;
	}

	public static void main(String args[]) {
		try {
			GraphParserDeprecated test = new GraphParserDeprecated(
					(new MainTest()).loadFile("web-Stanford.txt"));
			test.altWork();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
