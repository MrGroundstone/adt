package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse BinTree (Binärbaum):
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
 * Update: Hendrik Bodenstein 27.11.2024
 */
public class BinTree {

	private Inhalt inh = new Inhalt(); // Inhalt der Wurzel des Baumes
	private BinTree links = null; // linker Teilbaum
	private BinTree rechts = null; // rechter Teilbaum

	/*
	 * Ein leerer Binärbaum wird angelegt.
	 */
	public BinTree() {
		inh = new Inhalt();
		links = null; // Kein Baum vorhanden
		rechts = null; // Kein Baum vorhanden
	}

	/*
	 * Ein Binärbaum mit dem übergebenen Inhalt wird angelegt.
	 */
	public BinTree(Inhalt i) {
		inh = i;
	}

	/*
	 * Ein Binärbaum mit der übergebenen Zeichenkette als Inhalt wird angelegt.
	 */
	public BinTree(String s) {
		inh.setText(s);
	}

	/*
	 * Ein Binärbaum mit der Ã¼bergebenen Ganzzahl als Inhalt wird angelegt.
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
	 * zurückgegeben, sonst der Wert falsch.
	 */
	public boolean hasItem() {
		if (inh.istLeer()) {
			return false;
		}
		return true;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird zurückgegeben.
	 */
	public Inhalt getItem() {
		return inh;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird auf den übergebenen Inhalt gesetzt.
	 */
	public void setItem(Inhalt inhalt) {
		inh = inhalt;
	}

	/*
	 * Der Inhalt der Wurzel des Baumes wird gelöscht.
	 */
	public void deleteItem() {
		inh = new Inhalt();
	}

	/*
	 * Wenn es sich bei dem Baum um ein Blatt handelt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 */
	public boolean isLeaf() {
		if ((!inh.istLeer()) && (links == null) && (rechts == null)) {
			return true;
		}
		return false;
	}

	/*
	 * Wenn der Baum einen linken Teilbaum besitzt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 */
	public Boolean hasLeft() {
		return links != null;
	}

	/*
	 * Der linke Teilbaum des Baumes wird zurückgegeben.
	 */
	public BinTree getLeft() {
		return links;
	}

	/*
	 * Der linke Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 */
	public void setLeft(BinTree b) {
		links = b;
	}

	/*
	 * Der linke Teilbaum des Baumes wird gelöscht.
	 */
	public void deleteLeft() {
		links = null;
	}

	/*
	 * Wenn der Baum einen linken Teilbaum besitzt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 */
	public Boolean hasRight() {
		return rechts != null;
	}

	/*
	 * Der rechte Teilbaum des Baumes wird zurückgegeben.
	 */
	public BinTree getRight() {
		return rechts;
	}

	/*
	 * Der rechte Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 */
	public void setRight(BinTree b) {
		rechts = b;
	}

	/*
	 * Der linke Teilbaum des Baumes wird gelöscht.
	 */
	public void deleteRight() {
		rechts = null;
	}

	/*
	 * Visualisierung eines Binärbaums in Processing - Wrapper-Methode
	 */
	public void drawBinTree(PApplet sketch) {
		drawTree(sketch, this, sketch.width / 2, 50, sketch.width / 4, this.getTiefe());
	}

	/*
	 * Methode zur Bestimmung der Tiefe, die für das Zeichnen benötigt wird.
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

		// Zeichne den aktuellen Knoten (Kreis) zuletzt, um Linien zu überdecken
		sketch.fill(255); // Weißer Hintergrund für den Knoten
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