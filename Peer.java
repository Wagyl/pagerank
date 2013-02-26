public class Peer {

	private int firstElement;
	private int secondElement;
	private int pos;

	public Peer() {
		this.pos = 0;
	}

	/* Maj du premier élément */
	public void addFst(int toAdd) {
		this.firstElement = toAdd;
	}

	/* Maj du second élément */
	public void addSnd(int toAdd) {
		this.secondElement = toAdd;
	}

	/* Fonction utilitaire privée */
	private void majPos() {
		pos = (pos + 1) % 2;
	}

	/* Maj séquentielle de la paire */
	public void add(int toAdd) {
		switch (pos) {
		case 0:
			addFst(toAdd);
			break;
		case 1:
			addSnd(toAdd);
			break;
		}
		majPos();
	}

	/* Récupérer le premier élément de la paire */
	public int fst() {
		return this.firstElement;
	}

	/* Récupérer le second élément de la paire */
	public int snd() {
		return this.secondElement;
	}

}
