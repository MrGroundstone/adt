package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse BinTree (Binärbaum). Die Implementierung lässt als
 * Inhalte Zeichenketten und Ganzzahlen zu. Die interne Verwaltung des Inhalts
 * des Binärbaums erfolgt über eine interne Klasse Inhalt. Die Funktionalität
 * und die Bezeichnungen des Binärbaums entsprechen den Vorgaben der
 * Thematischen Schwerpunkte für die schriftliche Abiturprüfung 2018 in
 * Informatik in Niedersachsen. Das bedeutet auch, dass in der Implementierung
 * keine "Absicherungen" enthalten sind, die z. B. das Entnehmen bei einem
 * leeren Binärbaum verhindern.
 *
 * Alternativ ließe sich auch eine spezielle Klasse Binärbaum implementieren,
 * die die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet.
 * Dazu müsste aber für jede Aufgabe ggf. eine neue Klasse Binärbaum erzeugt
 * werden. Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object,
 * so ist diese zwar universell nutzbar, man benötigt beim Zugriff auf den
 * Binärbaum dann aber häufig Typecasting.
 *
 * Update: Hendrik Bodenstein 14.01.2025
 */
public class BinTree {

	private BinTreeGUI gui = new BinTreeGUI(this);

	private Inhalt inh = new Inhalt(); // Inhalt der Wurzel des Baumes
	private BinTree links = null; // linker Teilbaum
	private BinTree rechts = null; // rechter Teilbaum

	/**
	 * Ein leerer Binärbaum wird angelegt.
	 */
	public BinTree() {
		inh = new Inhalt();
		links = null; // Kein Baum vorhanden
		rechts = null; // Kein Baum vorhanden
	}

	/**
	 * Ein Binärbaum mit dem übergebenen Inhalt wird angelegt.
	 * 
	 * @param i Der Inhalt für den neuen Binärbaum.
	 */
	public BinTree(Inhalt i) {
		inh = i;
	}

	/**
	 * Ein Binärbaum mit der übergebenen Zeichenkette als Inhalt wird angelegt.
	 * 
	 * @param s Die Zeichenkette als Inhalt.
	 */
	public BinTree(String s) {
		inh.setText(s);
	}

	/**
	 * Ein Binärbaum mit der übergebenen Ganzzahl als Inhalt wird angelegt.
	 * 
	 * @param z Die Ganzzahl als Inhalt.
	 */
	public BinTree(int z) {
		inh.setZahl(z);
	}

	/**
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

	/**
	 * Der Baum wird zu einem Beispiel-Suchbaum umgewandelt.
	 */
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

	/**
	 * Wenn die Wurzel des Baumes keinen Inhalt besitzt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 * 
	 * @return true, wenn die Wurzel leer ist, sonst false.
	 */
	public boolean hasItem() {
		if (inh.istLeer()) {
			return false;
		}
		return true;
	}

	/**
	 * Der Inhalt der Wurzel des Baumes wird zurückgegeben.
	 * 
	 * @return Der Inhalt der Wurzel.
	 */
	public Inhalt getItem() {
		return inh;
	}

	/**
	 * Der Inhalt der Wurzel des Baumes wird auf den übergebenen Inhalt gesetzt.
	 * 
	 * @param inhalt Der neue Inhalt für die Wurzel.
	 */
	public void setItem(Inhalt inhalt) {
		inh = inhalt;
	}

	/**
	 * Der Inhalt der Wurzel des Baumes wird gelöscht.
	 */
	public void deleteItem() {
		inh = new Inhalt();
	}

	/**
	 * Wenn es sich bei dem Baum um ein Blatt handelt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 * 
	 * @return true, wenn der Baum ein Blatt ist, sonst false.
	 */
	public boolean isLeaf() {
		if ((!inh.istLeer()) && (links == null) && (rechts == null)) {
			return true;
		}
		return false;
	}

	/**
	 * Wenn der Baum einen linken Teilbaum besitzt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 * 
	 * @return true, wenn ein linker Teilbaum existiert, sonst false.
	 */
	public Boolean hasLeft() {
		return links != null;
	}

	/**
	 * Der linke Teilbaum des Baumes wird zurückgegeben.
	 * 
	 * @return Der linke Teilbaum.
	 */
	public BinTree getLeft() {
		return links;
	}

	/**
	 * Der linke Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 * 
	 * @param b Der neue linke Teilbaum.
	 */
	public void setLeft(BinTree b) {
		links = b;
	}

	/**
	 * Der linke Teilbaum des Baumes wird gelöscht.
	 */
	public void deleteLeft() {
		links = null;
	}

	/**
	 * Wenn der Baum einen rechten Teilbaum besitzt, wird der Wert wahr
	 * zurückgegeben, sonst der Wert falsch.
	 * 
	 * @return true, wenn ein rechter Teilbaum existiert, sonst false.
	 */
	public Boolean hasRight() {
		return rechts != null;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird zurückgegeben.
	 * 
	 * @return Der rechte Teilbaum.
	 */
	public BinTree getRight() {
		return rechts;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 * 
	 * @param b Der neue rechte Teilbaum.
	 */
	public void setRight(BinTree b) {
		rechts = b;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird gelöscht.
	 */
	public void deleteRight() {
		rechts = null;
	}

	/**
	 * Visualisierung eines Binärbaums in Processing - Wrapper-Methode
	 * 
	 * @param sketch Das PApplet-Objekt für die Visualisierung.
	 */
	public void drawBinTree(PApplet sketch) {
		gui.drawBinTree(sketch);
	}

	/**
	 * Methode zur Bestimmung der Tiefe, die für das Zeichnen benötigt wird.
	 * 
	 * @return Die Tiefe des Baumes.
	 */
	int getTiefe() {
		if (!hasItem()) {
			return 0;
		}
		int leftDepth = (links != null && links.hasItem()) ? links.getTiefe() : 0;
		int rightDepth = (rechts != null && rechts.hasItem()) ? rechts.getTiefe() : 0;
		return Math.max(leftDepth, rightDepth) + 1;
	}

	/**
	 * Fügt einen Knoten zum Baum hinzu (zufällige Verteilung).
	 * 
	 * @param value Der Wert des neuen Knotens (Integer).
	 */
	void addNodeToTree(int value) {
		if (!hasItem()) {
			inh.setZahl(value);
		} else if (links == null) {
			links = new BinTree(value);
		} else if (rechts == null) {
			rechts = new BinTree(value);
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}
	}

	/**
	 * Fügt einen Knoten zum Baum hinzu (zufällige Verteilung).
	 * 
	 * @param value Der Wert des neuen Knotens (String).
	 */
	void addNodeToTree(String value) {
		if (!hasItem()) {
			inh.setText(value);
		} else if (links == null) {
			links = new BinTree(value);
		} else if (rechts == null) {
			rechts = new BinTree(value);
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}

	}

	/**
	 * Setzt den Baum zurück.
	 */
	void reset() {
		this.inh = new Inhalt();
		this.links = null;
		this.rechts = null;
	}

	/**
	 * Klasse Inhalt zur internen Verwaltung der einzelnen Elemente des Binärbaums.
	 */
	protected class Inhalt {
		private int zahl = -1;
		private String text = "";

		/**
		 * Ein neuer Inhalt wird angelegt.
		 */
		public Inhalt() {
			zahl = -1;
			text = "";
		}

		/**
		 * Die Ganzzahl des Inhalts wird auf die übergebene Zahl gesetzt.
		 * 
		 * @param z Die neue Ganzzahl.
		 */
		public void setZahl(int z) {
			zahl = z;
		}

		/**
		 * Die Zeichenkette des Inhalts wird auf die übergebene Zeichenkette gesetzt.
		 * 
		 * @param s Die neue Zeichenkette.
		 */
		public void setText(String s) {
			text = s;
		}

		/**
		 * Die Ganzzahl des Inhalts wird zurückgegeben.
		 * 
		 * @return Die Ganzzahl.
		 */
		public int getZahl() {
			return zahl;
		}

		/**
		 * Die Zeichenkette des Inhalts wird zurückgegeben.
		 * 
		 * @return Die Zeichenkette.
		 */
		public String getText() {
			return text;
		}

		/**
		 * Wenn der Inhalt leer ist, wird der Wert wahr zurückgegeben, sonst der Wert
		 * falsch.
		 * 
		 * @return true, wenn der Inhalt leer ist, sonst false.
		 */
		public boolean istLeer() {

			return (zahl == -1 && text.equals(""));
		}

	}
}