# README

Dieses Projekt implementiert vier grundlegende Datenstrukturen: einen Binärbaum, ein dynamisches Array einen Stack und eine Queue. Jede dieser Strukturen ist in einer separaten Klasse implementiert und bietet spezifische Methoden für die Manipulation und Visualisierung der jeweiligen Datenstruktur. Die Implementierungen orientieren sich an den Vorgaben für die Informatik-Abiturprüfung in Niedersachsen und sind für Bildungszwecke optimiert.

## Klassenübersicht

### 1. BinTree (Binärbaum)
**Datei:** `BinTree.java`

- **Beschreibung:** Implementiert einen Binärbaum, der sowohl Zeichenketten als auch Ganzzahlen als Inhalte speichern kann.
- **Wichtige Methoden:**
  - `beispielBaumErstellen()`: Erstellt einen Beispiel-Binärbaum.
  - `beispielSuchbaumErstellen()`: Erstellt einen Beispiel-Suchbaum.
  - `hasItem()`, `getItem()`, `setItem(Inhalt inhalt)`: Verwalten des Wurzelinhalts.
  - `hasLeft()`, `getLeft()`, `setLeft(BinTree b)`: Zugriff auf den linken Teilbaum.
  - `hasRight()`, `getRight()`, `setRight(BinTree b)`: Zugriff auf den rechten Teilbaum.
  - `drawBinTree(PApplet sketch)`: Visualisierung des Baumes.

### 2. DynArray (Dynamisches Array)
**Datei:** `DynArray.java`

- **Beschreibung:** Eine generische Implementierung eines dynamischen Arrays, das die Größe bei Bedarf anpasst.
- **Wichtige Methoden:**
  - `isEmpty()`: Prüft, ob das Array leer ist.
  - `getItem(int index)`: Gibt das Element an einer bestimmten Position zurück.
  - `append1(T inhalt)`: Fügt ein Element am Ende hinzu.
  - `insertAt(int index, T inhalt)`: Fügt ein Element an einer bestimmten Position ein.
  - `delete(int index)`: Entfernt ein Element an einer bestimmten Position.
  - `drawDynArray(PApplet sketch)`: Visualisierung des Arrays.

### 3. Stack (Stapel)
**Datei:** `Stack.java`

- **Beschreibung:** Implementiert eine generische Stack-Datenstruktur (LIFO - Last In, First Out).
- **Wichtige Methoden:**
  - `isEmpty()`: Prüft, ob der Stack leer ist.
  - `push1(T d)`: Fügt ein Element oben auf dem Stack hinzu.
  - `pop1()`: Entfernt das oberste Element des Stacks und gibt dessen Inhalt zurück.
  - `top()`: Gibt den Inhalt des obersten Elements zurück, ohne es zu entfernen.
  - `drawStack(PApplet sketch)`: Visualisierung der Queue.

### 4. Queue (Schlange)
**Datei:** `Queue.java`

- **Beschreibung:** Implementiert eine generische Queue-Datenstruktur (FIFO - First In, First Out).
- **Wichtige Methoden:**
  - `isEmpty()`: Prüft, ob die Queue leer ist.
  - `enqueue(T d)`: Fügt ein Element am Ende der Queue hinzu.
  - `dequeue()`: Entfernt das erste Element der Queue und gibt dessen Inhalt zurück.
  - `head()`: Gibt den Inhalt des ersten Elements zurück, ohne es zu entfernen.
  - `drawQueue(PApplet sketch)`: Visualisierung der Queue.

## Abhängigkeiten
- **Processing Library:** Alle Klassen nutzen Processing zur Visualisierung der Datenstrukturen. Stellen Sie sicher, dass die Processing-Bibliothek in Ihrem Projekt eingebunden ist.

## Verwendung
1. **Kompilieren:** Stellen Sie sicher, dass alle Java-Dateien im gleichen Verzeichnis liegen und korrekt kompiliert werden.
2. **Ausführen:** Die Klassen können eigenständig oder als Teil eines größeren Projekts verwendet werden.
3. **Visualisierung:** Die Visualisierungsfunktionen (`drawBinTree`, `drawDynArray`, `drawStack`, `drawQueue`) erfordern ein `PApplet`-Objekt und können in einem Processing-Sketch aufgerufen werden.

## Autoren
unbekannt
Hendrik Bodenstein, 2024

## Lizenz
Dieses Projekt ist unter einer offenen Lizenz für Bildungszwecke freigegeben. Änderungen und Weiterverwendung sind gestattet, solange die ursprüngliche Quelle genannt wird.
