public class Peer {

	private int firstElement;
	private int secondElement;
	private int pos;

	public Peer() {
		this.pos = 0;
	}

	/* Maj du premier �l�ment */
	public void addFst(int toAdd) {
		this.firstElement = toAdd;
	}

	/* Maj du second �l�ment */
	public void addSnd(int toAdd) {
		this.secondElement = toAdd;
	}

	/* Fonction utilitaire priv�e */
	private void majPos() {
		pos = (pos + 1) % 2;
	}

	/* Maj s�quentielle de la paire */
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

	/* R�cup�rer le premier �l�ment de la paire */
	public int fst() {
		return this.firstElement;
	}

	/* R�cup�rer le second �l�ment de la paire */
	public int snd() {
		return this.secondElement;
	}

}
