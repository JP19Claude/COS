# Checkliste: Was ich noch von dir brauche

Diese Liste reicht für den Start mit Phase A. Hake ab, was erledigt ist, und lade die Dateien hier im Chat hoch. Was danach noch kommt, steht Phase für Phase in [`reference/INDEX.md`](reference/INDEX.md).

## 1. Angaben zum Handy

- [ ] Handymodell (Einstellungen → Über das Gerät)
- [ ] Genaue ColorOS-Version / Build-Nummer (ebenda)
- [ ] Auflösung und dpi, falls du sie findest. Mit adb: `adb shell wm size` und `adb shell wm density`

## 2. Handy vorbereiten (einmalig)

- [ ] **Entwickleroptionen einschalten:** Die Build-Nummer 7× antippen. Bei ColorOS meist unter Einstellungen → Über das Gerät → Version.
- [ ] In den Entwickleroptionen alle drei **Animationsskalen auf 1x** stellen.
- [ ] In den Entwickleroptionen **„Berührungen anzeigen“** einschalten (nur für Videos nötig).
- [ ] Display auf die **höchste Bildwiederholrate** stellen.
- [ ] **Bildschirmaufnahme** auf höchste Auflösung und höchste Bildrate stellen, Ton aus.
- [ ] **Schachbrett-Wallpaper** aufs Handy laden (`reference/calibration/checker_360.png`, schicke ich dir mit). Du brauchst es nur für die drei `*_checker.png`-Screenshots.
- [ ] Anzeigegröße und Schriftgröße auf **Standard**.

## 3. Die 28 Dateien für den Start

**Screenshot** = normales Bildschirmfoto. **Video** = Bildschirmaufnahme. Bei „hell/dunkel“ brauche ich zwei Bilder: einmal im hellen und einmal im dunklen Modus. Den Dateinamen musst du nicht selbst vergeben, schreib einfach dazu, was es ist.

### Lockscreen (3)
- [ ] `lock_light.png`: Lockscreen ohne Benachrichtigungen, heller Modus (Screenshot)
- [ ] `lock_dark.png`: dasselbe im dunklen Modus (Screenshot)
- [ ] `island_pill.png`: Musik abspielen, Handy sperren, Bildschirm an. Die Musik-Pille auf dem Lockscreen (Screenshot)

### Kontrollzentrum (3)
- [ ] `cc_light.png`: Kontrollzentrum ganz geöffnet, hell (Screenshot)
- [ ] `cc_dark.png`: dasselbe, dunkel (Screenshot)
- [ ] `cc_checker.png`: Kontrollzentrum ganz geöffnet, mit dem Schachbrett als Wallpaper (Screenshot)

### Homescreen und Ordner (5)
- [ ] `home_light.png`: Startseite mit Dock, mindestens einem Ordner, einem Widget und der Suchleiste, hell (Screenshot)
- [ ] `home_dark.png`: dieselbe Seite, dunkel (Screenshot)
- [ ] `home_checker.png`: dieselbe Seite mit dem Schachbrett als Wallpaper (Screenshot)
- [ ] `folder_open_light.png`: ein geöffneter Ordner mit mehr Apps, als auf eine Ordner-Seite passen, hell (Screenshot)
- [ ] `folder_open_dark.png`: dasselbe, dunkel (Screenshot)

### Navigationsleiste (2)
- [ ] `capsule_light.png`: die schwebende Kapsel-Leiste unten in einer System-App, z. B. Telefon, hell (Screenshot)
- [ ] `capsule_dark.png`: dasselbe, dunkel (Screenshot)

### Einstellungen und Bedienelemente (15)
- [ ] `settings_main_light.png`: Einstellungen, Hauptseite, hell (Screenshot)
- [ ] `settings_main_dark.png`: dasselbe, dunkel (Screenshot)
- [ ] `settings_sub_light.png`: eine Einstellungs-Unterseite mit Schaltern, einem Slider und zweizeiligen Einträgen, hell (Screenshot)
- [ ] `settings_sub_dark.png`: dasselbe, dunkel (Screenshot)
- [ ] `settings_scroll.mp4`: In den Einstellungen langsam nach unten scrollen, über das Ende hinausziehen, wieder nach oben und über den Anfang hinausziehen. Der große Titel soll dabei einklappen (Video)
- [ ] `switch.mp4`: einen Schalter ein paarmal antippen, dann einmal mit dem Finger hinüberziehen (Video)
- [ ] `slider.mp4`: einen Slider in den Einstellungen langsam und schnell bis an beide Enden ziehen (Video)
- [ ] `toggles.png`: Auswahl-Buttons bzw. Segment-Schalter, falls es sie gibt (Screenshot)
- [ ] `dialog_light.png`: ein normaler Dialog mit zwei Buttons, z. B. eine Löschen-Abfrage, hell (Screenshot)
- [ ] `dialog_dark.png`: dasselbe, dunkel (Screenshot)
- [ ] `dialog.mp4`: den Dialog öffnen und wieder schließen (Video)
- [ ] `sheet_light.png`: ein Menü, das von unten hochfährt, z. B. das Teilen-Menü, hell (Screenshot)
- [ ] `sheet_dark.png`: dasselbe, dunkel (Screenshot)
- [ ] `sheet.mp4`: das Menü öffnen, nach oben und unten ziehen, darin scrollen, schließen (Video)
- [ ] `haptics.md`: ein paar Sätze, wo du beim Bedienen eine Vibration spürst und wie sie sich anfühlt: kurz oder lang, stark oder schwach. Zum Beispiel beim Schalter, beim Slider-Ende, beim langen Drücken auf ein Icon (Text)

## 4. Damit ich die App bauen kann

- [ ] **Netzwerk freigeben:** In den Einstellungen dieser Cloud-Umgebung unter Network access → Allowed domains `dl.google.com` eintragen. Anleitung: https://code.claude.com/docs/en/cloud-environments#network-access
- [ ] **Oder** sag mir, dass die APK stattdessen über GitHub Actions gebaut werden soll.

## 5. Optional

- [ ] Ein neutraler App-Name. Sonst nehme ich „Shell“.
- [ ] Okay für die freie Blur-Bibliothek Haze (Apache-2.0). Sonst entscheide ich nach dem Vergleich in Phase A.

## Hinweise

- Bilder bitte als **Original-PNG** hochladen, nicht über WhatsApp o. Ä. schicken, das komprimiert.
- Gibt es etwas auf deinem Handy nicht, schreib einfach „gibt es nicht“. Dann streiche ich es.
