# README

Dieses Projekt implementiert vier grundlegende Datenstrukturen: einen Binärbaum, ein dynamisches Array einen Stack und eine Queue. Jede dieser Strukturen ist in einer separaten Klasse implementiert und bietet spezifische Methoden für die Manipulation und Visualisierung der jeweiligen Datenstruktur. Die Implementierungen orientieren sich an den Vorgaben für die Informatik-Abiturprüfung in Niedersachsen und sind für Bildungszwecke optimiert.
Zusätzlich sind in allen vier Datenstrukturen Möglichkeiten für die Visualisierung in Processing eingefügt.

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
 
### 5. BinTreeGUI

**Datei:** `BinTreeGUI.java`

- **Beschreibung:** Implementiert eine grafische Benutzeroberfläche (GUI) für die Interaktion mit einem Binärbaum. Es verwendet Processing für die grafische Darstellung und Swing für Dialogfenster zur Benutzereingabe.
- **Wichtige Funktionen:**
  - `Hinzufügen von Knoten:` Über einen Dialog kann der Benutzer den Inhalt eines neuen Knotens eingeben, der dann dem Baum hinzugefügt wird.
  - `Zurücksetzen des Baums:` Der Baum kann per Knopfdruck zurückgesetzt werden.
  - `Visuelle Darstellung:` Der Binärbaum wird mit Linien und Ellipsen gezeichnet, wobei der Inhalt der Knoten in den Ellipsen angezeigt wird.
  - `Interaktive Buttons:` "Add Node" und "Reset Tree" Buttons ermöglichen die Interaktion mit dem Baum.

## Abhängigkeiten
- **Processing Library:** Alle Klassen nutzen Processing zur Visualisierung der Datenstrukturen.
- **Swing:** Wird für Dialogfenster in der Klasse BinTreeGUI benötigt.

## Autoren
unbekannt
Hendrik Bodenstein, 2025

## Lizenz
Dieses Projekt ist unter einer offenen Lizenz für Bildungszwecke freigegeben. Änderungen und Weiterverwendung sind gestattet, solange die ursprüngliche Quelle genannt wird.
