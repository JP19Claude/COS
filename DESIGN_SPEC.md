# DESIGN_SPEC

Zentrale Quelle für alle gemessenen Gestaltungs- und Bewegungswerte. Der Code liest ausschließlich Tokens, die hier stehen.

> **Status (2026-10-23): Skelett.** `reference/` enthält noch kein Material. Deshalb ist jeder Wert `TODO: Referenz nötig`. Es wurde nichts geschätzt.
> Was gebraucht wird, steht in [`reference/README.md`](reference/README.md).

Spalte **Quelle** = Datei in `reference/` + Frame-Nummer oder Pixel-Koordinate, aus der der Wert stammt. Ein Wert ohne Quelle gilt als nicht gemessen.

---

## 0. Meta

| Feld | Wert | Quelle |
|---|---|---|
| Gerät (Modell) | TODO: Referenz nötig | |
| OS-Version (exakt, inkl. Build) | TODO: Referenz nötig | |
| Displayauflösung (px) | TODO: Referenz nötig | `adb shell wm size` |
| Displaydichte (dpi) | TODO: Referenz nötig | `adb shell wm density` |
| Bildwiederholrate Display | TODO: Referenz nötig | |
| Bildrate der Aufnahmen (fps) | TODO: Referenz nötig | `ffprobe` |
| Anzeigegröße / Schriftgröße | TODO: Referenz nötig | muss auf Standard stehen |
| Navigationsmodus | TODO: Referenz nötig | Gesten / 3 Tasten |

## Messmethode

- **px → dp:** `dp = px × 160 / dpi`. In dieser Datei stehen dp/sp. Der Roh-px-Wert steht in der Quellen-Spalte.
- **Farben:** Mittelwert über 5×5 px in einer flachen Fläche, nicht an Kanten und nicht in Blur-Bereichen mit Verlauf.
- **Transparente Flächen (Alpha + Tönung):** Dieselbe Fläche wird über bekanntem Schwarz und Weiß gemessen (Kalibrier-Wallpaper `reference/calibration/checker_360.png`). Aus `C = α·C_fläche + (1−α)·C_hintergrund` mit zwei Hintergründen folgen α und die Flächenfarbe.
- **Blur:** Kantenbreite 10 %→90 % über einer harten Schwarz-Weiß-Kante ≈ 2,56·σ. Umrechnung in den `RenderEffect`-Radius nach Skia-Konvention σ ≈ 0,57735·r + 0,5. Die Konvention wird beim ersten Gerätevergleich geprüft.
- **Animationen:** Frames mit `ffmpeg -fps_mode passthrough` extrahieren, Zeitstempel per `ffprobe -show_frames` lesen. Position/Skalierung/Alpha eines Elements pro Frame tracken und auf Fortschritt p(t) ∈ [0,1] normieren. Danach zwei Modelle fitten: (a) Cubic-Bezier mit festen Endpunkten, (b) gedämpfte Feder (Steifigkeit, Dämpfungsgrad). Das Modell mit kleinerem RMS-Fehler gewinnt, der Fehler wird notiert. Dauer = erster Frame mit Bewegung bis erster Frame in Ruhe. Die Genauigkeit liegt bei ±1 Frame (≈ 8–17 ms je nach fps).
- **Gesten:** Aufnahmen mit „Berührungen anzeigen“. Aus Fingerpunkt und Inhaltsposition pro Frame folgen der Mitlauffaktor, die Rubber-Band-Funktion (Widerstand über Strecke) und das Verhalten nach dem Loslassen in Abhängigkeit von der Geschwindigkeit.
- **Kurvenvergleich Hin/Rück:** Öffnen und Schließen werden getrennt gemessen und nie gespiegelt angenommen.

---

## 1. Farben

| Token | Hell | Dunkel | Quelle |
|---|---|---|---|
| `background` | TODO: Referenz nötig | TODO: Referenz nötig | |
| `surface` (Karten, Listen-Gruppen) | TODO: Referenz nötig | TODO: Referenz nötig | |
| `surfaceVariant` | TODO: Referenz nötig | TODO: Referenz nötig | |
| `textPrimary` | TODO: Referenz nötig | TODO: Referenz nötig | |
| `textSecondary` | TODO: Referenz nötig | TODO: Referenz nötig | |
| `textTertiary` / deaktiviert | TODO: Referenz nötig | TODO: Referenz nötig | |
| `divider` | TODO: Referenz nötig | TODO: Referenz nötig | |
| `accent` (Standard-Akzent) | TODO: Referenz nötig | TODO: Referenz nötig | |
| `accentContainer` | TODO: Referenz nötig | TODO: Referenz nötig | |
| Akzent-Palette (wählbare Akzente) | TODO: Referenz nötig | TODO: Referenz nötig | |
| `error` | TODO: Referenz nötig | TODO: Referenz nötig | |
| CC-Kachel aus (Fläche + α) | TODO: Referenz nötig | TODO: Referenz nötig | |
| CC-Kachel an (Fläche) | TODO: Referenz nötig | TODO: Referenz nötig | |
| CC-Kachel Icon an / aus | TODO: Referenz nötig | TODO: Referenz nötig | |
| Slider-Spur / -Füllung | TODO: Referenz nötig | TODO: Referenz nötig | |
| Benachrichtigungskarte (Fläche + α) | TODO: Referenz nötig | TODO: Referenz nötig | |
| Scrim Lockscreen | TODO: Referenz nötig | TODO: Referenz nötig | |
| Scrim Benachrichtigungsleiste / CC | TODO: Referenz nötig | TODO: Referenz nötig | |
| Dock-Hintergrund (Fläche + α) | TODO: Referenz nötig | TODO: Referenz nötig | |
| Ordner-Hintergrund (geschlossen / offen) | TODO: Referenz nötig | TODO: Referenz nötig | |
| Schublade-Hintergrund | TODO: Referenz nötig | TODO: Referenz nötig | |
| Switch: Spur an/aus, Knopf an/aus | TODO: Referenz nötig | TODO: Referenz nötig | |
| Benachrichtigungspunkt (Badge) | TODO: Referenz nötig | TODO: Referenz nötig | |

Dynamische Akzentfarbe: Herkunft (aus Wallpaper extrahiert oder feste Auswahl) — TODO: Referenz nötig.

## 2. Typografie

Schrift: Platzhalter, austauschbar über eine Theme-Datei. Kandidaten sind Inter Variable und Roboto Flex. Gewählt wird die Schrift, deren x-Höhe und Versalhöhe den gemessenen Werten am nächsten kommen. Entscheidung: TODO: Referenz nötig.

| Rolle | Größe (sp) | Gewicht | Zeilenhöhe | Laufweite | Quelle |
|---|---|---|---|---|---|
| Lockscreen-Uhr (je Uhr-Variante) | TODO: Referenz nötig | | | | |
| Lockscreen-Datum | TODO: Referenz nötig | | | | |
| Lockscreen-Infozeile (Wetter o. ä.) | TODO: Referenz nötig | | | | |
| Statusleisten-Uhr | TODO: Referenz nötig | | | | |
| Benachrichtigung: App-Name / Zeit | TODO: Referenz nötig | | | | |
| Benachrichtigung: Titel | TODO: Referenz nötig | | | | |
| Benachrichtigung: Text | TODO: Referenz nötig | | | | |
| CC-Kachel: Label / Unterlabel | TODO: Referenz nötig | | | | |
| CC große Kachel: Titel / Status | TODO: Referenz nötig | | | | |
| Medienkarte: Titel / Interpret | TODO: Referenz nötig | | | | |
| App-Icon-Beschriftung | TODO: Referenz nötig | | | | |
| Ordner-Titel (offen) | TODO: Referenz nötig | | | | |
| Schublade: Suchfeld, Buchstaben-Leiste | TODO: Referenz nötig | | | | |
| Einstellungen: großer Titel (ausgeklappt / eingeklappt) | TODO: Referenz nötig | | | | |
| Einstellungen: Eintrag-Titel / Untertitel | TODO: Referenz nötig | | | | |
| Dialog: Titel / Text / Button | TODO: Referenz nötig | | | | |

## 3. Formen und Radien

| Element | Form | Radius (dp) | Quelle |
|---|---|---|---|
| App-Icon-Maske | TODO: Referenz nötig (abgerundetes Rechteck oder Superellipse, ggf. Exponent n) | | |
| Benachrichtigungskarte | | TODO: Referenz nötig | |
| CC kleine Kachel | | TODO: Referenz nötig | |
| CC große Kachel / Verbindungskarte | | TODO: Referenz nötig | |
| CC-Slider | | TODO: Referenz nötig | |
| Medienkarte | | TODO: Referenz nötig | |
| Ordner-Icon (geschlossen) | | TODO: Referenz nötig | |
| Ordner-Panel (offen) | | TODO: Referenz nötig | |
| Dock | | TODO: Referenz nötig | |
| Widget-Rahmen | | TODO: Referenz nötig | |
| Recents-Karte | | TODO: Referenz nötig | |
| Dialog | | TODO: Referenz nötig | |
| Bottom-Sheet (oben) | | TODO: Referenz nötig | |
| Button | | TODO: Referenz nötig | |
| Suchfeld | | TODO: Referenz nötig | |
| Einstellungs-Gruppe (Karte) | | TODO: Referenz nötig | |

Bei einer Superellipse werden 12+ Konturpunkte pro Viertel abgetastet und n gefittet.

## 4. Schatten und Elevation

| Element | Versatz x/y (dp) | Blur (dp) | Farbe + α | Quelle |
|---|---|---|---|---|
| App-Icon | TODO: Referenz nötig | | | |
| Icon beim Ziehen (angehoben) | TODO: Referenz nötig | | | |
| Ordner-Panel | TODO: Referenz nötig | | | |
| Dialog / Bottom-Sheet | TODO: Referenz nötig | | | |
| Recents-Karte | TODO: Referenz nötig | | | |

## 5. Blur und Materialien

| Material | Blur-Radius (dp) | Tönung (Farbe + α) | Sättigung / Helligkeit | Fallback API 29–30 | Quelle |
|---|---|---|---|---|---|
| Lockscreen-Benachrichtigungen | TODO: Referenz nötig | | | | |
| Benachrichtigungsleiste (voll offen) | TODO: Referenz nötig | | | | |
| Kontrollzentrum (voll offen) | TODO: Referenz nötig | | | | |
| Dock | TODO: Referenz nötig | | | | |
| Ordner offen | TODO: Referenz nötig | | | | |
| App-Schublade | TODO: Referenz nötig | | | | |
| Recents | TODO: Referenz nötig | | | | |
| Hinter Dialogen / Sheets | TODO: Referenz nötig | | | | |
| Lautstärke-Panel | TODO: Referenz nötig | | | | |

Blur-Aufbau über den Gestenfortschritt (Blur-Radius als Funktion von p) bei Benachrichtigungsleiste und CC: TODO: Referenz nötig.

## 6. Raster und Abstände

| Wert | dp | Quelle |
|---|---|---|
| Seitlicher Bildschirmrand (Standard) | TODO: Referenz nötig | |
| Statusleistenhöhe | TODO: Referenz nötig | |
| Gestenleiste unten (Höhe / Breite) | TODO: Referenz nötig | |
| Launcher-Raster Spalten × Zeilen | TODO: Referenz nötig | |
| App-Icon-Größe | TODO: Referenz nötig | |
| Abstand Icon → Beschriftung | TODO: Referenz nötig | |
| Zellbreite / Zellhöhe | TODO: Referenz nötig | |
| Seitenindikator: Position, Punktgröße, Abstand | TODO: Referenz nötig | |
| Dock: Höhe, Rand, Icon-Anzahl | TODO: Referenz nötig | |
| Ordner offen: Raster, Innenabstand | TODO: Referenz nötig | |
| Ordner-Vorschau: Mini-Icon-Anzahl, -Größe, -Abstand | TODO: Referenz nötig | |
| Schublade: Spalten, Zeilenabstand | TODO: Referenz nötig | |
| CC: Spalten, Kachelgröße klein/groß, Abstand, Rand | TODO: Referenz nötig | |
| Benachrichtigungskarte: Rand, Abstand, Innenabstand | TODO: Referenz nötig | |
| Recents: Kartenbreite, -abstand, Seitenverhältnis | TODO: Referenz nötig | |
| Einstellungen: Eintragshöhe (1- / 2-zeilig), Gruppenabstand | TODO: Referenz nötig | |

## 7. Icons

| Wert | Ausprägung | Quelle |
|---|---|---|
| App-Icon: Keyline-Raster, Inhaltsfläche | TODO: Referenz nötig | |
| App-Icon: Gradient-Stil (Richtung, Stops) | TODO: Referenz nötig | |
| System-Icons: Strichstärke (dp) | TODO: Referenz nötig | |
| System-Icons: Ecken / Enden (rund / eckig) | TODO: Referenz nötig | |
| System-Icons: Gefüllt vs. Kontur je Zustand | TODO: Referenz nötig | |
| Badge: Größe, Position, Zahl ja/nein | TODO: Referenz nötig | |
| Akku-Icon: Form, Prozent innen/außen, Laden-Zustand | TODO: Referenz nötig | |

Alle Icons werden selbst gezeichnet (Compose-Paths / Vector Drawables). Es werden keine Original-Ressourcen verwendet.

## 8. Motion-Tokens

Spring-Notation: `stiffness`, `dampingRatio`. Tween-Notation: Dauer + `cubic-bezier(x1, y1, x2, y2)`.

| Token | Typ | Dauer (ms) | Kurve / Feder | Fit-Fehler | Quelle |
|---|---|---|---|---|---|
| `lock.unlock.settle` (nach Loslassen) | TODO: Referenz nötig | | | | |
| `lock.unlock.cancel` (Rückfedern) | TODO: Referenz nötig | | | | |
| `lock.toHome.wallpaperZoom` | TODO: Referenz nötig | | | | |
| `lock.notification.enter` / `.exit` | TODO: Referenz nötig | | | | |
| `lock.pin.keyPress` / `.errorShake` | TODO: Referenz nötig | | | | |
| `lock.aod.wake` | TODO: Referenz nötig | | | | |
| `shade.open` / `.close` | TODO: Referenz nötig | | | | |
| `cc.open` / `.close` | TODO: Referenz nötig | | | | |
| `cc.tile.stagger` (Verzögerung je Kachel) | TODO: Referenz nötig | | | | |
| `cc.tile.toggle` (Farbe, Skalierung) | TODO: Referenz nötig | | | | |
| `cc.tile.press` (Skalierung) | TODO: Referenz nötig | | | | |
| `cc.slider.press` / `.iconMorph` | TODO: Referenz nötig | | | | |
| `cc.edit.reflow` | TODO: Referenz nötig | | | | |
| `home.page.settle` | TODO: Referenz nötig | | | | |
| `app.open` (Icon → Fenster) | TODO: Referenz nötig | | | | |
| `app.close` (Fenster → Icon) | TODO: Referenz nötig | | | | |
| `folder.open` / `.close` | TODO: Referenz nötig | | | | |
| `drawer.open` / `.close` | TODO: Referenz nötig | | | | |
| `icon.drag.lift` / `.drop` | TODO: Referenz nötig | | | | |
| `editMode.jiggle` (Amplitude °, Periode) | TODO: Referenz nötig | | | | |
| `recents.enter` / `.exit` | TODO: Referenz nötig | | | | |
| `recents.card.dismiss` | TODO: Referenz nötig | | | | |
| `recents.clearAll` (Stagger) | TODO: Referenz nötig | | | | |
| `dialog.enter` / `.exit` | TODO: Referenz nötig | | | | |
| `sheet.enter` / `.exit` | TODO: Referenz nötig | | | | |
| `switch.toggle` | TODO: Referenz nötig | | | | |
| `list.overscroll` | TODO: Referenz nötig | | | | |
| `volume.enter` / `.expand` / `.exit` | TODO: Referenz nötig | | | | |

## 9. Gesten-Parameter

| Parameter | Wert | Quelle |
|---|---|---|
| Entsperren: Distanz-Schwelle (Anteil Höhe) | TODO: Referenz nötig | |
| Entsperren: Fling-Schwelle (dp/s) | TODO: Referenz nötig | |
| Rubber-Band-Funktion (Widerstand über Strecke) | TODO: Referenz nötig | |
| Pull-Down: Trennlinie links/rechts (x-Anteil) | TODO: Referenz nötig | |
| Pull-Down: Mitlauffaktor Finger → Inhalt | TODO: Referenz nötig | |
| Seitenwechsel: Distanz- / Fling-Schwelle | TODO: Referenz nötig | |
| Schublade: Wisch-nach-oben-Schwelle | TODO: Referenz nötig | |
| Recents: Auslöser (Pause / Geschwindigkeit) | TODO: Referenz nötig | |
| Zurück-Geste: Vorschau-Skalierung, Randbreite | TODO: Referenz nötig | |
| Long-Press-Dauer (Shortcuts, Kacheln, Icons) | TODO: Referenz nötig | |

## 10. Haptik

Haptik lässt sich nicht aufnehmen. Die Werte kommen aus deiner Beschreibung (wo spürbar, kurz/lang, stark/schwach) und werden auf Android-Konstanten abgebildet.

| Stelle | Eindruck (Beschreibung) | Android-Abbildung | Quelle |
|---|---|---|---|
| Entsperr-Schwelle erreicht | TODO: Referenz nötig | | |
| Kachel an/aus | TODO: Referenz nötig | | |
| Slider Anschlag / Raster | TODO: Referenz nötig | | |
| Long-Press Icon / Shortcut | TODO: Referenz nötig | | |
| Icon über Ordner / Drop | TODO: Referenz nötig | | |
| Recents-Karte geschlossen | TODO: Referenz nötig | | |
| Buchstaben-Leiste in der Schublade | TODO: Referenz nötig | | |
| PIN-Taste / Fehler | TODO: Referenz nötig | | |

## 11. Layout je Screen

Jeder Screen bekommt eine Tabelle mit Positionen (Abstand von oben/unten in dp), sobald Screenshots vorliegen.

- **Lockscreen:** Uhr-Oberkante und Grundlinie, Datum, Infozeile, Start des Benachrichtigungsstapels, Shortcuts (Position, Größe, Treffer-Fläche), Entsperr-Hinweis, Querformat-Anordnung — TODO: Referenz nötig.
- **Statusleiste:** Uhr-Position, Icon-Reihenfolge, Icon-Größe und Abstand, Kamera-Aussparung — TODO: Referenz nötig.
- **Benachrichtigungsleiste:** Kopfbereich, Kartenstapel, Gruppen-Darstellung, „Alle löschen“ — TODO: Referenz nötig.
- **Kontrollzentrum:** Kopfbereich, Kachelraster, Slider-Position, Medienkarte, Verbindungskarte — TODO: Referenz nötig.
- **Homescreen:** Raster-Ursprung, Widget-Zellen, Seitenindikator, Dock — TODO: Referenz nötig.
- **Ordner:** Panel-Position, Titel, Raster, Seitenindikator — TODO: Referenz nötig.
- **Schublade:** Suchfeld, Kategorien, Buchstaben-Leiste — TODO: Referenz nötig.
- **Recents:** Kartenposition, App-Icon/-Titel über der Karte, „Alle löschen“ — TODO: Referenz nötig.
- **Lautstärke-Panel, Ausschalt-Menü, Dialog, Bottom-Sheet, Einstellungen** — TODO: Referenz nötig.
