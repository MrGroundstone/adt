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
 * Update: Hendrik Bodenstein, 25.01.2025
 * @param <T> Der Typ der im Stack gespeicherten Elemente.
 */
public class Stack<T> {

    /**
     * Zur Verwaltung des obersten Elements des Stapels.
     */
    private Item top;

    /**
     * Ein leerer Stapel wird angelegt.
     */
    public Stack() {
        top = null;
    }

    /**
     * Wenn der Stapel kein Element besitzt, wird der Wert wahr zurückgegeben, sonst
     * der Wert falsch.
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
     * @return Der Inhalt des obersten Elements.
     */
    public T top() {
        return top.data;
    }

    /**
     * Der Inhalt des obersten Elements des Stapels wird zurückgegeben und das
     * Element dabei vom Stapel entfernt.
     * @return Der Inhalt des entfernten Elements.
     */
    public T pop1() {
        T temp = top.data;
        top = top.below;
        return temp;
    }

    /**
     * Ein neues Element mit dem übergebenen Inhalt wird auf dem Stapel abgelegt.
     * @param d Der Inhalt des neuen Elements.
     */
    public void push1(T d) {
        Item i = new Item(d);
        i.below = top;
        top = i;
    }

    /**
     * Zeichnet den Stack, für den die Operation aufgerufen wurde, in der Mitte des Fensters.
     * @param sketch Das PApplet Objekt zum Zeichnen.
     */
    public void drawStack(PApplet sketch) {
        drawStack(sketch, sketch.width / 2);
    }

    /**
     * Zeichnet den Stack, für den die Operation aufgerufen wurde, beginnend bei der übergebenen x-Koordinate.
     * @param sketch Das PApplet Objekt zum Zeichnen.
     * @param x Die x-Koordinate, an der der Stack gezeichnet werden soll.
     */
    @SuppressWarnings({"unchecked", "rawtypes", "static-access"})
    public void drawStack(PApplet sketch, float x) {
        if (!isEmpty()) {
            DynArray kopie = new DynArray();
            int groesse = 0;
            float largestItemsize = 0;

            // Länge des Stapels bestimmen
            while (!isEmpty()) {
                kopie.append1(pop1());
                groesse += 1;
            }

            // Rechteckhöhe berechnen
            float textSizeFactor = (float) 0.4;
            float itemHeight = (sketch.height - sketch.height / 50) / groesse; // 10 Pixel Gesamtfreiraum
            sketch.textSize(itemHeight * textSizeFactor);

            // Vertikale Verschiebung für mittige Darstellung (inkl. 5 Pixel Freiraum oben)
            float offsetY = sketch.height / 100;

            // Längstes Item des Stacks finden
            largestItemsize = kopie.getItem(0).toString().length();
            for (int i = 1; i < groesse; i++) {
                int current = kopie.getItem(i).toString().length();
                if (largestItemsize < current) {
                    largestItemsize = current;
                }
            }

            // Breite des Stacks passend zur Größe des längsten Elements
            // font pt zu px: px = pt * ( 72pt / 96 )
            float stackWidth = (float) ((itemHeight * textSizeFactor * 0.75) * largestItemsize); // Der Stapel hat eine Breite, die der Rechteckhöhe entspricht

            // Rechtecke im Stapel zeichnen
            sketch.strokeWeight(1); // Zurücksetzen der Linienstärke für Rechtecke
            for (int i = 0; i < groesse; i++) {
                // Oberstes Element betrachten
                T element = (T) kopie.getItem(i);

                // Rechteck zeichnen
                sketch.fill(255); // Weiß
                sketch.rect(x - stackWidth / 2, offsetY + i * itemHeight, stackWidth, itemHeight);

                // Text in der Mitte des Rechtecks
                sketch.fill(0); // Schwarz
                sketch.textAlign(sketch.CENTER, sketch.CENTER); // Text zentrieren
                sketch.text("" + element, x, offsetY + i * itemHeight + itemHeight / 2);

            }
            // Rahmen um den Stack (nur unten, links und rechts) mit Freiraum
            sketch.stroke(0); // Schwarz
            sketch.strokeWeight(4); // Dicke der Umrandung
            sketch.noFill(); // Keine Füllung
            // Linke Linie
            sketch.line(x - stackWidth / 2, offsetY, // Freiraum oben
                    x - stackWidth / 2, offsetY + groesse * itemHeight // Freiraum unten
            );
            // Rechte Linie
            sketch.line(x + stackWidth / 2, offsetY, // Freiraum oben
                    x + stackWidth / 2, offsetY + groesse * itemHeight // Freiraum unten
            );
            // Untere Linie
            sketch.line(x - stackWidth / 2, offsetY + groesse * itemHeight, // Freiraum unten
                    x + stackWidth / 2, offsetY + groesse * itemHeight // Freiraum unten
            );

            // Stapel wiederherstellen
            for (int i = kopie.getLength() - 1; i >= 0; i--) {
                push1((T) kopie.getItem(i));
            }

        } else {
            System.out.println("Der Stack ist leer!");
        }
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
         * @param d Die im Item zu speichernden Daten.
         */
        public Item(T d) {
            data = d;
            below = null;
        }
    }
}