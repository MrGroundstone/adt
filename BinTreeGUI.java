package adt;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import processing.core.PApplet;

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
			() -> handleResetButtonClick());;

	boolean typing = false;
	float translateX = 0, translateY = 0, scaleFactor = (float) 1.0;

	// Konstruktor
	public BinTreeGUI(BinTreeP binTree) {
		this.binTree = binTree;
		
	}

	public void drawBinTree(PApplet p) {
		this.sketch = p;
		
		sketch.translate(translateX, translateY);
		sketch.scale(scaleFactor);
	    
		drawTree(this.binTree, (float) sketch.width / 2, (float) 50, sketch.width / 4, binTree.getTiefe() - 1);

		drawButtons();
	}

	private void drawButtons() {
		// Buttons zeichnen
		addButton.drawButton();
		resetButton.drawButton();
		// Überprüfe, ob einer der Buttons geklickt wurde

		addButton.handleClick(); // Prüft und führt die Aktion aus
		resetButton.handleClick(); // Prüft und führt die Aktion aus

	}

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

	private void handleResetButtonClick() {
		try {
			binTree.reset();
		} catch (Exception e) {
			showMessageDialog(null, "Fehler beim Zurücksetzen des Baums!", "Fehler", ERROR_MESSAGE);
		}
	}

	private void drawTree(BinTreeP BinTreeP, float x, float y, int hSpacing, int depth) {
		// Abbruchbedingungen: Leerer Baum oder keine weitere Tiefe
		if (BinTreeP == null || !BinTreeP.hasItem() || depth < 0) {
			sketch.background(200);
			return;
		}

		// Zeichne die Verbindungslinien zu den Kindern (Linien zuerst)
		if (BinTreeP.hasLeft() && BinTreeP.getLeft() != null && BinTreeP.getLeft().hasItem()) {
			sketch.line(x, y, x - hSpacing, y + 50); // Linie zum linken Kind
		}
		if (BinTreeP.hasRight() && BinTreeP.getRight() != null && BinTreeP.getRight().hasItem()) {
			sketch.line(x, y, x + hSpacing, y + 50); // Linie zum rechten Kind
		}

		// Zeichne den aktuellen Knoten (Kreis)
		sketch.fill(255); // Weißer Hintergrund für den Knoten
		sketch.ellipse(x, y, 30, 30); // Kreis mit Durchmesser 30

		// Zeichne den Text im Knoten
		sketch.fill(0); // Schwarzer Text
		sketch.textAlign(PApplet.CENTER, PApplet.CENTER); // Zentrierter Text
		if (BinTreeP.getItem().getZahl() != -1) {
			sketch.text("" + BinTreeP.getItem().getZahl(), x, y); // Zahl anzeigen
		} else {
			sketch.text(BinTreeP.getItem().getText(), x, y); // Text anzeigen
		}

		// Rekursive Zeichnung des linken Teilbaums
		if (BinTreeP.hasLeft() && BinTreeP.getLeft() != null && BinTreeP.getLeft().hasItem()) {
			drawTree(BinTreeP.getLeft(), x - hSpacing, y + 50, hSpacing / 2, depth - 1);
		}

		// Rekursive Zeichnung des rechten Teilbaums
		if (BinTreeP.hasRight() && BinTreeP.getRight() != null && BinTreeP.getRight().hasItem()) {
			drawTree(BinTreeP.getRight(), x + hSpacing, y + 50, hSpacing / 2, depth - 1);
		}
	}

	protected class Button {
        int x, y, w, h;
        String label;
        Runnable action;
        boolean clicked = false;

        Button(int x, int y, int w, int h, String label, Runnable action) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.label = label;
            this.action = action;
        }

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

        boolean isHovered() {
            return sketch.mouseX > x && sketch.mouseX < x + w && sketch.mouseY > y && sketch.mouseY < y + h;
        }

        void handleClick() {
            // Verhindern, dass der Button mehr als einmal pro Klick verarbeitet wird
            if (isHovered() && sketch.mousePressed && !clicked && sketch.focused) {
                clicked = true;
                action.run(); // Führt die zugewiesene Methode aus
            } else if (!sketch.mousePressed) {
                clicked = false; // Reset nach dem Klick
            }
        }
    }
}
