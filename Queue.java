package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse Queue (Schlange):
 * <p>
 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse der Schlange
 * generische Datentypen. Das bedeutet, dass bei der Deklaration einer Schlange
 * die zu verwendende Inhaltsklasse mit angegeben werden muss. Die interne
 * Verwaltung des Inhalts der Schlange erfolgt ber eine interne Klasse Item.
 * <p>
 * Die Funktionalitt und die Bezeichnungen der Schlange entsprechen den
 * Vorgaben der Thematischen Schwerpunkte fr die schriftliche Abiturprfung
 * 2018 in Informatik in Niedersachsen. Das bedeutet auch, dass in der
 * Implementierung keine "Absicherungen" enthalten sind, die z. B. das Entnehmen
 * bei einer leeren Schlange verhindern.
 * <p>
 * Alternativ liee sich auch eine spezielle Klasse Schlange implementieren, die
 * die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet. Dazu
 * msste aber fr jede Aufgabe ggf. eine neue Klasse Schlange erzeugt werden.
 * Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object, so ist
 * diese zwar universell nutzbar, man bentigt beim Zugriff auf die Schlange
 * dann aber hufig Typecasting.
 * <p>
 *
 * @param <T> Der Datentyp der in der Schlange gespeicherten Elemente.
 * 
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (berarbeitungen und Verbesserungen)
 * @author ChatGPT (berarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class Queue<T> {
	/**
	 * Zur Verwaltung des ersten Elements der Schlange.
	 */
	Item head;
	/**
	 * Zur Verwaltung des letzten Elements der Schlange.
	 */
	private Item back;

	private int size;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private GUI gui = new GUI(this);

	/**
	 * Eine leere Schlange wird angelegt.
	 */
	public Queue() {
		head = null;
		back = null;
		size = 0;
	}

	/**
	 * berprft, ob die Schlange leer ist.
	 *
	 * @return {@code true}, wenn die Schlange leer ist, {@code false} sonst.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Gibt den Inhalt des ersten Elements der Schlange zurck, ohne es zu
	 * entfernen.
	 *
	 * @return Der Inhalt des ersten Elements.
	 */
	public T head() {
		return head.data;
	}

	/**
	 * Gibt den Inhalt des ersten Elements der Schlange zurck und entfernt es.
	 *
	 * @return Der Inhalt des entfernten Elements.
	 */
	public T dequeue() {
		T temp = head.data;
		head = head.next;
		size--;
		return temp;
	}

	/**
	 * Fgt ein neues Element mit dem bergebenen Inhalt am Ende der Schlange hinzu.
	 *
	 * @param d Der Inhalt des neuen Elements.
	 */
	public void enqueue(T d) {
		Item i = new Item(d);
		if (!isEmpty()) {
			back.next = i;
		} else {
			head = i;
		}
		size++;
		back = i;
	}

	/**
	 * Gibt die Lnge der Schlange zurck. Wird fr Visualisierung in Processing
	 * bentigt.
	 *
	 * @return Die Lnge der Schlange.
	 */
	int size() {
		return size;
	}

	/**
	 * Visualisierung einer Schlange in Processing.
	 *
	 * @param sketch Das PApplet-Objekt fr die Darstellung.
	 */
	public void drawQueue(PApplet sketch) {
		gui.drawQueue(sketch);
	}

	/**
	 * Visualisierung einer Schlange in Processing an einer vorgegebenen Hhe.
	 *
	 * @param sketch Das PApplet-Objekt fr die Darstellung.
	 * @param y      Die vorgegebenen Hhe.
	 */
	public void drawQueue(PApplet sketch, float y) {
		gui.drawQueue(sketch, y);
	}

	/**
	 * Klasse Item zur internen Verwaltung der einzelnen Elemente der dynamischen
	 * Reihung.
	 */
	class Item {
		/**
		 * Der Inhalt des Elements.
		 */
		public T data;
		/**
		 * Verweis auf das nachfolgende Element.
		 */
		public Item next;

		/**
		 * Erzeugt ein neues Item mit dem gegebenen Inhalt.
		 *
		 * @param d Der Inhalt des Items.
		 */
		public Item(T d) {
			data = d;
			next = null;
		}
	}
}