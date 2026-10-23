# Referenzmaterial

Hier liegen Screenshots und Bildschirmaufnahmen des Originals (ColorOS 17). Alle Werte in [`DESIGN_SPEC.md`](../DESIGN_SPEC.md) und [`MOTION_TOKENS.md`](../MOTION_TOKENS.md) werden daraus gemessen. Das Material dient nur als private Referenz. Es wird nie Teil der App und das Repo bleibt privat.

Was konkret fehlt, steht pro Ordner in [`INDEX.md`](INDEX.md).

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
| `calibration/` | Kalibrier-Wallpaper (von mir erzeugt, kein Original-Material) |

## Aufnahme-Regeln

1. **Ein Gerät, ein Build.** Das gesamte Material stammt von einem Gerät mit einem ColorOS-17-Build. Trage Gerät, Auflösung, dpi, Bildrate und Build oben in `INDEX.md` ein. Weicht eine Datei ab, vermerke das in deren Zeile.
2. **Standardgrößen:** Anzeigegröße und Schriftgröße stehen auf Standard.
3. **Entwickleroptionen:**
   - Alle drei Animationsskalen auf **1x**.
   - **Berührungen anzeigen** an, für alle Gesten-Videos. Damit lässt sich messen, wie der Inhalt dem Finger folgt.
4. **Bildrate:** Display auf die höchste Bildwiederholrate. Bildschirmaufnahme mit höchster Auflösung und höchster fps (mind. 60, besser 120), ohne Mikrofon.
5. **Hell und Dunkel:** Jeder Screenshot mit `{light,dark}` im Namen wird in beiden Modi gebraucht. Bei Videos genügt ein Modus, sofern nicht anders angegeben.
6. **Gesten zweimal:** einmal langsam (halten, hin und her ziehen, kurz vor der Schwelle loslassen) und einmal schnell als Fling.
7. **Wallpaper notieren:** Glas verändert Farben. Vermerke in `INDEX.md`, welches Wallpaper zu sehen ist. Für die `*_checker.png`-Aufnahmen [`calibration/checker_360.png`](calibration/checker_360.png) als Wallpaper setzen. Daran lassen sich Blur, Transparenz und Tönung exakt ausrechnen. Für Parallax [`calibration/grid_numbered.png`](calibration/grid_numbered.png) verwenden.
8. **Unkomprimiert übertragen:** PNG in Original-Auflösung. Nicht über Messenger schicken, die komprimieren.

Falls adb vorhanden ist, bitte zusätzlich die Ausgabe von:

```
adb shell wm size
adb shell wm density
adb shell getprop ro.build.display.id
```

## Übertragung

- **Bevorzugt:** Dateien hier im Chat hochladen. Ich lege sie im passenden Ordner ab und trage sie in `INDEX.md` ein.
- **Alternativ:** direkt committen. Videos über ca. 50 MB besser per Git LFS.

## Kalibrier-Wallpaper neu erzeugen

```
python3 tools/make_calibration_wallpapers.py reference/calibration
```
