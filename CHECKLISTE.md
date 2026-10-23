# Checkliste: Was ich noch von dir brauche

Es gibt kein ColorOS-17-Gerät. Deshalb kommt das Material aus öffentlichen Quellen. In [`reference/QUELLEN.md`](reference/QUELLEN.md) stehen Links zu Testberichten, zur offiziellen Vorstellung und Suchtipps für YouTube. Hake ab, was erledigt ist, und lade die Dateien hier im Chat hoch, **jeweils mit dem Link, woher sie stammen**.

Du findest etwas nicht? Schreib „nicht gefunden“. Dann markiere ich den Wert als geschätzt und rate nicht still.

## 1. Bilder und Videos für den Start (Phase A)

Gesucht ist jeweils die beste Aufnahme, die du findest. „Hell/dunkel“ heißt: beide Varianten, falls vorhanden.

### Lockscreen
- [ ] Lockscreen ohne Benachrichtigungen, hell
- [ ] Lockscreen ohne Benachrichtigungen, dunkel
- [ ] Lockscreen mit der Musik-Pille (Island)

### Kontrollzentrum
- [ ] Kontrollzentrum ganz geöffnet, hell
- [ ] Kontrollzentrum ganz geöffnet, dunkel

### Homescreen und Ordner
- [ ] Startseite mit Dock und Ordner, möglichst auch mit Widget und Suchleiste, hell
- [ ] dasselbe, dunkel
- [ ] Geöffneter Ordner, hell
- [ ] Geöffneter Ordner, dunkel

### Navigationsleiste
- [ ] Die schwebende Kapsel-Leiste unten, z. B. in der Telefon-App, hell
- [ ] dasselbe, dunkel

### Einstellungen und Bedienelemente
- [ ] Einstellungen, Hauptseite, hell
- [ ] Einstellungen, Hauptseite, dunkel
- [ ] Eine Einstellungs-Unterseite mit Schaltern und Slider, hell
- [ ] dasselbe, dunkel
- [ ] Video: Scrollen in den Einstellungen, mit einklappendem großem Titel
- [ ] Video: Schalter wird umgelegt
- [ ] Video: Slider wird gezogen
- [ ] Auswahl-Buttons bzw. Segment-Schalter, falls es sie gibt
- [ ] Ein normaler Dialog mit zwei Buttons, hell
- [ ] dasselbe, dunkel
- [ ] Video: Dialog öffnet und schließt
- [ ] Ein Menü, das von unten hochfährt (Bottom-Sheet, z. B. Teilen-Menü), hell
- [ ] dasselbe, dunkel
- [ ] Video: Bottom-Sheet öffnet, wird gezogen und schließt

**Am meisten bringen:** offizielle Pressebilder, Bildschirmfotos aus Testberichten in voller Größe und Hands-on-Videos mit Bildschirmaufnahmen in 1080p60.

## 2. Deine Testgeräte

- [x] Galaxy S22 Ultra und Galaxy S25 FE: Darauf installieren wir die App und messen Leistung, Gesten und Haptik.
- [ ] Optional: Android- und One-UI-Version beider Geräte (Einstellungen → Telefoninfo → Softwareinformationen)

## 3. Damit ich die App bauen kann

- [ ] **Netzwerk freigeben:** In den Einstellungen dieser Cloud-Umgebung unter Network access → Allowed domains `dl.google.com` eintragen. Anleitung: https://code.claude.com/docs/en/cloud-environments#network-access
- [ ] **Oder:** Sag mir, dass die APK stattdessen über GitHub Actions gebaut werden soll. Du lädst sie dort herunter und installierst sie auf deinem Samsung.

## 4. Optional

- [ ] Ein neutraler App-Name. Sonst nehme ich „Shell“.
- [ ] Okay für die freie Blur-Bibliothek Haze (Apache-2.0). Sonst entscheide ich nach dem Vergleich in Phase A.

## Was ohne ColorOS-Gerät nicht geht

- **Pixelgenauigkeit:** Öffentliche Bilder sind komprimiert, und die genaue Displaydichte ist unbekannt. Proportionen messe ich genau. Der absolute Maßstab ist ein einziger, gekennzeichneter Schätzwert.
- **Gesten:** Ohne „Berührungen anzeigen“ ist nicht zu sehen, wo der Finger ist. Mitlauffaktor und Schwellen werden deshalb meist geschätzt.
- **Haptik:** Sie kommt nur aus Beschreibungen in Testberichten und wird geschätzt.
- **Kalibrier-Wallpaper:** Sie entfallen. Blur messe ich stattdessen an Textkanten hinter dem Glas.
