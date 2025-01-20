package adt;

import processing.core.PApplet;

import java.util.*;

import static processing.core.PApplet.print;
import static processing.core.PApplet.println;

/**
 * Implementierung der Klasse BinTree (Bin�rbaum). Die Implementierung l�sst als
 * Inhalte Zeichenketten und Ganzzahlen zu. Die interne Verwaltung des Inhalts
 * des Bin�rbaums erfolgt �ber eine interne Klasse Inhalt. Die Funktionalit�t
 * und die Bezeichnungen des Bin�rbaums entsprechen den Vorgaben der
 * Thematischen Schwerpunkte f�r die schriftliche Abiturpr�fung 2018 in
 * Informatik in Niedersachsen. Das bedeutet auch, dass in der Implementierung
 * keine "Absicherungen" enthalten sind, die z. B. das Entnehmen bei einem
 * leeren Bin�rbaum verhindern.
 *
 * Alternativ lie�e sich auch eine spezielle Klasse Bin�rbaum implementieren,
 * die die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet.
 * Dazu m�sste aber f�r jede Aufgabe ggf. eine neue Klasse Bin�rbaum erzeugt
 * werden. Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object,
 * so ist diese zwar universell nutzbar, man ben�tigt beim Zugriff auf den
 * Bin�rbaum dann aber h�ufig Typecasting.
 *
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (�berarbeitungen und Verbesserungen)
 * @author ChatGPT (�berarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class BinTree {

	private BinTreeGUI gui = new BinTreeGUI(this);

	private Inhalt inh = new Inhalt(); // Inhalt der Wurzel des Baumes
	private BinTree links = null; // linker Teilbaum
	private BinTree rechts = null; // rechter Teilbaum

	/**
	 * Ein leerer Bin�rbaum wird angelegt.
	 */
	public BinTree() {
		inh = new Inhalt();
		links = null; // Kein Baum vorhanden
		rechts = null; // Kein Baum vorhanden
	}

	/**
	 * Ein Bin�rbaum mit dem �bergebenen Inhalt wird angelegt.
	 *
	 * @param i Der Inhalt f�r den neuen Bin�rbaum.
	 */
	public BinTree(Inhalt i) {
		inh = i;
	}

	/**
	 * Ein Bin�rbaum mit der �bergebenen Zeichenkette als Inhalt wird angelegt.
	 *
	 * @param s Die Zeichenkette als Inhalt.
	 */
	public BinTree(String s) {
		inh.setText(s);
	}

	/**
	 * Ein Bin�rbaum mit der �bergebenen Ganzzahl als Inhalt wird angelegt.
	 *
	 * @param z Die Ganzzahl als Inhalt.
	 */
	public BinTree(int z) {
		inh.setZahl(z);
	}

	/**
	 * Der Baum wird zu einem Beispielbaum umgewandelt.
	 */
	public void traversiere(String input, String type) {
		ArrayList<BinTree> nodesList = new ArrayList<>();
		String rootName = input.split(" ")[0];

		for (String segment : input.split(" ")) {
			nodesList.add(new BinTree(segment));
		}


		// calculate nodes per level
		List<Integer> nodesPerLevel = new ArrayList<>();
		int level = 0;
		int n = nodesList.size();
		while (n > 0) {
			int nodesAtCurrentLevel = Math.min((int) Math.pow(2, level), n);
			nodesPerLevel.add(nodesAtCurrentLevel);
			n -= nodesAtCurrentLevel;
			level++;
		}


		// level Traversion
		if (Objects.equals(type, "level")) {
			// create root element
			this.inh.setText(rootName);
			nodesList.remove(0);

			// current item to append
			int pointer = nodesPerLevel.get(1);
			// node the item is appendend to
			int lastNode = 0;
			for (int i = 1; i < nodesPerLevel.size(); i++ )  {
				for (int j = 0; j < nodesPerLevel.get(i) ; j++ )  {
					if (i == 1) {
						if (j % 2 == 0) {
							this.setLeft(nodesList.get(0));
						}else {
							this.setRight(nodesList.get(1));
						}
					}else{
						BinTree current = nodesList.get(pointer);
						if (j % 2 == 0) {
							nodesList.get(lastNode).setLeft(current);
						}else {
							nodesList.get(lastNode).setRight(current);
							lastNode++;
						}
						pointer++;
					}

				}
			}

		}

	}



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
		this.setLeft(G); // Setze den linken Teilbaum
		G.setRight(D);

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
	 * zur�ckgegeben, sonst der Wert falsch.
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
	 * Der Inhalt der Wurzel des Baumes wird zur�ckgegeben.
	 *
	 * @return Der Inhalt der Wurzel.
	 */
	public Inhalt getItem() {
		return inh;
	}

	/**
	 * Der Inhalt der Wurzel des Baumes wird auf den �bergebenen Inhalt gesetzt.
	 *
	 * @param inhalt Der neue Inhalt f�r die Wurzel.
	 */
	public void setItem(Inhalt inhalt) {
		inh = inhalt;
	}

	/**
	 * Der Inhalt der Wurzel des Baumes wird gel�scht.
	 */
	public void deleteItem() {
		inh = new Inhalt();
	}

	/**
	 * Wenn es sich bei dem Baum um ein Blatt handelt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
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
	 * zur�ckgegeben, sonst der Wert falsch.
	 *
	 * @return true, wenn ein linker Teilbaum existiert, sonst false.
	 */
	public Boolean hasLeft() {
		return links != null;
	}

	/**
	 * Der linke Teilbaum des Baumes wird zur�ckgegeben.
	 *
	 * @return Der linke Teilbaum.
	 */
	public BinTree getLeft() {
		return links;
	}

	/**
	 * Der linke Teilbaum des Baumes wird auf den �bergebenen Baum gesetzt.
	 *
	 * @param b Der neue linke Teilbaum.
	 */
	public void setLeft(BinTree b) {
		links = b;
	}

	/**
	 * Der linke Teilbaum des Baumes wird gel�scht.
	 */
	public void deleteLeft() {
		links = null;
	}

	/**
	 * Wenn der Baum einen rechten Teilbaum besitzt, wird der Wert wahr
	 * zur�ckgegeben, sonst der Wert falsch.
	 *
	 * @return true, wenn ein rechter Teilbaum existiert, sonst false.
	 */
	public Boolean hasRight() {
		return rechts != null;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird zur�ckgegeben.
	 *
	 * @return Der rechte Teilbaum.
	 */
	public BinTree getRight() {
		return rechts;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird auf den �bergebenen Baum gesetzt.
	 *
	 * @param b Der neue rechte Teilbaum.
	 */
	public void setRight(BinTree b) {
		rechts = b;
	}

	/**
	 * Der rechte Teilbaum des Baumes wird gel�scht.
	 */
	public void deleteRight() {
		rechts = null;
	}

	/**
	 * Visualisierung eines Bin�rbaums in Processing - Wrapper-Methode
	 *
	 * @param sketch Das PApplet-Objekt f�r die Visualisierung.
	 */
	public void drawBinTree(PApplet sketch) {
		gui.drawBinTree(sketch);
	}

	/**
	 * Methode zur Bestimmung der Tiefe, die f�r das Zeichnen ben�tigt wird.
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
	 * F�gt einen Knoten zum Baum hinzu (zuf�llige Verteilung).
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
	 * F�gt einen Knoten zum Baum hinzu (zuf�llige Verteilung).
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
	 * Setzt den Baum zur�ck.
	 */
	void reset() {
		this.inh = new Inhalt();
		this.links = null;
		this.rechts = null;
	}

	/**
	 * Klasse Inhalt zur internen Verwaltung der einzelnen Elemente des Bin�rbaums.
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
		 * Die Ganzzahl des Inhalts wird auf die �bergebene Zahl gesetzt.
		 *
		 * @param z Die neue Ganzzahl.
		 */
		public void setZahl(int z) {
			zahl = z;
		}

		/**
		 * Die Zeichenkette des Inhalts wird auf die �bergebene Zeichenkette gesetzt.
		 *
		 * @param s Die neue Zeichenkette.
		 */
		public void setText(String s) {
			text = s;
		}

		/**
		 * Die Ganzzahl des Inhalts wird zur�ckgegeben.
		 *
		 * @return Die Ganzzahl.
		 */
		public int getZahl() {
			return zahl;
		}

		/**
		 * Die Zeichenkette des Inhalts wird zur�ckgegeben.
		 *
		 * @return Die Zeichenkette.
		 */
		public String getText() {
			return text;
		}

		/**
		 * Wenn der Inhalt leer ist, wird der Wert wahr zur�ckgegeben, sonst der Wert
		 * falsch.
		 *
		 * @return true, wenn der Inhalt leer ist, sonst false.
		 */
		public boolean istLeer() {

			return (zahl == -1 && text.equals(""));
		}

	}
}