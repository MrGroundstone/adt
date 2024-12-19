package adt;

import processing.core.PApplet;

public class Stack<T> {
	/**
	 * Implementierung der Klasse Stack (Stapel):
	 * 
	 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse des Stapels
	 * generische Datentypen. Das bedeutet, dass bei der Deklaration eines Stapel
	 * die zu verwendende Inhaltsklasse mit angegeben werden muss. Die interne
	 * Verwaltung des Inhalts des Stapels erfolgt �ber eine interne Klasse Item.
	 * 
	 * Die Funktionalit�t und die Bezeichnungen des Stapels entsprechen den Vorgaben
	 * der Thematischen Schwerpunkte f�r die schriftliche Abiturpr�fung 2018 in
	 * Informatik in Niedersachsen. Das bedeutet auch, dass in der Implementierung
	 * keine "Absicherungen" enthalten sind, die z. B. das Entnehmen bei einem
	 * leeren Stapel verhindern.
	 *
	 * Alternativ lie�e sich auch eine spezielle Klasse Stapel implementieren, die
	 * die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet. Dazu
	 * m�sste aber f�r jede Aufgabe ggf. eine neue Klasse Stapel erzeugt werden.
	 * Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object, so ist
	 * diese zwar universell nutzbar, man ben�tigt beim Zugriff auf den Stapel dann
	 * aber h�ufig Typecasting.
	 * 
	 * Update: Hendrik Bodenstein, 16.12.2024
	 */

	private Item top; // Zur Verwaltung des obersten Elements des Stapels

	/*
	 * Ein leerer Stapel wird angelegt.
	 */
	public Stack() {
		top = null;
	}

	/*
	 * Wenn der Stapel kein Element besitzt, wird der Wert wahr zur�ckgegeben, sonst
	 * der Wert falsch.
	 */
	public boolean isEmpty() {

		if (top == null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Der Inhalt des obersten Element des Stapels wird zur�ckgegeben, das Element
	 * aber nicht aus dem Stapel entfernt.
	 */
	public T top() {
		return top.data;
	}

	/*
	 * Der Inhalt des obersten Elements des Stapels wird zur�ckgegeben und das
	 * Element dabei vom Stapel entfernt.
	 */
	public T pop1() {
		T temp = top.data;
		top = top.below;
		return temp;
	}

	/*
	 * Ein neues Element mit dem �bergebenen Inhalt wird auf dem Stapel abgelegt.
	 */
	public void push1(T d) {
		Item i = new Item(d);
		i.below = top;
		top = i;
	}

	/*
	 * Zeichnet den Stack, f�r den die Operation aufgerufen wurde, in der Mitte des Fensters.
	 */
	public void drawStack(PApplet sketch) {
		drawStack(sketch, sketch.width/2);
	}

	/*
	 * Zeichnet den Stack, f�r den die Operation aufgerufen wurde, beginnend bei der �bergebenen x-Koordinate.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void drawStack(PApplet sketch, float x) {
		if (!isEmpty()) {
			DynArray kopie = new DynArray();
			int groesse = 0;
			float largestItemsize = 0;

			// L�nge des Stapels bestimmen
			while (!isEmpty()) {
				kopie.append1(pop1());
				groesse += 1;
			}

			// Rechteckh�he berechnen
			float textSizeFactor = (float) 0.4;
			float itemHeight = (sketch.height - sketch.height / 50) / groesse; // 10 Pixel Gesamtfreiraum
			sketch.textSize(itemHeight * textSizeFactor);

			// Vertikale Verschiebung f�r mittige Darstellung (inkl. 5 Pixel Freiraum oben)
			float offsetY = sketch.height / 100;

			// Längstes Item des Stacks finden
			largestItemsize = kopie.getItem(0).toString().length();
			for (int i = 1; i < groesse ; i++) {
				int current = kopie.getItem(i).toString().length();
				if (largestItemsize < current) {
					largestItemsize = current;
				}
			}

			// Breite des Stacks passend zur Größe des längsten Elements
			// font pt zu px: px = pt * ( 72pt / 96 )
			float stackWidth = (float) ((itemHeight * textSizeFactor * 0.75)* largestItemsize); // Der Stapel hat eine Breite, die der Rechteckh�he entspricht

			// Rechtecke im Stapel zeichnen
			sketch.strokeWeight(1); // Zur�cksetzen der Linienst�rke f�r Rechtecke
			for (int i = 0; i < groesse; i++) {
				// Oberstes Element betrachten
				T element = (T) kopie.getItem(i);

				// Rechteck zeichnen
				sketch.fill(255); // Wei�
				sketch.rect(x-stackWidth/2, offsetY + i * itemHeight, stackWidth, itemHeight);

				// Text in der Mitte des Rechtecks
				sketch.fill(0); // Schwarz
				sketch.textAlign(sketch.CENTER, sketch.CENTER); // Text zentrieren
				sketch.text("" + element, x, offsetY + i * itemHeight + itemHeight / 2);

			}
			// Rahmen um den Stack (nur unten, links und rechts) mit Freiraum
			sketch.stroke(0); // Schwarz
			sketch.strokeWeight(4); // Dicke der Umrandung
			sketch.noFill(); // Keine F�llung
			// Linke Linie
			sketch.line(x-stackWidth/2, offsetY, // Freiraum oben
					x-stackWidth/2, offsetY + groesse * itemHeight // Freiraum unten
			);
			// Rechte Linie
			sketch.line(x + stackWidth/2, offsetY, // Freiraum oben
					x + stackWidth/2, offsetY + groesse * itemHeight // Freiraum unten
			);
			// Untere Linie
			sketch.line(x-stackWidth/2, offsetY + groesse * itemHeight, // Freiraum unten
					x + stackWidth/2, offsetY + groesse * itemHeight // Freiraum unten
			);

			// Stapel wiederherstellen
			for (int i = kopie.getLength() - 1; i >= 0; i--) {
				push1((T) kopie.getItem(i));
			}

		} else {
			System.out.println("Der Stack ist leer!");
		}
	}

	/*
	 * Klasse Item zur internen Verwaltung der einzelnen Elemente des Stacks
	 */
	private class Item {
		public T data;
		public Item below; // Verweis auf das nachfolgende "darunter liegende" Element

		public Item(T d) {
			data = d;
			below = null;
		}
	}
}
