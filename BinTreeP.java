package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse BinTreeP (Binärbaum):
 * 
 * Die Implementierung lässt als Inhalte Zeichenketten und Ganzzahlen zu.
 * 
 * Die interne Verwaltung des Inhalts des Binärbaums erfolgt über eine interne
 * Klasse Inhalt.
 * 
 * Die Funktionalität und die Bezeichnungen des Binärbaums entsprechen den
 * Vorgaben der Thematischen Schwerpunkte für die schriftliche Abiturprüfung
 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
 * bei einem leeren Binärbaum verhindern.
 *
 * Alternativ ließe sich auch eine spezielle Klasse Binärbaum implementieren,
 * die die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet.
 * Dazu müsste aber für jede Aufgabe ggf. eine neue Klasse Binärbaum erzeugt
 * werden. Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object,
 * so ist diese zwar universell nutzbar, man benötigt beim Zugriff auf den
 * Binärbaum dann aber häufig Typecasting.
 * 
 * Update: Hendrik Bodenstein 16.12.2024
 */

public class BinTreeP {

	private BinTreeGUI gui = new BinTreeGUI(this);
	
	Inhalt inh = new Inhalt();
	BinTreeP links = null;
	BinTreeP rechts = null;

	/*
	 * Ein leerer Binärbaum wird angelegt.
	 */
	public BinTreeP() {
		inh = new Inhalt();
		links = null;
		rechts = null;
	}

	/*
	 * Ein Binärbaum mit dem übergebenen Inhalt wird angelegt.
	 */
	public BinTreeP(Inhalt i) {
		inh = i;
	}

	/*
	 * Ein Binärbaum mit der übergebenen Zeichenkette als Inhalt wird angelegt.
	 */
	public BinTreeP(String s) {
		inh.setText(s);
	}

	/*
	 * Ein Binärbaum mit der übergebenen Ganzzahl als Inhalt wird angelegt.
	 */
	public BinTreeP(int z) {
		inh.setZahl(z);
	}

	/*
	 * Der Baum wird zu einem Beispielbaum umgewandelt.
	 */
	public void beispielBaumErstellen() {
		// Einzelne Knoten
		BinTreeP A = new BinTreeP("A");
		BinTreeP D = new BinTreeP("D");
		BinTreeP G = new BinTreeP("G");
		BinTreeP X = new BinTreeP("X");
		BinTreeP Y = new BinTreeP("Y");
		BinTreeP Z = new BinTreeP("Z");
		BinTreeP S = new BinTreeP("S");
		BinTreeP T = new BinTreeP("T");
		BinTreeP L = new BinTreeP("L");
		BinTreeP M = new BinTreeP("M");
		BinTreeP N = new BinTreeP("N");

		// Wurzel
		this.inh.setText("W");

		// Linker Teilbaum
		G.setLeft(A);
		D.setLeft(X);
		Y.setRight(Z);
		D.setRight(Y);
		G.setRight(D);
		this.setLeft(G); // Setze den linken Teilbaum

		// Rechter Teilbaum
		T.setLeft(N);
		M.setLeft(L);
		S.setLeft(T);
		S.setRight(M);
		this.setRight(S); // Setze den rechten Teilbaum
	}

	public void beispielSuchbaumErstellen() {
		BinTreeP aTree = new BinTreeP(0);
		BinTreeP bTree = new BinTreeP(1);
		BinTreeP cTree = new BinTreeP(2);
		BinTreeP dTree = new BinTreeP(3);
		BinTreeP eTree = new BinTreeP(5);
		BinTreeP gTree = new BinTreeP(8);
		BinTreeP hTree = new BinTreeP(9);
		BinTreeP iTree = new BinTreeP(10);
		BinTreeP jTree = new BinTreeP(12);
		BinTreeP kTree = new BinTreeP(13);
		BinTreeP lTree = new BinTreeP(15);
		this.inh.setZahl(7);
		bTree.setLeft(aTree);
		dTree.setLeft(cTree);
		dTree.setRight(eTree);
		bTree.setRight(dTree);
		this.setLeft(bTree);
		hTree.setLeft(gTree);
		hTree.setRight(iTree);
		jTree.setLeft(hTree);
		kTree.setRight(lTree);
		jTree.setRight(kTree);
		this.setRight(jTree);
	}

	public boolean hasItem() {
		return !inh.istLeer();
	}

	public Inhalt getItem() {
		return inh;
	}

	public void setItem(Inhalt inhalt) {
		inh = inhalt;
	}

	public void deleteItem() {
		inh = new Inhalt();
	}

	public boolean isLeaf() {
		return (!inh.istLeer()) && (links == null) && (rechts == null);
	}

	public Boolean hasLeft() {
		return links != null;
	}

	public BinTreeP getLeft() {
		return links;
	}

	public void setLeft(BinTreeP b) {
		links = b;
	}

	public void deleteLeft() {
		links = null;
	}

	public Boolean hasRight() {
		return rechts != null;
	}

	public BinTreeP getRight() {
		return rechts;
	}

	public void setRight(BinTreeP b) {
		rechts = b;
	}

	public void deleteRight() {
		rechts = null;
	}

	int getTiefe() {
		if (!hasItem()) {
			return 0;
		}
		int leftDepth = (links != null && links.hasItem()) ? links.getTiefe() : 0;
		int rightDepth = (rechts != null && rechts.hasItem()) ? rechts.getTiefe() : 0;
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	@SuppressWarnings("unused")
	void addNodeToTree(int value) {
		if (!hasItem()) {
			inh.setZahl(value);
		} else if (links == null) {
			links = new BinTreeP(value);
		} else if (rechts == null) {
			rechts = new BinTreeP(value);
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}
	}

	void addNodeToTree(String value) {
		if (!hasItem()) {
			inh.setText(value);
		} else if (links == null) {
			links = new BinTreeP(value);
		} else if (rechts == null) {
			rechts = new BinTreeP(value);
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}

	}
	
	void reset() {
		this.inh = new Inhalt();
		this.links = null;
		this.rechts = null;
	}
	
	public void drawBinTreeP(PApplet sketch) {
		gui.drawBinTree(sketch);
	}

	/*
	 * Klasse Inhalt zur internen Verwaltung der einzelnen Elemente des Binärbaums.
	 */
	protected class Inhalt {
		private int zahl = -1;
		private String text = "";

		/*
		 * Ein neuer Inhalt wird angelegt.
		 */
		public Inhalt() {
			zahl = -1;
			text = "";
		}

		/*
		 * Die Ganzzahl des Inhalts wird auf die übergebene Zahl gesetzt.
		 */
		public void setZahl(int z) {
			zahl = z;
		}

		/*
		 * Die Zeichenkette des Inhalts wird auf die übergebene Zeichenkette gesetzt.
		 */
		public void setText(String s) {
			text = s;
		}

		/*
		 * Die Ganzzahl des Inhalts wird zurückgegeben.
		 */
		public int getZahl() {
			return zahl;
		}

		/*
		 * Die Zeichenkette des Inhalts wird zurückgegeben.
		 */
		public String getText() {
			return text;
		}

		/*
		 * Wenn der Inhalt leer ist, wird der Wert wahr zurückgegeben, sonst der Wert
		 * falsch.
		 */
		public boolean istLeer() {

			return (zahl == -1 && text.equals(""));
		}
	}
}