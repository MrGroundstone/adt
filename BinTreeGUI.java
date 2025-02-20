package adt;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import processing.core.PApplet;

/**
 * Die Klasse {@code BinTreeGUI} implementiert eine grafische Benutzeroberfläche
 * (GUI) für die Interaktion mit einem Binärbaum ({@code BinTreeP}). Sie
 * verwendet Processing für die grafische Darstellung und Swing für
 * Dialogfenster.
 *
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (Überarbeitungen und Verbesserungen)
 * @author ChatGPT (Überarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class BinTreeGUI extends PApplet {

	private BinTree binTree;
	private PApplet sketch;
	private int buttonColor = 255;
	private int highlightColor = 150;
	String input = "";

	private int addButtonX = 50, addButtonY = 400, addButtonW = 150, addButtonH = 50;
	private Button addButton = new Button(addButtonX, addButtonY, addButtonW, addButtonH, "Add Node",
			() -> handleAddButtonClick());

	private int resetButtonX = 250, resetButtonY = 400, resetButtonW = 150, resetButtonH = 50;
	private Button resetButton = new Button(resetButtonX, resetButtonY, resetButtonW, resetButtonH, "Reset Tree",
			() -> handleResetButtonClick());

	boolean typing = false;
	float translateX = 0, translateY = 0, scaleFactor = (float) 1.0;

	/**
	 * Konstruktor für die {@code BinTreeGUI}.
	 *
	 * @param binTree Der zugehörige Binärbaum.
	 */
	public BinTreeGUI(BinTree binTree) {
		this.binTree = binTree;
	}

	public void mousePressed() {

		if (mouseX > 50 && mouseX < 250 && mouseY > 350 && mouseY < 380) {
			typing = true;
		} else {
			typing = false;
		}
	}

	/**
	 * Zeichnet den Binärbaum auf den gegebenen {@code PApplet} Sketch.
	 *
	 * @param p Der {@code PApplet} Sketch, auf dem der Baum gezeichnet wird.
	 */
	public void drawBinTree(PApplet p) {
		this.sketch = p;

		sketch.translate(translateX, translateY);
		sketch.scale(scaleFactor);

		drawTree(this.binTree, (float) sketch.width / 2, (float) 50, sketch.width / 4, binTree.getTiefe() - 1);

		drawButtons();
	}

	/**
	 * Zeichnet die Buttons auf den Bildschirm.
	 */
	private void drawButtons() {
		addButton.drawButton();
		resetButton.drawButton();

		if (sketch.focused) {
			addButton.handleClick();
			resetButton.handleClick();
		}
	}

	/**
	 * Behandelt den Klick auf den "Add Node" Button. Öffnet einen Dialog zur
	 * Eingabe des Knoteninhalts und fügt den Knoten zum Baum hinzu.
	 */
	private void handleAddButtonClick() {
		try {
			input = showInputDialog("Bitte gib den Inhalt des Knotens ein");
			if (input == null || input.trim().isEmpty()) {
				throw new IllegalArgumentException("Eingabe fehlt");
			}
			binTree.addNodeToTree(input);
		} catch (IllegalArgumentException error) {
			showMessageDialog(null, "Fehler: " + error.getMessage(), "Fehler", ERROR_MESSAGE);
		} catch (Exception e) {
			showMessageDialog(null, "Ein unerwarteter Fehler ist aufgetreten!", "Fehler", ERROR_MESSAGE);
		}

	}

	/**
	 * Behandelt den Klick auf den "Reset Tree" Button. Setzt den Baum zurück.
	 */
	private void handleResetButtonClick() {
		try {
			binTree.reset();
		} catch (Exception e) {
			showMessageDialog(null, "Fehler beim Zurücksetzen des Baums!", "Fehler", ERROR_MESSAGE);
		}
	}

	/**
	 * Zeichnet den Baum rekursiv.
	 *
	 * @param bintree  Der aktuelle Knoten.
	 * @param x        Die x-Koordinate des Knotens.
	 * @param y        Die y-Koordinate des Knotens.
	 * @param hSpacing Der horizontale Abstand zwischen den Knoten.
	 * @param depth    Die aktuelle Tiefe im Baum.
	 */
	private void drawTree(BinTree bintree, float x, float y, int hSpacing, int depth) {
		if (bintree == null || !bintree.hasItem() || depth < 0) {
			sketch.background(200); // Hintergrund neu zeichnen, falls der Baum leer ist
			return;
		}

		if (bintree.hasLeft() && bintree.getLeft() != null && bintree.getLeft().hasItem()) {
			sketch.line(x, y, x - hSpacing, y + 50);
		}
		if (bintree.hasRight() && bintree.getRight() != null && bintree.getRight().hasItem()) {
			sketch.line(x, y, x + hSpacing, y + 50);
		}

		sketch.fill(255);
		sketch.ellipse(x, y, 30, 30);

		sketch.fill(0);
		sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
		if (bintree.getItem().getZahl() != -1) {
			sketch.text("" + bintree.getItem().getZahl(), x, y);
		} else {
			sketch.text(bintree.getItem().getText(), x, y);
		}

		if (bintree.hasLeft() && bintree.getLeft() != null && bintree.getLeft().hasItem()) {
			drawTree(bintree.getLeft(), x - hSpacing, y + 50, hSpacing / 2, depth - 1);
		}

		if (bintree.hasRight() && bintree.getRight() != null && bintree.getRight().hasItem()) {
			drawTree(bintree.getRight(), x + hSpacing, y + 50, hSpacing / 2, depth - 1);
		}
	}

	/**
	 * Innere Klasse zur Repräsentation eines Buttons.
	 */
	protected class Button {
		int x, y, w, h;
		String label;
		Runnable action;
		boolean clicked = false;

		/**
		 * Konstruktor für einen Button.
		 *
		 * @param x      Die x-Koordinate des Buttons.
		 * @param y      Die y-Koordinate des Buttons.
		 * @param w      Die Breite des Buttons.
		 * @param h      Die Höhe des Buttons.
		 * @param label  Die Beschriftung des Buttons.
		 * @param action Die auszuführende Aktion beim Klicken.
		 */
		Button(int x, int y, int w, int h, String label, Runnable action) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.label = label;
			this.action = action;
		}

		/**
		 * Zeichnet den Button.
		 */
		void drawButton() {
			if (isHovered()) {
				sketch.fill(highlightColor);
			} else {
				sketch.fill(buttonColor);
			}
			sketch.rect(x, y, w, h);
			sketch.fill(0);
			sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
			sketch.text(label, x + w / 2, y + h / 2);
		}

		/**
		 * Überprüft, ob die Maus über dem Button ist.
		 *
		 * @return {@code true}, wenn die Maus über dem Button ist, sonst {@code false}.
		 */
		boolean isHovered() {
			return sketch.mouseX > x && sketch.mouseX < x + w && sketch.mouseY > y && sketch.mouseY < y + h;
		}

		/**
		 * Behandelt das Klicken auf den Button.
		 */
		void handleClick() {
			if (isHovered() && sketch.mousePressed && !clicked && sketch.focused) {
				sketch.mousePressed = false;
				clicked = true;
				action.run(); // Führt die zugewiesene Methode aus
			} else if (!sketch.mousePressed) {
				clicked = false; // Reset nach dem Klick
			}
		}

	}
}
