package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse BinTree (Bin�rbaum):
 * 
 * Die Implementierung l�sst als Inhalte Zeichenketten und Ganzzahlen zu.
 * 
 * Die interne Verwaltung des Inhalts des Bin�rbaums erfolgt �ber eine interne
 * Klasse Inhalt.
 * 
 * Die Funktionalit�t und die Bezeichnungen des Bin�rbaums entsprechen den
 * Vorgaben der Thematischen Schwerpunkte f�r die schriftliche Abiturpr�fung
 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
 * bei einem leeren Bin�rbaum verhindern.
 *
 * Alternativ lie�e sich auch eine spezielle Klasse Bin�rbaum implementieren,
 * die die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet.
 * Dazu m�sste aber f�r jede Aufgabe ggf. eine neue Klasse Bin�rbaum erzeugt
 * werden. Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object,
 * so ist diese zwar universell nutzbar, man ben�tigt beim Zugriff auf den
 * Bin�rbaum dann aber h�ufig Typecasting.
 * 
 * Update: Hendrik Bodenstein 27.11.2024
 */
public class BinTree {

	private Inhalt inh = new Inhalt(); // Inhalt der Wurzel des Baumes
	private BinTree links = null; // linker Teilbaum
	private BinTree rechts = null; // rechter Teilbaum

	/*
	 * Ein leerer Bin�rbaum wird angelegt.
	 */
	public BinTree() {
		inh = new Inhalt();
		links = null; // Kein Baum vorhanden
		rechts = null; // Kein Baum vorhanden
	}

	/*
	 * Ein Bin�rbaum mit dem �bergebenen Inhalt wird angelegt.
	 */
	public BinTree(Inhalt i) {
		inh = i;
	}

	/*
	 * Ein Bin�rbaum mit der �bergebenen Zeichenkette als Inhalt wird angelegt.
	 */
	public BinTree(String s) {
		inh.setText(s);
	}

	/*
	 * Ein Bin�rbaum mit der übergebenen Ganzzahl als Inhalt wird angelegt.
	 */
	public BinTree(int z) {
		inh.setZahl(z);
	}

	/*
	 * Der Baum wird zu einem Beispielbaum umgewandelt.
	 */
	public void beispielBaumErstellen() {
		// Einzelne Knoten
		BinTree A = new BinTree("A");
		BinTree D = new BinTree("D");
		BinTree G = new BinTree("G");
		BinTree X = new BinTree("X");
		BinTree Y = new BinTree("Y");
		BinTree Z = new BinTree("Z");
		BinTree S = new BinTree("S");
		BinTree T = new BinTree("T");
		BinTree L = new BinTree("L");
		BinTree M = new BinTree("M");
		BinTree N = new BinTree("N");

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
		BinTree aTree = new BinTree(0);
		BinTree bTree = new BinTree(1);
		BinTree cTree = new BinTree(2);
		BinTree dTree = new BinTree(3);
		BinTree eTree = new BinTree(5);
		BinTree gTree = new BinTree(8);
		BinTree hTree = new BinTree(9);
		BinTree iTree = new BinTree(10);
		BinTree jTree = new BinTree(12);
		BinTree kTree = new BinTree(13);
		BinTree lTree = new BinTree(15);
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

	/*
	 * Wenn die Wurzel des Baumes keinen Inhalt besitzt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
	 */
	public boolean hasItem() {
		if (inh.istLeer()) {
			return false;
		}
		return true;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird zur�ckgegeben.
	 */
	public Inhalt getItem() {
		return inh;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird auf den �bergebenen Inhalt gesetzt.
	 */
	public void setItem(Inhalt inhalt) {
		inh = inhalt;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird gel�scht.
	 */
	public void deleteItem() {
		inh = new Inhalt();
	}

	/*
	 * Wenn es sich bei dem Baum um ein Blatt handelt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
	 */
	public boolean isLeaf() {
		if ((!inh.istLeer()) && (links == null) && (rechts == null)) {
			return true;
		}
		return false;
	}

	/*
	 * Wenn der Baum einen linken Teilbaum besitzt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
	 */
	public Boolean hasLeft() {
		return links != null;
	}

	/*
	 * Der linke Teilbaum des Baumes wird zur�ckgegeben.
	 */
	public BinTree getLeft() {
		return links;
	}

	/*
	 * Der linke Teilbaum des Baumes wird auf den �bergebenen Baum gesetzt.
	 */
	public void setLeft(BinTree b) {
		links = b;
	}

	/*
	 * Der linke Teilbaum des Baumes wird gel�scht.
	 */
	public void deleteLeft() {
		links = null;
	}

	/*
	 * Wenn der Baum einen linken Teilbaum besitzt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
	 */
	public Boolean hasRight() {
		return rechts != null;
	}

	/*
	 * Der rechte Teilbaum des Baumes wird zur�ckgegeben.
	 */
	public BinTree getRight() {
		return rechts;
	}

	/*
	 * Der rechte Teilbaum des Baumes wird auf den �bergebenen Baum gesetzt.
	 */
	public void setRight(BinTree b) {
		rechts = b;
	}

	/*
	 * Der linke Teilbaum des Baumes wird gel�scht.
	 */
	public void deleteRight() {
		rechts = null;
	}

	/*
	 * Visualisierung eines Bin�rbaums in Processing - Wrapper-Methode
	 */
	public void drawBinTree(PApplet sketch) {
		drawTree(sketch, this, sketch.width / 2, 50, sketch.width / 4, this.getTiefe());
	}

	/*
	 * Methode zur Bestimmung der Tiefe, die f�r das Zeichnen ben�tigt wird.
	 */
	private int getTiefe() {
		if (!hasItem()) {
			return 0;
		}
		int leftDepth = (links != null && links.hasItem()) ? links.getTiefe() : 0;
		int rightDepth = (rechts != null && rechts.hasItem()) ? rechts.getTiefe() : 0;
		return Math.max(leftDepth, rightDepth) + 1;
	}

	/*
	 * Umsetzung der Wrapper-Methode
	 */
	private void drawTree(PApplet sketch, BinTree binTree, float x, float y, int hSpacing, int depth) {
		// Abbruchbedingungen: Leerer Baum oder keine weitere Tiefe
		if (binTree == null || !binTree.hasItem() || depth <= 0) {
			return;
		}

		// Rekursive Zeichnung des linken Teilbaums (falls vorhanden)
		if (binTree.hasLeft() && binTree.getLeft() != null) {
			// Zeichne Verbindungslinie zuerst
			sketch.line(x, y, x - hSpacing, y + 50);
			// Zeichne den linken Teilbaum rekursiv
			drawTree(sketch, binTree.getLeft(), x - hSpacing, y + 50, hSpacing / 2, depth - 1);
		}

		// Rekursive Zeichnung des rechten Teilbaums (falls vorhanden)
		if (binTree.hasRight() && binTree.getRight() != null) {
			// Zeichne Verbindungslinie zuerst
			sketch.line(x, y, x + hSpacing, y + 50);
			// Zeichne den rechten Teilbaum rekursiv
			drawTree(sketch, binTree.getRight(), x + hSpacing, y + 50, hSpacing / 2, depth - 1);
		}

		// Zeichne den aktuellen Knoten (Kreis) zuletzt, um Linien zu �berdecken
		sketch.fill(255); // Wei�er Hintergrund f�r den Knoten
		sketch.ellipse(x, y, 30, 30); // Kreis mit Durchmesser 30

		// Zeichne den Text im Knoten
		sketch.fill(0); // Schwarzer Text
		sketch.textAlign(PApplet.CENTER, PApplet.CENTER); // Zentrierter Text
		if (binTree.getItem().getZahl() != -1) {
			sketch.text("" + binTree.getItem().getZahl(), x, y); // Zahl anzeigen
		} else {
			sketch.text(binTree.getItem().getText(), x, y); // Text anzeigen
		}
	}

	/*
	 * Klasse Inhalt zur internen Verwaltung der einzelnen Elemente des Bin�rbaums.
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
		 * Die Ganzzahl des Inhalts wird auf die �bergebene Zahl gesetzt.
		 */
		public void setZahl(int z) {
			zahl = z;
		}

		/*
		 * Die Zeichenkette des Inhalts wird auf die �bergebene Zeichenkette gesetzt.
		 */
		public void setText(String s) {
			text = s;
		}

		/*
		 * Die Ganzzahl des Inhalts wird zur�ckgegeben.
		 */
		public int getZahl() {
			return zahl;
		}

		/*
		 * Die Zeichenkette des Inhalts wird zur�ckgegeben.
		 */
		public String getText() {
			return text;
		}

		/*
		 * Wenn der Inhalt leer ist, wird der Wert wahr zur�ckgegeben, sonst der Wert
		 * falsch.
		 */
		public boolean istLeer() {

			return (zahl == -1 && text.equals(""));
		}

	}
}