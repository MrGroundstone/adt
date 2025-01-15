package adt;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

/**
 * Die Klasse {@code GUI} implementiert eine grafische Benutzeroberfläche (GUI)
 * für die Darstellung von {@link Stack}, {@link Queue} und {@link DynArray}.
 * Sie verwendet Processing für die grafische Darstellung.
 *
 * @param <T> Der Typ der Elemente, die in den Datenstrukturen gespeichert
 *            werden.
 * 
 * @author Hendrik Bodenstein (basierend auf Originalcode)
 * @author Gemini (Überarbeitungen und Verbesserungen)
 * @author ChatGPT (Überarbeitungen und Verbesserungen)
 * @version 1.1
 */
public class GUI<T> extends PApplet {

	private static final float MARGIN_FACTOR = 0.01f; // 1% Rand
	private static final float TEXT_SIZE_FACTOR = 0.4f;
	private static final float STACK_WIDTH_FACTOR = 0.75f;
	private static final int BORDER_WEIGHT = 4;
	private static final int HEAD_BORDER_WEIGHT = 3;

	private Stack<T> stack;
	private Queue<T> queue;
	private DynArray<T> dynArray;

	/**
	 * Konstruktor für die GUI mit einem Stack.
	 *
	 * @param stack Der zu visualisierende Stack.
	 */
	public GUI(Stack<T> stack) {
		this.stack = stack;
	}

	/**
	 * Konstruktor für die GUI mit einer Queue.
	 *
	 * @param queue Die zu visualisierende Queue.
	 */
	public GUI(Queue<T> queue) {
		this.queue = queue;
	}

	/**
	 * Konstruktor für die GUI mit einem DynArray.
	 *
	 * @param dynArray Das zu visualisierende DynArray.
	 */
	public GUI(DynArray<T> dynArray) {
		this.dynArray = dynArray;
	}

	/**
	 * Hilfsmethode, um die Elemente eines Stacks in einer Liste zurückzugeben, ohne
	 * den Stack zu verändern.
	 *
	 * @param stack Der Stack, dessen Elemente abgerufen werden sollen.
	 * @return Eine Liste mit den Elementen des Stacks.
	 */
	private List<T> getStackElements(Stack<T> stack) {
		List<T> elements = new ArrayList<>();
		Stack<T> tempStack = new Stack<>(); // Temporärer Stack
		while (!stack.isEmpty()) {
			T element = stack.pop1();
			elements.add(element);
			tempStack.push1(element); // Elemente im temporären Stack zwischenspeichern
		}
		while (!tempStack.isEmpty()) {
			stack.push1(tempStack.pop1()); // Ursprünglichen Stack wiederherstellen
		}
		return elements;
	}

	/**
	 * Zeichnet den Stack auf dem übergebenen PApplet.
	 *
	 * @param p Der PApplet, auf dem gezeichnet wird.
	 * @param x Die x-Koordinate des Stacks.
	 */
	@SuppressWarnings("static-access")
	public void drawStack(PApplet p, float x) {
		if (stack == null)
			return; // Überprüfung auf Null
		if (stack.isEmpty()) {
			p.text("Stack ist leer", x, p.height / 2);
			return; // Frühes Verlassen der Methode
		}

		List<T> elements = getStackElements(stack);
		int groesse = elements.size();

		float itemHeight = (p.height * (1 - 2 * MARGIN_FACTOR)) / groesse;
		p.textSize(itemHeight * TEXT_SIZE_FACTOR);

		float largestItemsize = 0;
		for (T element : elements) {
			largestItemsize = Math.max(largestItemsize, element.toString().length());
		}

		float stackWidth = (itemHeight * TEXT_SIZE_FACTOR * STACK_WIDTH_FACTOR) * largestItemsize;
		float offsetY = p.height * MARGIN_FACTOR;

		p.strokeWeight(1);
		for (int i = 0; i < groesse; i++) {
			p.fill(255);
			p.rect(x - stackWidth / 2, offsetY + i * itemHeight, stackWidth, itemHeight);
			p.fill(0);
			p.textAlign(p.CENTER, p.CENTER);
			p.text(elements.get(i).toString(), x, offsetY + i * itemHeight + itemHeight / 2);
		}

		p.stroke(0);
		p.strokeWeight(BORDER_WEIGHT);
		p.noFill();
		p.line(x - stackWidth / 2, offsetY, x - stackWidth / 2, offsetY + groesse * itemHeight);
		p.line(x + stackWidth / 2, offsetY, x + stackWidth / 2, offsetY + groesse * itemHeight);
		p.line(x - stackWidth / 2, offsetY + groesse * itemHeight, x + stackWidth / 2, offsetY + groesse * itemHeight);
	}

	/**
	 * Zeichnet das DynArray auf dem übergebenen PApplet.
	 *
	 * @param p Der PApplet, auf dem gezeichnet wird.
	 * @param y Die y-Koordinate des DynArrays.
	 */
	@SuppressWarnings("static-access")
	public void drawDynArray(PApplet p, float y) {
		if (dynArray == null)
			return; // Überprüfung auf Null
		if (dynArray.getLaenge() == 0) {
			p.text("DynArray ist leer", p.width / 2, y);
			return;
		}

		float textSizeFactor = TEXT_SIZE_FACTOR;
		float itemWidth = (p.width * (1 - 2 * MARGIN_FACTOR)) / dynArray.getLaenge();
		p.textSize(itemWidth * textSizeFactor);

		for (int i = 0; i < dynArray.getLaenge(); i++) {
			p.fill(255);
			p.rect(i * itemWidth + p.width * MARGIN_FACTOR, y - itemWidth / 2, itemWidth, itemWidth);
			p.fill(0);
			p.textAlign(p.CENTER, p.CENTER);
			p.text(dynArray.getItem(i).toString(), i * itemWidth + p.width * MARGIN_FACTOR + itemWidth / 2, y);
		}
	}

	/**
	 * Zeichnet die Queue auf dem übergebenen PApplet.
	 *
	 * @param p Der PApplet, auf dem gezeichnet wird.
	 * @param y Die y-Koordinate der Queue.
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public void drawQueue(PApplet p, float y) {
		if (queue == null)
			return; // Überprüfung auf Null
		if (queue.isEmpty()) {
			p.text("Queue ist leer", p.width / 2, y);
			return;
		}

		int groesse = queue.size(); // Annahme: Queue hat eine size() Methode
		float itemWidth = (p.width * (1 - 2 * MARGIN_FACTOR)) / groesse;
		float offsetX = p.width * MARGIN_FACTOR;
		p.textSize(itemWidth * TEXT_SIZE_FACTOR);

		Queue.Item aktuell = queue.head;

		for (int i = 0; i < groesse; i++) { // Korrekte Schleife
			p.strokeWeight(1);
			p.fill(255);
			p.stroke(0);

			if (i == 0) {
				p.strokeWeight(HEAD_BORDER_WEIGHT);
				p.stroke(255, 255, 0);
			}

			p.rect(i * itemWidth + offsetX, y - itemWidth / 2, itemWidth, itemWidth);

			p.fill(0);
			p.textAlign(p.CENTER, p.CENTER);
			p.text("" + aktuell.data, i * itemWidth + offsetX + itemWidth / 2, y);

			if (aktuell != null) {
				aktuell = aktuell.next;
			}
		}
	}

	public void drawQueue(PApplet sketch) {
		drawQueue(sketch, sketch.height / 2);

	}

	public void drawStack(PApplet sketch) {
		drawStack(sketch, sketch.width / 2);

	}
}