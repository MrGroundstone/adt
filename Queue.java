package adt;

import processing.core.PApplet;

public class Queue<T> {
	/**
	 * Implementierung der Klasse Queue (Schlange):
	 * 
	 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse der Schlange
	 * generische Datentypen. Das bedeutet, dass bei der Deklaration einer Schlange
	 * die zu verwendende Inhaltsklasse mit angegeben werden muss. Die interne
	 * Verwaltung des Inhalts der Schlange erfolgt über eine interne Klasse Item.
	 * 
	 * Die Funktionalität und die Bezeichnungen der Schlange entsprechen den
	 * Vorgaben der Thematischen Schwerpunkte für die schriftliche Abiturprüfung
	 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
	 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
	 * bei einer leeren Schlange verhindern.
	 *
	 * Alternativ ließe sich auch eine spezielle Klasse Schlange implementieren, die
	 * die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet. Dazu
	 * müsste aber für jede Aufgabe ggf. eine neue Klasse Schlange erzeugt werden.
	 * Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object, so ist
	 * diese zwar universell nutzbar, man benötigt beim Zugriff auf die Schlange
	 * dann aber häufig Typecasting.
	 * 
	 * Update: Hendrik Bodenstein, 17.12.2024
	 */

	private Item head; // Zur Verwaltung des ersten Elements der Schlange
	private Item back; // Zur Verwaltung des letzten Elements der Schlange

	/* Eine leere Schlange wird angelegt. */
	public Queue() {
		head = null;
		back = null;
	}

	/*
	 * Wenn die Schlange kein Element besitzt, wird der Wert wahr zurückgegeben,
	 * sonst der Wert falsch.
	 */
	public boolean isEmpty() {

		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Der Inhalt des ersten Element der Schlange wird zurückgegeben, das Element
	 * aber nicht aus der Schlange entfernt.
	 */
	public T head() {
		return head.data;
	}

	/*
	 * Der Inhalt des ersten Elements der Schlange wird zurückgegeben und das
	 * Element dabei aus der Schlange entfernt.
	 */
	public T dequeue() {
		T temp = head.data;
		head = head.next;
		return temp;
	}

	/*
	 * Ein neues Element mit dem übergebenen Inhalt wird am Ende der Schlange
	 * angehängt.
	 */
	public void enqueue(T d) {
		Item i = new Item(d);
		if (!isEmpty()) {
			back.next = i;
		} else {
			head = i;
		}
		back = i;
	}

	/*
	 * Visualisierung einer Schlange in Processing
	 */
	public void drawQueue(PApplet sketch) {
		drawQueue(sketch, sketch.height / 2);
	}

	/*
	 * Visualisierung einer Schlange in Processing mit angegebenen y-Wert
	 */

	@SuppressWarnings({ "static-access" })
	public void drawQueue(PApplet sketch, float y) {
		if (!isEmpty()) {
			int groesse = 0;

			// Länge der Schlange bestimmen
			Item aktuell = head;
			while (aktuell != null) {
				groesse++;
				aktuell = aktuell.next;
			}

			// Zeichnen der Schlange
			float textSizeFactor = 0.4f;
			float itemWidth = (sketch.width - sketch.width / 50) / groesse;
			float offsetX = sketch.width / 100;
			sketch.textSize(itemWidth * textSizeFactor);
			aktuell = head.next;

			for (int i = 1; i < groesse; i++) {
				sketch.strokeWeight(1);
				sketch.fill(255); // Weiße Füllung für die Elemente
				sketch.stroke(0); // Schwarzer Rahmen

				// Rechteck für das aktuelle Element
				sketch.rect(i * itemWidth + offsetX, y - itemWidth / 2, itemWidth, itemWidth);

				// Text in der Mitte des Rechtecks
				sketch.fill(0); // Schwarzer Text
				sketch.textAlign(sketch.CENTER, sketch.CENTER); // Zentrierung
				sketch.text("" + aktuell.data, i * itemWidth + offsetX + itemWidth / 2, y);

				aktuell = aktuell.next;
			}

			// Zeichnen des head
			sketch.strokeWeight(3);
			sketch.fill(255);
			sketch.stroke(255, 255, 0); // Gelber Rahmen für head
			sketch.rect(offsetX, y - itemWidth / 2, itemWidth, itemWidth);
			
			// Text in der Mitte des Rechtecks
			sketch.fill(0); // Schwarzer Text
			sketch.textAlign(sketch.CENTER, sketch.CENTER); // Zentrierung
			sketch.text("" + head.data, offsetX + itemWidth / 2, y);

		} else {
			System.out.println("Die Queue ist leer!");
		}

	}

	/*
	 * Klasse Item zur internen Verwaltung der einzelnen Elemente der dynamischen
	 * Reihung.
	 */
	private class Item {
		public T data;
		public Item next; // Verweis auf das nachfolgende Element

		public Item(T d) {
			data = d;
			next = null;
		}
	}
}