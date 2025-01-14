package adt;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import processing.core.PApplet;

/**
 * Die Klasse {@code BinTreeGUI} implementiert eine grafische Benutzeroberfl�che
 * (GUI) f�r die Interaktion mit einem Bin�rbaum ({@code BinTreeP}). Sie
 * verwendet Processing f�r die grafische Darstellung und Swing f�r
 * Dialogfenster.
 *
 * @author (Ihr Name oder urspr�nglicher Autor, falls bekannt)
 * @version 1.0
 */
public class BinTreeGUI extends PApplet {

	private BinTreeP binTree;
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
	 * Konstruktor f�r die {@code BinTreeGUI}.
	 *
	 * @param binTree Der zugeh�rige Bin�rbaum.
	 */
	public BinTreeGUI(BinTreeP binTree) {
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
	 * Zeichnet den Bin�rbaum auf den gegebenen {@code PApplet} Sketch.
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
	 * Behandelt den Klick auf den "Add Node" Button. �ffnet einen Dialog zur
	 * Eingabe des Knoteninhalts und f�gt den Knoten zum Baum hinzu.
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
	 * Behandelt den Klick auf den "Reset Tree" Button. Setzt den Baum zur�ck.
	 */
	private void handleResetButtonClick() {
		try {
			binTree.reset();
		} catch (Exception e) {
			showMessageDialog(null, "Fehler beim Zur�cksetzen des Baums!", "Fehler", ERROR_MESSAGE);
		}
	}

	/**
	 * Zeichnet den Baum rekursiv.
	 *
	 * @param BinTreeP Der aktuelle Knoten.
	 * @param x        Die x-Koordinate des Knotens.
	 * @param y        Die y-Koordinate des Knotens.
	 * @param hSpacing Der horizontale Abstand zwischen den Knoten.
	 * @param depth    Die aktuelle Tiefe im Baum.
	 */
	private void drawTree(BinTreeP BinTreeP, float x, float y, int hSpacing, int depth) {
		if (BinTreeP == null || !BinTreeP.hasItem() || depth < 0) {
			sketch.background(200); // Hintergrund neu zeichnen, falls der Baum leer ist
			return;
		}

		if (BinTreeP.hasLeft() && BinTreeP.getLeft() != null && BinTreeP.getLeft().hasItem()) {
			sketch.line(x, y, x - hSpacing, y + 50);
		}
		if (BinTreeP.hasRight() && BinTreeP.getRight() != null && BinTreeP.getRight().hasItem()) {
			sketch.line(x, y, x + hSpacing, y + 50);
		}

		sketch.fill(255);
		sketch.ellipse(x, y, 30, 30);

		sketch.fill(0);
		sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
		if (BinTreeP.getItem().getZahl() != -1) {
			sketch.text("" + BinTreeP.getItem().getZahl(), x, y);
		} else {
			sketch.text(BinTreeP.getItem().getText(), x, y);
		}

		if (BinTreeP.hasLeft() && BinTreeP.getLeft() != null && BinTreeP.getLeft().hasItem()) {
			drawTree(BinTreeP.getLeft(), x - hSpacing, y + 50, hSpacing / 2, depth - 1);
		}

		if (BinTreeP.hasRight() && BinTreeP.getRight() != null && BinTreeP.getRight().hasItem()) {
			drawTree(BinTreeP.getRight(), x + hSpacing, y + 50, hSpacing / 2, depth - 1);
		}
	}

	/**
	 * Innere Klasse zur Repr�sentation eines Buttons.
	 */
	protected class Button {
		int x, y, w, h;
		String label;
		Runnable action;
		boolean clicked = false;

		/**
		 * Konstruktor f�r einen Button.
		 *
		 * @param x      Die x-Koordinate des Buttons.
		 * @param y      Die y-Koordinate des Buttons.
		 * @param w      Die Breite des Buttons.
		 * @param h      Die H�he des Buttons.
		 * @param label  Die Beschriftung des Buttons.
		 * @param action Die auszuf�hrende Aktion beim Klicken.
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
		 * �berpr�ft, ob die Maus �ber dem Button ist.
		 *
		 * @return {@code true}, wenn die Maus �ber dem Button ist, sonst {@code false}.
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
				action.run(); // F�hrt die zugewiesene Methode aus
			} else if (!sketch.mousePressed) {
				clicked = false; // Reset nach dem Klick
			}
		}

	}
}
