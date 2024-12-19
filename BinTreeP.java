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

	// Button-Koordinaten und Größen
	private int addButtonX = 50, addButtonY = 400, addButtonW = 150, addButtonH = 50;
	private int resetButtonX = 250, resetButtonY = 400, resetButtonW = 150, resetButtonH = 50;

	// Benutzer-Input
	private String inputText = ""; // Eingabetext für neue Knoten
	private boolean isAddingNode = false; // Status, ob ein neuer Knoten hinzugefügt wird

	private Inhalt inh = new Inhalt(); // Inhalt der Wurzel des Baumes
	private BinTreeP links = null; // linker Teilbaum
	private BinTreeP rechts = null; // rechter Teilbaum

	/*
	 * Ein leerer Binärbaum wird angelegt.
	 */
	public BinTreeP() {
		inh = new Inhalt();
		links = null; // Kein Baum vorhanden
		rechts = null; // Kein Baum vorhanden
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
	 * Ein Binärbaum mit der Ã¼bergebenen Ganzzahl als Inhalt wird angelegt.
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
	public BinTreeP getLeft() {
		return links;
	}

	/*
	 * Der linke Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 */
	public void setLeft(BinTreeP b) {
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
	public BinTreeP getRight() {
		return rechts;
	}

	/*
	 * Der rechte Teilbaum des Baumes wird auf den übergebenen Baum gesetzt.
	 */
	public void setRight(BinTreeP b) {
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
	float translateX = 0, translateY = 0, scaleFactor = (float)1.0;

	public void drawBinTreeP(PApplet sketch) {
	    sketch.translate(translateX, translateY);
	    sketch.scale(scaleFactor);
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
	private void drawTree(PApplet sketch, BinTreeP BinTreeP, float x, float y, int hSpacing, int depth) {
	    if (BinTreeP == null || !BinTreeP.hasItem() || depth <= 0) return;

	    int levelSpacing = 50; // Vertikaler Abstand zwischen Ebenen

	    if (BinTreeP.hasLeft()) {
	        float childX = x - hSpacing;
	        float childY = y + levelSpacing;
	        sketch.line(x, y, childX, childY); // Linie zeichnen
	        drawTree(sketch, BinTreeP.getLeft(), childX, childY, hSpacing / 2, depth - 1);
	    }

	    if (BinTreeP.hasRight()) {
	        float childX = x + hSpacing;
	        float childY = y + levelSpacing;
	        sketch.line(x, y, childX, childY); // Linie zeichnen
	        drawTree(sketch, BinTreeP.getRight(), childX, childY, hSpacing / 2, depth - 1);
	    }

	    sketch.fill(255);
	    sketch.ellipse(x, y, 30, 30); // Kreis für den Knoten
	    sketch.fill(0);
	    sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
	    if (BinTreeP.getItem().getZahl() != -1) {
	        sketch.text("" + BinTreeP.getItem().getZahl(), x, y);
	    } else {
	        sketch.text(BinTreeP.getItem().getText(), x, y);
	    }
	}


	//Ab hier Implementation von Interaktionsmöglichkeiten in Processing
	public void drawButtons(PApplet sketch) {
		drawButton(sketch, addButtonX, addButtonY, addButtonW, addButtonH, "Add Node");
		drawButton(sketch, resetButtonX, resetButtonY, resetButtonW, resetButtonH, "Reset Tree");

		// Eingabetext anzeigen
		sketch.fill(0);
		sketch.textSize(16);
		sketch.text("Input: " + inputText, 50, 380);
	}

	private void drawButton(PApplet sketch, int x, int y, int w, int h, String label) {
		sketch.fill(200);
		sketch.rect(x, y, w, h);
		sketch.fill(0);
		sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
		sketch.text(label, x + w / 2, y + h / 2);
	}

	public void handleClick(int mouseX, int mouseY) {
		// "Add Node"-Button
		if (mouseX > addButtonX && mouseX < addButtonX + addButtonW && mouseY > addButtonY
				&& mouseY < addButtonY + addButtonH) {
			isAddingNode = true; // Aktiviert den Modus für das Hinzufügen
		}

		// "Reset Tree"-Button
		if (mouseX > resetButtonX && mouseX < resetButtonX + resetButtonW && mouseY > resetButtonY
				&& mouseY < resetButtonY + resetButtonH) {
			this.inh = new Inhalt(); // Inhalt der Wurzel leeren
			this.links = null; // Linken Teilbaum entfernen
			this.rechts = null; // Rechten Teilbaum entfernen
		}
	}

	public void handleInput(char key) {
		if (key == '\n') { // Enter-Taste
			if (isAddingNode && inputText.length() > 0) {
				// Neuen Knoten hinzufügen
				try {
					int value = Integer.parseInt(inputText); // Zahl hinzufügen
					addNodeToTree(value);
				} catch (NumberFormatException e) {
					addNodeToTree(inputText); // Zeichenkette hinzufügen
				}

				inputText = ""; // Eingabe zurücksetzen
				isAddingNode = false;
			}
		} else if (key == PApplet.BACKSPACE) {
			// Letztes Zeichen entfernen
			if (inputText.length() > 0) {
				inputText = inputText.substring(0, inputText.length() - 1);
			}
		} else {
			inputText += key; // Neues Zeichen hinzufügen
		}
	}

	// Hilfsmethode zum Hinzufügen in die Baumstruktur
	private void addNodeToTree(int value) {
		if (inh.istLeer()) {
			inh.setZahl(value); // Aktuellen Knoten initialisieren
		} else if (links == null) {
			links = new BinTreeP(value); // Links hinzufügen
		} else if (rechts == null) {
			rechts = new BinTreeP(value); // Rechts hinzufügen
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}
	}

	// Hilfsmethode zum Hinzufügen in die Baumstruktur
	private void addNodeToTree(String value) {
		if (inh.istLeer()) {
			inh.setText(value); // Aktuellen Knoten initialisieren
		} else if (links == null) {
			links = new BinTreeP(value); // Links hinzufügen
		} else if (rechts == null) {
			rechts = new BinTreeP(value); // Rechts hinzufügen
		} else {
			if (Math.random() < 0.5) {
				links.addNodeToTree(value);
			} else {
				rechts.addNodeToTree(value);
			}
		}
	}

	public void mouseDragged(int mouseX, int mouseY, int pmouseX, int pmouseY, int mouseButton) {
	    if (mouseButton == PApplet.RIGHT) {
	        translateX += mouseX - pmouseX;
	        translateY += mouseY - pmouseY;
	    }
	}

	public void mouseWheel(float e) {
	    scaleFactor += e > 0 ? -0.1 : 0.1;
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