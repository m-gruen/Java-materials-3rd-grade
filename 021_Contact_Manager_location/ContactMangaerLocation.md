# Contact Manager with Location

## Ergänzen Sie Ihren Kontakt-Manager um folgende Funktionalität:

- In der Datenbank soll eine weitere Tabelle zur Speicherung von Orten mit PLZ und Ortsname eingefügt werden.
- Bei Anlegen/Laden der Datenbank sollen in Orts-Tabelle Datensätze eingetragen werden (4020 Linz, 4060 Leonding, 4050 Traun, etc.)
- Bezüglich der Adresse in einem Kontakt soll eine Referenz auf die Ortstabelle hergestellt werden.
- Die Zuordnung eines Kontakt auf einen Ort soll in der GUI mittels ComboBox realisiert werden.


## Mögliche Vorgehensweise:

- Anpassen der Database-Klasse
    - Erzeugen einer Location-Tabelle
    - Einfügen von initialen Datensätzen
    - Anpassen der Contact-Tabelle (Referenz auf Location)
- Erzeugen einer Location-Model-Klasse: Abbildung der Eigenschaften eines Orts
- Erzeugen einer LocationRepository-Klasse zum Zugriff auf die Location-Datenobjekte der Datenbank
    - getAllLocations()
    - findById(id)
- Anpassen der Contact-Klasse
    - Verwaltung einer zusätzlichen Location-Eigenschaft
- Anpassung der ContactRepository-Klasse
- Anpassung der ContactView-Klasse
- Anpassung der ContactPresenter-Klasse
