package adt;

import processing.core.PApplet;

public class DynArray<Variablentyp> {
	/**
	 * Implementierung der Klasse DynArray (Dynamische Reihung):
	 * 
	 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse der dynamischen
	 * Reihung generische Datentypen. Das bedeutet, dass bei der Deklaration einer
	 * dynamischen Reihung die zu verwendende Inhaltsklasse mit angegeben werden
	 * muss. Die interne Verwaltung des Inhalts der dynamischen Reihung erfolgt über
	 * eine interne Klasse Element.
	 * 
	 * Die Funktionalität und die Bezeichnungen der dynamischen Reihung entsprechen
	 * den Vorgaben der Thematischen Schwerpunkte für die schriftliche Abiturprüfung
	 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
	 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
	 * bei einer leeren dynamischen Reihung verhindern.
	 *
	 * Alternativ ließe sich auch eine spezielle Klasse Dynamische Reihung
	 * implementieren, die die konkrete Inhaltsklasse der bearbeiteten
	 * Aufgabenstellung verwendet. Dazu müsste aber für jede Aufgabe ggf. eine neue
	 * Klasse Dynamische Reihung erzeugt werden. Nutzt man als Inhaltsklasse die
	 * sehr allgemeine Java-Klasse Object, so ist diese zwar universell nutzbar, man
	 * benötigt beim Zugriff auf die dynamische Reihung dann aber häufig
	 * Typecasting.
	 * 
	 * Update: Hendrik Bodenstein, 16.12.2024
	 */

	// Anfang Attribute
	private int laenge;
	private Element kopf;
	// Ende Attribute

	public DynArray() {
		laenge = 0;
		kopf = null;
	}

	// Anfang Methoden
	public boolean isEmpty() {
		if (laenge == 0)
			return true;
		// else
		return false;
	}

	public Variablentyp getItem(int index) {
		if (index >= 0 && index < laenge) {
			Element aktuell = kopf;
			for (int i = 0; i < index; i++) {
				aktuell = aktuell.naechstes;
			}
			return aktuell.inhalt;
		}
		return null;
	}

	public int getLength() {
		return laenge;
	}

	public void append1(Variablentyp inhalt) {
		if (!isEmpty()) {
			getElement(laenge).naechstes = new Element(inhalt);
		} else {
			kopf = new Element(inhalt);
		}
		laenge++;
	}

	public void insertAt(int index, Variablentyp inhalt) {
		if (index > 0 && index < laenge) { // Es wird "mittig" eingefügt
			Element neu = new Element(inhalt);
			Element temp = getElement(index);
			getElement(index - 1).naechstes = neu;
			neu.naechstes = temp;
			laenge++;
		} else if (index == 0) { // Es wird vorne eingefügt
			Element temp = kopf;
			kopf = new Element(inhalt);
			kopf.naechstes = temp;
			laenge++;
		} else if (index == laenge) { // Es wird am Ende hinzugefügt
			append1(inhalt);
		}
	}

	public void setItem(int index, Variablentyp inhalt) {
		if (index > 0 && index <= laenge) {
			Element temp = getElement(index);
			temp.inhalt = inhalt;
		}
	}

	public void delete(int index) {
		if (index > 0 && index < laenge) { // Es wird "mittig" gelöscht
			getElement(index - 1).naechstes = getElement(index - 1).naechstes.naechstes;
			laenge--;
		} else if (index == laenge - 1) { // Es wird das letzte Element gelöscht
			getElement(index - 1).naechstes = null;
			laenge--;
		} else if (index == 0) { // Es wird das erste Element gelöscht
			kopf = kopf.naechstes;
			laenge--;
		}
	}

	// Interne Hilfsoperation zum vereinfachten Zugriff auf die Elemente der Reihung
	// durch die öffentlichen Operationen
	private Element getElement(int index) {
		if (laenge >= index) {
			Element aktuell = kopf;
			for (int i = 1; i < index; i++) {
				aktuell = aktuell.naechstes;
			}
			return aktuell;
		}
		return null;
	}

	/*
	 * Visualisierung eines DynArray in Processing
	 */
	public void drawDynArray(PApplet sketch) {
		drawDynArray(sketch, sketch.height/2);
	}

	/*
	 * Visualisierung eines DynArray in Processing
	 */
	@SuppressWarnings({ "static-access" })
	public void drawDynArray(PApplet sketch, float y) {
		if (laenge != 0) {

			float textSizeFactor = 0.4f;
			float itemWidth = (sketch.width - sketch.width / 50) / laenge;
			sketch.textSize(itemWidth * textSizeFactor);
			for (int i = 0; i < laenge; i++) {
				sketch.fill(255);
				sketch.rect(i * itemWidth + sketch.width / 100, y - itemWidth / 2, itemWidth, itemWidth);
				// Text in der Mitte der Zelle
				sketch.fill(0);
				sketch.textAlign(sketch.CENTER, sketch.CENTER); // Text zentrieren
				sketch.text(getItem(i).toString(), i * itemWidth + sketch.width / 100 + itemWidth / 2, y);
			}
		} else {
			System.out.println("Das DynArray ist leer!");
		}
	}

	// Ende Methoden

	// Klasse Element zur internen Verwaltung der einzelnen Elemente der
	// dynamischen Reihung
	private class Element {
		public Variablentyp inhalt;
		public Element naechstes;

		public Element(Variablentyp inh) {
			inhalt = inh;
			naechstes = null;
		}
	} // Ende der Klasse Element
}