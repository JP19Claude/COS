# Referenzmaterial

Hier liegen Screenshots und Bildschirmaufnahmen des Originals. Alle Werte in [`DESIGN_SPEC.md`](../DESIGN_SPEC.md) werden daraus gemessen. Das Material dient nur als private Referenz. Es wird nie Teil der App und das Repo bleibt privat.

## Aufnahme-Regeln

Diese Einstellungen machen die Messungen erst möglich:

1. **Eine Version.** Das gesamte Material stammt von einem Gerät mit einer OS-Version. Notiere Modell und Version (Einstellungen → Über das Gerät).
2. **Standardgrößen.** Anzeigegröße und Schriftgröße auf Standard.
3. **Entwickleroptionen:**
   - Alle drei Animationsskalen auf **1x**.
   - **Berührungen anzeigen** an, für alle Gesten-Videos. Damit lässt sich messen, wie der Inhalt dem Finger folgt.
4. **Bildrate:** Display auf die höchste Bildwiederholrate stellen. Die Bildschirmaufnahme mit höchster Auflösung und höchster fps (mind. 60, besser 90/120), ohne Mikrofon.
5. **Unkomprimiert übertragen.** Screenshots als PNG in Original-Auflösung. Nicht über Messenger schicken, die komprimieren. Besser per USB, Cloud-Ordner oder direkt hier im Chat hochladen.
6. **Hell und Dunkel**, wo die Liste es nennt.
7. **Gesten zweimal:** einmal langsam (halten, hin und her ziehen, kurz vor der Schwelle loslassen) und einmal schnell als Fling.
8. **Kalibrier-Wallpaper:** Statische Aufnahmen von Overlays (CC, Benachrichtigungsleiste, Dock, Ordner, Schublade, Recents) zusätzlich einmal mit [`calibration/checker_360.png`](calibration/checker_360.png) als Wallpaper. Daran lassen sich Blur-Stärke, Transparenz und Tönung exakt ausrechnen. Für Parallax/Zoom-Videos [`calibration/grid_numbered.png`](calibration/grid_numbered.png) verwenden.

Falls adb vorhanden ist, bitte zusätzlich die Ausgabe von:

```
adb shell wm size
adb shell wm density
adb shell getprop ro.build.display.id
```

## Übertragung

- **Bevorzugt:** Dateien hier im Chat hochladen. Ich lege sie in diesem Ordner nach dem Namensschema unten ab.
- **Alternativ:** direkt in `reference/` committen. Videos über ca. 50 MB besser per Git LFS.

## Checkliste

Dateinamen sind Vorschläge, damit die Spec-Quellen eindeutig bleiben. `.png` = Screenshot, `.mp4` = Aufnahme. **Fett** = Minimal-Set A, damit Phase A (Design-System) starten kann.

### Design-System (Phase A)
| Datei | Inhalt |
|---|---|
| **`settings_main_light.png` / `_dark.png`** | Einstellungen, Hauptseite |
| **`settings_sub_light.png` / `_dark.png`** | Unterseite mit Schaltern, Slider, zweizeiligen Einträgen |
| **`settings_scroll.mp4`** | Scrollen, Überscrollen oben und unten, großer Titel klappt ein |
| **`switch_toggle.mp4`** | Schalter an/aus, einmal tippen, einmal ziehen |
| **`dialog.png`**, **`dialog_open_close.mp4`** | Standard-Dialog mit zwei Buttons |
| **`bottom_sheet.png`**, **`bottom_sheet_open_close.mp4`** | Bottom-Sheet öffnen, ziehen, schließen |
| **`accent_colors.png`** | Auswahl der Akzentfarben (falls vorhanden) |
| `haptics.md` | Text: wo du Vibration spürst, kurz/lang, stark/schwach (siehe Spec §10) |

### Lockscreen (Phase B)
| Datei | Inhalt |
|---|---|
| **`lock_light.png` / `lock_dark.png`** | Lockscreen ohne Benachrichtigungen |
| `lock_notifications.png` | Mit 3+ Benachrichtigungen, davon eine Gruppe |
| `lock_notifications_hidden.png` | Mit „Inhalte verbergen“ |
| `lock_clock_<variante>.png` | Jede Uhr-Variante |
| `lock_depth.png` | Uhr hinter Motiv, falls vorhanden |
| `lock_landscape.png` | Querformat, falls unterstützt |
| `lock_unlock_swipe.mp4` | Langsam ziehen, vor Schwelle loslassen (Rückfedern), dann entsperren bis Homescreen |
| `lock_unlock_fling.mp4` | Schnell entsperren |
| `lock_pin.mp4` | PIN-Eingabe inkl. einer Fehleingabe |
| `lock_pattern.mp4` | Muster inkl. Fehleingabe |
| `lock_fingerprint.mp4` / `lock_face.mp4` | Biometrie-Entsperren |
| `lock_shortcuts.mp4` | Long-Press Taschenlampe und Kamera |
| `lock_notification_arrival.mp4` | Neue Benachrichtigung trifft ein |
| `lock_parallax.mp4` | Gerät neigen (mit Grid-Wallpaper) |
| `aod.png`, `aod_to_lock.mp4` | Always-on-Display und Aufwecken |

### Statusleiste und Benachrichtigungsleiste (Phase C)
| Datei | Inhalt |
|---|---|
| `statusbar_<zustand>.png` | Statusleiste: normal, Laden, Akku niedrig, WLAN/Mobil, viele Benachrichtigungs-Icons |
| `shade_light.png` / `shade_dark.png` | Benachrichtigungsleiste voll offen |
| `shade_pull_slow.mp4` / `shade_pull_fling.mp4` | Öffnen und Schließen |
| `shade_group_expand.mp4` | Gruppe aufklappen |
| `shade_dismiss.mp4` | Wisch-zum-Schließen, „Alle löschen“ |

### Kontrollzentrum (Phase C)
| Datei | Inhalt |
|---|---|
| **`cc_light.png` / `cc_dark.png`** | Voll geöffnet |
| `cc_checker.png` | Voll geöffnet mit Kalibrier-Wallpaper |
| `cc_open_slow.mp4` / `cc_open_fling.mp4` | Öffnen und Schließen |
| `cc_switch_shade.mp4` | Wechsel zwischen Benachrichtigungen und CC (falls vorhanden) |
| `cc_toggle.mp4` | Mehrere Kacheln an/aus |
| `cc_brightness.mp4` / `cc_volume.mp4` | Slider ziehen, bis zu beiden Enden |
| `cc_media.png`, `cc_media.mp4` | Medienkarte mit laufender Wiedergabe |
| `cc_connectivity_longpress.mp4` | Verbindungskarte erweitern |
| `cc_edit.mp4` | Bearbeiten-Modus, Kachel verschieben, hinzufügen, entfernen |

### Homescreen, Ordner, Schublade (Phase D)
| Datei | Inhalt |
|---|---|
| **`home_light.png` / `home_dark.png`** | Erste Seite mit Dock und mind. einem Ordner |
| `home_checker.png` | Dieselbe Seite mit Kalibrier-Wallpaper |
| `home_page_swipe.mp4` | Langsam, Fling und Rand-Rubber-Band |
| `home_app_open_close.mp4` | App öffnen, per Geste zurück |
| `home_back_preview.mp4` | Zurück-Geste langsam ziehen (Vorschau) |
| `folder.png`, `folder_open.png` | Geschlossen und offen, mit mehr Apps als eine Seite fasst |
| `folder_open_close.mp4`, `folder_rename.mp4` | |
| `icon_drag.mp4` | Icon ziehen, auf andere Seite, auf Icon (Ordner bilden), ins Dock |
| `edit_mode.mp4` | Bearbeiten-/Wackel-Modus |
| `widget_add_resize.mp4` | Widget hinzufügen und skalieren |
| `drawer.png`, `drawer_open.mp4`, `drawer_search.mp4`, `drawer_alphabet.mp4` | App-Schublade (falls aktiviert) |
| `launcher_settings.png` | Launcher-Einstellungen mit aktuellem Raster und Icongröße |

### Recents (Phase E)
| Datei | Inhalt |
|---|---|
| `recents.png` | Übersicht mit 4+ Apps |
| `recents_enter.mp4` | Öffnen per Geste und Rückkehr in eine App |
| `recents_dismiss.mp4`, `recents_clear_all.mp4` | Karte wegwischen, alle löschen |
| `recents_quick_switch.mp4` | Wisch entlang der unteren Kante |

### Weitere Oberflächen (Phase F)
| Datei | Inhalt |
|---|---|
| `volume.png`, `volume.mp4` | Lautstärke-Panel einblenden, ausklappen, ausblenden |
| `power_menu.png`, `power_menu.mp4` | Ausschalt-Menü |

## Kalibrier-Wallpaper neu erzeugen

```
python3 tools/make_calibration_wallpapers.py reference/calibration
```
