# Referenz-Index

> **Status (2026-10-23): leer.** Es liegt noch kein Material vor, alle neun Ordner fehlen vollständig. Aufnahme-Regeln: [`README.md`](README.md).

## Gerät

Diese Angaben gelten für alle Dateien, sofern die Dateitabelle nichts anderes vermerkt.

| Feld | Wert |
|---|---|
| Gerät (Modell) | TODO: Referenz nötig |
| ColorOS-Build (Einstellungen → Über das Gerät) | TODO: Referenz nötig |
| Android-Version | TODO: Referenz nötig |
| Auflösung (px) | TODO: Referenz nötig |
| Dichte (dpi) | TODO: Referenz nötig |
| Display-Bildwiederholrate | TODO: Referenz nötig |
| Aufnahme-Bildrate | TODO: Referenz nötig |

## Dateien

| Datei | Inhalt | Hell/Dunkel | Wallpaper | fps | Abweichendes Gerät/Build | Notiz |
|---|---|---|---|---|---|---|
| — | noch keine | | | | | |

## Fehlendes Material pro Ordner

★ = Minimal-Set für Phase A (Glas-Presets und Grundkomponenten). Wenn ein Merkmal auf deinem Gerät nicht existiert (z. B. Multicolor, Kategorien in der Schublade), sag es mir. Dann streiche ich es, statt zu raten.

### `lockscreen/`

| Datei | Inhalt | |
|---|---|---|
| `aod_dark.png` | Always-on-Display, jeder verfügbare Stil, mindestens Vollbild-AOD |  |
| `aod_to_lock_tap.mp4` | Aufwecken per Tippen: AOD → Lockscreen |  |
| `aod_to_lock_swipe.mp4` | Aufwecken per Wisch |  |
| `aod_lock_home.mp4` | Ganze Kette AOD → Lockscreen → Homescreen in einer Aufnahme |  |
| `lock_light.png`, `lock_dark.png` | Lockscreen ohne Benachrichtigungen | ★ |
| `clock_<stil>.png` | Jeder Uhr-Stil, inkl. gestrecktem Stil, Live Clock, transparentem Uhr-Widget |  |
| `clock_live.mp4` | Live Clock in Bewegung (Wallpaper in den Ziffern) |  |
| `clock_contrast.png` | Dieselbe Uhr auf hellem und auf dunklem Wallpaper (Kontrastlogik) |  |
| `clock_landscape.png` | Querformat, bei faltbarem Gerät auch Faltformat |  |
| `depth_effect.png` | Motiv vor der Uhr (falls verfügbar) |  |
| `parallax.mp4` | Gerät neigen, Wallpaper `calibration/grid_numbered.png` |  |
| `video_wallpaper.mp4` | Motion-Photo-/Video-Wallpaper inkl. Übergang beim Aufwecken/Entsperren (falls genutzt) |  |
| `widgets.png` | Lockscreen mit allen Widgets |  |
| `widgets_edit.mp4` | Widgets hinzufügen und anordnen |  |
| `shortcuts.mp4` | Long-Press auf Shortcut links und rechts |  |
| `island_pill.png` | Lockscreen Island als Pille (Musik läuft) | ★ |
| `island_expand.mp4` | Pille nach unten ziehen bis zum Vollbild-Player und zurück, einmal abbrechen |  |
| `island_player.png` | Vollbild-Player mit Albumcover |  |
| `island_lyrics.png` | Lyrics-Ansicht |  |
| `notifications.png` | 3+ Benachrichtigungen, davon eine Gruppe |  |
| `notifications_hidden.png` | Mit „Inhalte verbergen“ |  |
| `notification_arrive.mp4` | Neue Benachrichtigung trifft ein, dann wegwischen |  |
| `unlock_slow.mp4` | Langsam ziehen, vor der Schwelle loslassen, danach entsperren |  |
| `unlock_fling.mp4` | Schnell entsperren |  |
| `auth_pin.mp4` | PIN inkl. einer Fehleingabe |  |
| `auth_pattern.mp4` | Muster inkl. Fehleingabe |  |
| `auth_password.mp4` | Passwort inkl. Fehleingabe |  |
| `auth_fingerprint.mp4` | Fingerabdruck-Entsperren |  |
| `auth_face.mp4` | Gesichtsentsperren |  |
| `charging.mp4` | Ladekabel im gesperrten Zustand anstecken |  |

### `statusbar-shade/`

| Datei | Inhalt | |
|---|---|---|
| `statusbar_<zustand>.png` | Statusleiste: normal, Laden, Akku niedrig, WLAN/Mobil, viele Icons |  |
| `statusbar_change.mp4` | Icon-Wechsel: Kabel an/ab, WLAN an/aus |  |
| `island_notification.mp4` | Island-Benachrichtigung: erscheinen, zusammenziehen, expandieren, wegwischen |  |
| `island_multi.mp4` | Zwei Island-Benachrichtigungen gleichzeitig |  |
| `shade_light.png`, `shade_dark.png` | Benachrichtigungsleiste voll offen |  |
| `shade_checker.png` | Voll offen, Wallpaper `calibration/checker_360.png` |  |
| `shade_pull_slow.mp4` | Langsam herunterziehen, halten, zurück, loslassen |  |
| `shade_pull_fling.mp4` | Schnell öffnen und schließen |  |
| `shade_zones.mp4` | Herunterziehen an mehreren x-Positionen (Zonen Benachrichtigungen/CC) |  |
| `shade_stack.mp4` | Karten-Stapel scrollen, Gruppe aufklappen |  |
| `shade_swipe.mp4` | Karte wegwischen, halb wischen (Zurückstellen/Aktionen) |  |
| `shade_clear_all.mp4` | „Alle löschen“ |  |

### `control-center/`

| Datei | Inhalt | |
|---|---|---|
| `cc_light.png`, `cc_dark.png` | Kontrollzentrum voll geöffnet | ★ |
| `cc_checker.png` | Voll geöffnet, Wallpaper `calibration/checker_360.png` | ★ |
| `cc_open_slow.mp4` | Langsam öffnen, halten, zurück, loslassen |  |
| `cc_open_fling.mp4` | Schnell öffnen und schließen |  |
| `cc_toggle.mp4` | Mehrere Kacheln an/aus, kleine und große |  |
| `cc_press_hold.mp4` | Kachel gedrückt halten (Drück-Zustand), dann Long-Press |  |
| `cc_brightness.mp4` | Helligkeit bis an beide Enden ziehen |  |
| `cc_volume.mp4` | Lautstärke bis an beide Enden ziehen |  |
| `cc_media.png` | Medienkarte bei laufender Wiedergabe |  |
| `cc_media.mp4` | Medienkarte bedienen |  |
| `cc_connectivity.mp4` | Verbindungskarte per Long-Press erweitern und schließen |  |
| `cc_devices.mp4` | Geräte-Panel öffnen und schließen |  |
| `cc_edit.mp4` | Bearbeiten: Kachel verschieben, skalieren, hinzufügen, entfernen |  |
| `cc_multicolor.png` | Multicolor-Variante (falls vorhanden) |  |

### `home/`

| Datei | Inhalt | |
|---|---|---|
| `home_light.png`, `home_dark.png` | Seite mit Dock, Ordner, Widget und Suchleiste | ★ |
| `home_checker.png` | Dieselbe Seite, Wallpaper `calibration/checker_360.png` | ★ |
| `home_page_swipe.mp4` | Seitenwechsel langsam, als Fling und mit Rubber-Band am Rand |  |
| `flux_shapes.png` | Alle Icon- und Ordner-Formen nebeneinander |  |
| `flux_resize.mp4` | Icon und Ordner per Long-Press in jede Form skalieren, Reflow sichtbar |  |
| `folder.png` | Geschlossener Ordner, kleine und große Form |  |
| `folder_open_light.png`, `folder_open_dark.png` | Offener Ordner mit mehr Apps, als eine Seite fasst | ★ |
| `folder_open_close.mp4` | Ordner öffnen und schließen |  |
| `folder_pages.mp4` | Im Ordner blättern |  |
| `folder_rename.mp4` | Ordner umbenennen |  |
| `jiggle_drag.mp4` | Wackelmodus; Icon auf andere Seite, auf ein Icon (Ordner bilden) und ins Dock ziehen |  |
| `widget_add_resize.mp4` | Widget hinzufügen und skalieren |  |
| `contour_glow.mp4` | Contour-Glow aktiv, einige Sekunden ruhig filmen |  |
| `glow_off.png` | Dieselbe Seite mit Glow aus |  |
| `launcher_settings.png` | Launcher-Einstellungen mit Raster und Icongröße |  |

### `drawer-search/`

| Datei | Inhalt | |
|---|---|---|
| `drawer_light.png`, `drawer_dark.png` | App-Schublade |  |
| `drawer_open.mp4` | Per Wisch nach oben öffnen und schließen |  |
| `drawer_alphabet.mp4` | Alphabetleiste benutzen |  |
| `drawer_categories.png` | Kategorien (falls vorhanden) |  |
| `search.mp4` | Suche öffnen, tippen, Ergebnisse erscheinen |  |
| `search_results.png` | Ergebnisliste |  |

### `recents/`

| Datei | Inhalt | |
|---|---|---|
| `recents_light.png`, `recents_dark.png` | Übersicht mit 4+ Apps |  |
| `recents_enter.mp4` | Recents per Geste öffnen, zurück in eine App |  |
| `recents_dismiss.mp4` | Karte wegwischen, einmal halb und zurück |  |
| `recents_clear_all.mp4` | „Alle löschen“ |  |
| `recents_quick_switch.mp4` | Wisch entlang der unteren Kante |  |

### `nav/`

| Datei | Inhalt | |
|---|---|---|
| `nav_pill.png` | Gesten-Navigationsleiste über hellem und dunklem Inhalt |  |
| `capsule_light.png`, `capsule_dark.png` | Schwebende Kapsel-Navigation in einer System-App (z. B. Telefon) | ★ |
| `capsule_transition.mp4` | Wechsel: App mit Kapsel ↔ App ohne Kapsel |  |
| `back_preview.mp4` | Zurück-Geste langsam: einmal abbrechen, einmal ausführen |  |
| `home_gesture.mp4` | Home-Geste aus einer App |  |

### `app-transitions/`

| Datei | Inhalt | |
|---|---|---|
| `launch_icon.mp4` | App aus Icon öffnen |  |
| `close_icon.mp4` | App per Geste schließen, zurück ins Icon |  |
| `launch_folder.mp4` | App aus Ordner öffnen und schließen |  |
| `launch_recents.mp4` | App aus Recents öffnen |  |
| `launch_interrupt.mp4` | Beim Öffnen sofort zurückwischen (Unterbrechung) |  |

### `system-panels/`

| Datei | Inhalt | |
|---|---|---|
| `volume.png` | Lautstärke-Panel ausgeklappt |  |
| `volume.mp4` | Taste drücken, ausklappen, ziehen, ausblenden |  |
| `power_menu.png` | Ausschalt-Menü |  |
| `power_menu.mp4` | Ausschalt-Menü öffnen und schließen |  |
| `dialog_light.png`, `dialog_dark.png` | Standard-Dialog mit zwei Buttons | ★ |
| `dialog.mp4` | Dialog öffnen und schließen | ★ |
| `sheet_light.png`, `sheet_dark.png` | Bottom-Sheet | ★ |
| `sheet.mp4` | Öffnen, ziehen, Liste darin scrollen, schließen | ★ |
| `switch.mp4` | Switch antippen und ziehen | ★ |
| `toggles.png` | Toggle-Buttons / Segmente | ★ |
| `slider.mp4` | Einstellungs-Slider ziehen | ★ |
| `settings_main_light.png`, `settings_main_dark.png` | Einstellungen, Hauptseite | ★ |
| `settings_sub_light.png`, `settings_sub_dark.png` | Unterseite mit Switches, Slider, zweizeiligen Einträgen | ★ |
| `settings_scroll.mp4` | Scrollen, Überscrollen, großer Titel klappt ein | ★ |
| `keyboard.mp4` | Tastatur im Suchfeld ein- und ausblenden |  |
| `reduce_motion.mp4` | CC öffnen und App öffnen mit „Animationen entfernen“ an |  |
| `haptics.md` | Text: wo Vibration spürbar ist, kurz/lang, stark/schwach | ★ |
