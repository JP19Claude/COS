# Referenzmaterial

Hier liegen Screenshots und Bildschirmaufnahmen von ColorOS 17. Alle Werte in [`DESIGN_SPEC.md`](../DESIGN_SPEC.md) und [`MOTION_TOKENS.md`](../MOTION_TOKENS.md) werden daraus gemessen. Das Material dient nur als private Referenz und wird nie Teil der App.

> **Das Repo ist öffentlich** (Stand 2026-10-24). Solange das so ist, werden Referenzbilder und -videos **nicht** committed. Ich werte sie aus, wenn du sie im Chat hochlädst. Ins Repo kommen nur die gemessenen Zahlen mit Quellenlink. Willst du das Material selbst ablegen, stell das Repo vorher auf privat.

- **Woher das Material kommt:** Es gibt kein eigenes ColorOS-17-Gerät. Das Material stammt aus öffentlichen Quellen, siehe [`QUELLEN.md`](QUELLEN.md).
- **Was fehlt:** steht pro Ordner in [`INDEX.md`](INDEX.md).
- **Was zuerst gebraucht wird:** steht in [`../CHECKLISTE.md`](../CHECKLISTE.md).

## Ordner

| Ordner | Inhalt |
|---|---|
| `lockscreen/` | Entsperren, AOD→Lockscreen, Uhr-Stile, Live Clock, Widgets, Benachrichtigungen, Shortcuts, PIN/Muster/Passwort/Fingerabdruck/Gesicht |
| `statusbar-shade/` | Statusleiste, Herunterziehen links/rechts, Island-Benachrichtigungen, Gruppierung |
| `control-center/` | Öffnen, Schließen, Kacheln, Slider, Medien, Bearbeiten-Modus, Long-Press-Erweiterungen |
| `home/` | Seitenwechsel, Flux Home (Resize), Ordner, Wackelmodus, Widgets |
| `drawer-search/` | App-Schublade und Suche |
| `recents/` | Multitasking |
| `nav/` | Kapsel-Navigationsleiste, Gesten, Predictive Back |
| `app-transitions/` | App öffnen/schließen aus Icon, Ordner, Recents |
| `system-panels/` | Lautstärke, Ausschalt-Menü, Dialoge, Bottom-Sheets, Toggles, Switches, Einstellungen |
| `calibration/` | Kalibrier-Wallpaper (von mir erzeugt). Nur für eigene Aufnahmen an einem ColorOS-17-Gerät nötig |

## Regeln für öffentliches Material

1. **Link immer mitschicken.** Er kommt in die Dateitabelle von `INDEX.md` und dient als Quellenangabe in der Spec.
2. **Originalgröße:** Bilder im Browser in voller Auflösung öffnen und dann speichern, keine Vorschaubilder.
3. **Bildschirmfotos vor Fotos vom Handy.** Abgefilmte Handys sind für Maße unbrauchbar.
4. **Videos:** möglichst 1080p60 oder besser. Bei YouTube-Videos zusätzlich den Zeitstempel nennen.
5. **Hell und Dunkel**, wo es beides gibt. Fehlt eine Variante, schreib „nur hell“ bzw. „nur dunkel“.
6. **Nicht über Messenger schicken**, die komprimieren. Direkt hier im Chat hochladen.

## Falls du später ein ColorOS-17-Gerät hast

Dann sind eigene Aufnahmen deutlich genauer. Dafür gelten diese Regeln:
- **Einstellungen:** Animationsskalen auf 1x, „Berührungen anzeigen“ an, höchste Bildwiederholrate, Bildschirmaufnahme in höchster Qualität, Anzeige- und Schriftgröße auf Standard.
- **Kalibrier-Wallpaper:** Für Glas-Aufnahmen [`calibration/checker_360.png`](calibration/checker_360.png), für Parallax [`calibration/grid_numbered.png`](calibration/grid_numbered.png).
- **Gerätedaten:** `adb shell wm size`, `adb shell wm density` und `adb shell getprop ro.build.display.id` notieren.

Eigene Aufnahmen ersetzen dann die öffentlichen Werte.

Die Kalibrier-Wallpaper lassen sich neu erzeugen mit:

```
python3 tools/make_calibration_wallpapers.py reference/calibration
```
