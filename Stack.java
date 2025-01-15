package adt;

import processing.core.PApplet;

/**
 * Implementierung der Klasse Stack (Stapel):
 * <p>
 * Die Implementierung nutzt zur Verwaltung der Inhaltsklasse des Stapels
 * generische Datentypen. Das bedeutet, dass bei der Deklaration eines Stapel
 * die zu verwendende Inhaltsklasse mit angegeben werden muss. Die interne
 * Verwaltung des Inhalts des Stapels erfolgt über eine interne Klasse Item.
 * <p>
 * Die Funktionalität und die Bezeichnungen des Stapels entsprechen den Vorgaben
 * der Thematischen Schwerpunkte für die schriftliche Abiturprüfung 2018 in
 * Informatik in Niedersachsen. Das bedeutet auch, dass in der Implementierung
 * keine "Absicherungen" enthalten sind, die z. B. das Entnehmen bei einem
 * leeren Stapel verhindern.
 * <p>
 * Alternativ ließe sich auch eine spezielle Klasse Stapel implementieren, die
 * die konkrete Inhaltsklasse der bearbeiteten Aufgabenstellung verwendet. Dazu
 * müsste aber für jede Aufgabe ggf. eine neue Klasse Stapel erzeugt werden.
 * Nutzt man als Inhaltsklasse die sehr allgemeine Java-Klasse Object, so ist
 * diese zwar universell nutzbar, man benötigt beim Zugriff auf den Stapel dann
 * aber häufig Typecasting.
 * <p>
 * 
 * @param <T> Der Typ der im Stack gespeicherten Elemente.
 * 
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (Überarbeitungen und Verbesserungen)
 * @author ChatGPT (Überarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class Stack<T> {

	/**
	 * Zur Verwaltung des obersten Elements des Stapels.
	 */
	private Item top;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private GUI gui = new GUI(this);

	/**
	 * Ein leerer Stapel wird angelegt.
	 */
	public Stack() {
		top = null;
	}

	/**
	 * Wenn der Stapel kein Element besitzt, wird der Wert wahr zurückgegeben, sonst
	 * der Wert falsch.
	 * 
	 * @return true wenn der Stapel leer ist, false sonst.
	 */
	public boolean isEmpty() {

		if (top == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Der Inhalt des obersten Element des Stapels wird zurückgegeben, das Element
	 * aber nicht aus dem Stapel entfernt.
	 * 
	 * @return Der Inhalt des obersten Elements.
	 */
	public T top() {
		return top.data;
	}

	/**
	 * Der Inhalt des obersten Elements des Stapels wird zurückgegeben und das
	 * Element dabei vom Stapel entfernt.
	 * 
	 * @return Der Inhalt des entfernten Elements.
	 */
	public T pop1() {
		T temp = top.data;
		top = top.below;
		return temp;
	}

	/**
	 * Ein neues Element mit dem übergebenen Inhalt wird auf dem Stapel abgelegt.
	 * 
	 * @param d Der Inhalt des neuen Elements.
	 */
	public void push1(T d) {
		Item i = new Item(d);
		i.below = top;
		top = i;
	}

	/**
	 * Visualisierung eines Stacks in Processing.
	 * 
	 * @param sketch Das PApplet-Objekt für die Visualisierung.
	 */
	public void drawStack(PApplet sketch) {
		gui.drawStack(sketch);
	}

	/**
	 * Visualisierung eines Tacks in Processing an einer vorgegebenen Stelle.
	 *
	 * @param sketch Das PApplet-Objekt für die Darstellung.
	 * @param x      Die vorgegebene Stelle.
	 */
	public void drawStack(PApplet sketch, float x) {
		gui.drawStack(sketch, x);
	}

	/**
	 * Klasse Item zur internen Verwaltung der einzelnen Elemente des Stacks
	 */
	private class Item {
		/**
		 * Die im Item gespeicherten Daten.
		 */
		public T data;
		/**
		 * Verweis auf das nachfolgende "darunter liegende" Element.
		 */
		public Item below;

		/**
		 * Konstruktor für ein neues Item.
		 * 
		 * @param d Die im Item zu speichernden Daten.
		 */
		public Item(T d) {
			data = d;
			below = null;
		}
	}
}