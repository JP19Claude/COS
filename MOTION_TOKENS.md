# MOTION_TOKENS

Alle Bewegungs-Tokens des Nachbaus. `MotionTokens.kt` wird hieraus befüllt. Jedes Token im Code trägt den Namen und die Quelle aus dieser Tabelle.

> **Status (2026-10-23): Gerüst (Phase 0).** `—` heißt: noch nicht gemessen. Jede Zeile bleibt `TODO: Referenz nötig`, bis Quelle und Fit-Fehler eingetragen sind.

## Abnahmeziele

- Positions- bzw. Skalenfehler unter 3 % des Wegs pro Frame, Dauer weniger als 1 Frame Abweichung (Report: `MOTION_REPORT.md`).
- Jede Animation ist unterbrechbar und übernimmt die Geschwindigkeit. Beim Abbrechen oder Umkehren gibt es keine Sprünge.

## Notation

- **Feder:** `spring(stiffness, dampingRatio)` in Compose-Einheiten. **Bezier:** `cubic(x1, y1, x2, y2)` plus Dauer.
- **Fit-Fehler:** RMS über alle Frames in % des Gesamtwegs, dazu der maximale Einzelfehler.
- **Geste:** Ob das Token fingergeführt ist (`ja`) oder nur beim Loslassen startet (`nein`).

## Tokens

| Token | Übergang | Komponente | Geste | Typ | Dauer (ms) | Kurve / Feder | Delay / Stagger | Start → Ende | Fit-Fehler | Quelle (erwartet) | Status |
|---|---|---|---|---|---|---|---|---|---|---|---|
| `aodWake` | AOD → Lockscreen (Tippen, Wisch) | §5 AOD; §5 Entsperren und Wallpaper-Übergang AOD → Lockscreen → Homescreen | nein | — | — | — | — | — | — | `lockscreen/aod_to_lock_tap.mp4`, `lockscreen/aod_to_lock_swipe.mp4`, `lockscreen/aod_lock_home.mp4` | TODO: Referenz nötig |
| `wallpaperParallax` | Wallpaper-/Motiv-Versatz beim Neigen | §5 Uhr – Live Clock; §5 Tiefeneffekt (Motiv vor Uhr) | Sensor | — | — | — | — | — | — | `lockscreen/parallax.mp4`, `lockscreen/clock_live.mp4` | TODO: Referenz nötig |
| `videoWallpaperTransition` | Video-Wallpaper beim Aufwecken/Entsperren | §5 Video-Wallpaper | nein | — | — | — | — | — | — | `lockscreen/video_wallpaper.mp4` | TODO: Referenz nötig |
| `lockWidgetEdit` | Lockscreen-Widgets anordnen | §5 Lockscreen-Widgets | ja | — | — | — | — | — | — | `lockscreen/widgets_edit.mp4` | TODO: Referenz nötig |
| `lockShortcutPress` | Shortcut gedrückt halten bis Auslösung | §5 Shortcuts links/rechts | ja | — | — | — | — | — | — | `lockscreen/shortcuts.mp4` | TODO: Referenz nötig |
| `lockShortcutTrigger` | Shortcut ausgelöst | §5 Shortcuts links/rechts | nein | — | — | — | — | — | — | `lockscreen/shortcuts.mp4` | TODO: Referenz nötig |
| `lockIslandExpand` | Island-Pille → Vollbild-Player | §5 Lockscreen Island – Pille; §5 Lockscreen Island – Vollbild-Player und Lyrics | ja | — | — | — | — | — | — | `lockscreen/island_expand.mp4` | TODO: Referenz nötig |
| `lockIslandCollapse` | Vollbild-Player → Island-Pille | §5 Lockscreen Island – Pille; §5 Lockscreen Island – Vollbild-Player und Lyrics | ja | — | — | — | — | — | — | `lockscreen/island_expand.mp4` | TODO: Referenz nötig |
| `lyricsLineChange` | Lyrics-Zeilenwechsel | §5 Lockscreen Island – Vollbild-Player und Lyrics | nein | — | — | — | — | — | — | `lockscreen/island_expand.mp4` | TODO: Referenz nötig |
| `lockNotificationEnter` | Benachrichtigung erscheint auf dem Lockscreen | §5 Lockscreen-Benachrichtigungskarte | nein | — | — | — | — | — | — | `lockscreen/notification_arrive.mp4` | TODO: Referenz nötig |
| `lockNotificationExit` | Benachrichtigung verschwindet vom Lockscreen | §5 Lockscreen-Benachrichtigungskarte | ja | — | — | — | — | — | — | `lockscreen/notification_arrive.mp4` | TODO: Referenz nötig |
| `unlockDrag` | Entsperr-Wisch: Fingerfolge und Rubber-Band | §5 Entsperren und Wallpaper-Übergang AOD → Lockscreen → Homescreen | ja | — | — | — | — | — | — | `lockscreen/unlock_slow.mp4`, `lockscreen/unlock_fling.mp4` | TODO: Referenz nötig |
| `unlockWallpaper` | Wallpaper Lockscreen → Homescreen (Skalierung, Blur, Helligkeit, Position) | §5 Entsperren und Wallpaper-Übergang AOD → Lockscreen → Homescreen | ja | — | — | — | — | — | — | `lockscreen/aod_lock_home.mp4`, `lockscreen/unlock_slow.mp4`, `lockscreen/unlock_fling.mp4` | TODO: Referenz nötig |
| `unlockCancel` | Rückfedern bei abgebrochenem Entsperren | §5 Entsperren und Wallpaper-Übergang AOD → Lockscreen → Homescreen | nein | — | — | — | — | — | — | `lockscreen/unlock_slow.mp4` | TODO: Referenz nötig |
| `authEnter` | Authentifizierungs-Ansicht erscheint | §5 Authentifizierung – PIN; §5 Authentifizierung – Muster; §5 Authentifizierung – Passwort | ja | — | — | — | — | — | — | `lockscreen/auth_pin.mp4`, `lockscreen/auth_pattern.mp4`, `lockscreen/auth_password.mp4` | TODO: Referenz nötig |
| `pinKeyPress` | PIN-Taste gedrückt | §5 Authentifizierung – PIN | nein | — | — | — | — | — | — | `lockscreen/auth_pin.mp4` | TODO: Referenz nötig |
| `authError` | Fehleingabe | §5 Authentifizierung – PIN; §5 Authentifizierung – Muster; §5 Authentifizierung – Passwort | nein | — | — | — | — | — | — | `lockscreen/auth_pin.mp4`, `lockscreen/auth_pattern.mp4`, `lockscreen/auth_password.mp4` | TODO: Referenz nötig |
| `patternDotHit` | Musterpunkt getroffen | §5 Authentifizierung – Muster | nein | — | — | — | — | — | — | `lockscreen/auth_pattern.mp4` | TODO: Referenz nötig |
| `keyboardInset` | Inhalt folgt der Tastatur | §5 Authentifizierung – Passwort; §8 Ordner offen; §9 Suche; §13 Tastatur-Übergang | nein | — | — | — | — | — | — | `lockscreen/auth_password.mp4`, `home/folder_rename.mp4`, `drawer-search/search.mp4`, `system-panels/keyboard.mp4` | TODO: Referenz nötig |
| `fingerprintAuth` | Fingerabdruck-Animation (Licht und Schatten) | §5 Fingerabdruck-Animation | nein | — | — | — | — | — | — | `lockscreen/auth_fingerprint.mp4` | TODO: Referenz nötig |
| `faceAuth` | Gesichtserkennungs-Animation | §5 Gesichtserkennungs-Animation | nein | — | — | — | — | — | — | `lockscreen/auth_face.mp4` | TODO: Referenz nötig |
| `chargingStart` | Lade-Animation beim Anstecken | §5 Lade-Animation | nein | — | — | — | — | — | — | `lockscreen/charging.mp4` | TODO: Referenz nötig |
| `statusIconChange` | Statusleisten-Icon wechselt | §6 Statusleiste | nein | — | — | — | — | — | — | `statusbar-shade/statusbar_change.mp4` | TODO: Referenz nötig |
| `islandAppear` | Island-Benachrichtigung erscheint | §6 Island-Benachrichtigung – kompakt | nein | — | — | — | — | — | — | `statusbar-shade/island_notification.mp4`, `statusbar-shade/island_multi.mp4` | TODO: Referenz nötig |
| `islandCollapse` | Island expandiert → kompakt | §6 Island-Benachrichtigung – kompakt; §6 Island-Benachrichtigung – expandiert | ja | — | — | — | — | — | — | `statusbar-shade/island_notification.mp4`, `statusbar-shade/island_multi.mp4` | TODO: Referenz nötig |
| `islandDismiss` | Island wegwischen | §6 Island-Benachrichtigung – kompakt | ja | — | — | — | — | — | — | `statusbar-shade/island_notification.mp4`, `statusbar-shade/island_multi.mp4` | TODO: Referenz nötig |
| `islandExpand` | Island kompakt → expandiert | §6 Island-Benachrichtigung – expandiert | ja | — | — | — | — | — | — | `statusbar-shade/island_notification.mp4` | TODO: Referenz nötig |
| `shadeOpen` | Benachrichtigungsleiste öffnen | §6 Benachrichtigungsleiste – Hintergrund und Layout | ja | — | — | — | — | — | — | `statusbar-shade/shade_pull_slow.mp4`, `statusbar-shade/shade_pull_fling.mp4`, `statusbar-shade/shade_zones.mp4` | TODO: Referenz nötig |
| `shadeClose` | Benachrichtigungsleiste schließen | §6 Benachrichtigungsleiste – Hintergrund und Layout | ja | — | — | — | — | — | — | `statusbar-shade/shade_pull_slow.mp4`, `statusbar-shade/shade_pull_fling.mp4`, `statusbar-shade/shade_zones.mp4` | TODO: Referenz nötig |
| `shadeBlurRamp` | Blur-Aufbau über den Zugfortschritt | §6 Benachrichtigungsleiste – Hintergrund und Layout | ja | — | — | — | — | — | — | `statusbar-shade/shade_pull_slow.mp4`, `statusbar-shade/shade_pull_fling.mp4`, `statusbar-shade/shade_zones.mp4` | TODO: Referenz nötig |
| `notificationStackScroll` | Karten-Stapel beim Scrollen | §6 Benachrichtigungskarte | ja | — | — | — | — | — | — | `statusbar-shade/shade_stack.mp4` | TODO: Referenz nötig |
| `notificationDismiss` | Karte wegwischen | §6 Benachrichtigungskarte | ja | — | — | — | — | — | — | `statusbar-shade/shade_swipe.mp4` | TODO: Referenz nötig |
| `notificationSnapBack` | Karte nach halbem Wisch zurück | §6 Benachrichtigungskarte | nein | — | — | — | — | — | — | `statusbar-shade/shade_swipe.mp4` | TODO: Referenz nötig |
| `notificationGroupExpand` | Gruppe auf-/zuklappen | §6 Benachrichtigungsgruppe | nein | — | — | — | — | — | — | `statusbar-shade/shade_stack.mp4` | TODO: Referenz nötig |
| `notificationClearAll` | „Alle löschen“, inkl. Stagger | §6 „Alle löschen“ (Benachrichtigungen) | nein | — | — | — | — | — | — | `statusbar-shade/shade_clear_all.mp4` | TODO: Referenz nötig |
| `controlCenterOpen` | Kontrollzentrum öffnen | §7 Kontrollzentrum – Hintergrund und Layout | ja | — | — | — | — | — | — | `control-center/cc_open_slow.mp4`, `control-center/cc_open_fling.mp4` | TODO: Referenz nötig |
| `controlCenterClose` | Kontrollzentrum schließen | §7 Kontrollzentrum – Hintergrund und Layout | ja | — | — | — | — | — | — | `control-center/cc_open_slow.mp4`, `control-center/cc_open_fling.mp4` | TODO: Referenz nötig |
| `controlCenterStagger` | Verzögerung je Kachel beim Öffnen | §7 Kontrollzentrum – Hintergrund und Layout | nein | — | — | — | — | — | — | `control-center/cc_open_slow.mp4`, `control-center/cc_open_fling.mp4` | TODO: Referenz nötig |
| `controlCenterBlurRamp` | Blur-Aufbau über den Zugfortschritt | §7 Kontrollzentrum – Hintergrund und Layout | ja | — | — | — | — | — | — | `control-center/cc_open_slow.mp4`, `control-center/cc_open_fling.mp4` | TODO: Referenz nötig |
| `tilePress` | Kachel gedrückt (Skala) | §7 Kachel klein; §7 Kachel groß | ja | — | — | — | — | — | — | `control-center/cc_press_hold.mp4` | TODO: Referenz nötig |
| `tileToggle` | Kachel an/aus (Farbe, Skala, Icon-Morphing) | §7 Kachel klein; §7 Kachel groß | nein | — | — | — | — | — | — | `control-center/cc_toggle.mp4` | TODO: Referenz nötig |
| `sliderPress` | Slider gedrückt | §7 Helligkeits-Slider; §7 Lautstärke-Slider; §13 Slider (Einstellungen) | ja | — | — | — | — | — | — | `control-center/cc_brightness.mp4`, `control-center/cc_volume.mp4`, `system-panels/slider.mp4` | TODO: Referenz nötig |
| `sliderDrag` | Slider ziehen (Fingerfolge, Anschlag) | §7 Helligkeits-Slider; §7 Lautstärke-Slider; §13 Slider (Einstellungen) | ja | — | — | — | — | — | — | `control-center/cc_brightness.mp4`, `control-center/cc_volume.mp4`, `system-panels/slider.mp4` | TODO: Referenz nötig |
| `sliderIconMorph` | Slider-Icon-Morphing über den Wert | §7 Helligkeits-Slider; §7 Lautstärke-Slider | ja | — | — | — | — | — | — | `control-center/cc_brightness.mp4`, `control-center/cc_volume.mp4` | TODO: Referenz nötig |
| `mediaCardControl` | Medienkarte: Play/Pause, Fortschritt | §7 Medienkarte | nein | — | — | — | — | — | — | `control-center/cc_media.mp4` | TODO: Referenz nötig |
| `connectivityExpand` | Verbindungskarte erweitern | §7 Verbindungskarte | nein | — | — | — | — | — | — | `control-center/cc_connectivity.mp4` | TODO: Referenz nötig |
| `connectivityCollapse` | Verbindungskarte schließen | §7 Verbindungskarte | nein | — | — | — | — | — | — | `control-center/cc_connectivity.mp4` | TODO: Referenz nötig |
| `devicePanelOpen` | Geräte-Panel öffnen | §7 Geräte-Panel | nein | — | — | — | — | — | — | `control-center/cc_devices.mp4` | TODO: Referenz nötig |
| `ccEditEnter` | Bearbeiten-Modus betreten | §7 Bearbeiten-Modus | nein | — | — | — | — | — | — | `control-center/cc_edit.mp4` | TODO: Referenz nötig |
| `ccEditDrag` | Kachel ziehen | §7 Bearbeiten-Modus | ja | — | — | — | — | — | — | `control-center/cc_edit.mp4` | TODO: Referenz nötig |
| `ccEditResize` | Kachel skalieren | §7 Bearbeiten-Modus | ja | — | — | — | — | — | — | `control-center/cc_edit.mp4` | TODO: Referenz nötig |
| `ccEditReflow` | Übrige Kacheln weichen aus | §7 Bearbeiten-Modus | nein | — | — | — | — | — | — | `control-center/cc_edit.mp4` | TODO: Referenz nötig |
| `homePageSettle` | Seitenwechsel nach Loslassen | §8 Seitenwechsel und Seitenindikator | ja | — | — | — | — | — | — | `home/home_page_swipe.mp4` | TODO: Referenz nötig |
| `homePageRubberBand` | Rubber-Band an der ersten/letzten Seite | §8 Seitenwechsel und Seitenindikator | ja | — | — | — | — | — | — | `home/home_page_swipe.mp4` | TODO: Referenz nötig |
| `iconPress` | Icon gedrückt | §8 App-Icon | ja | — | — | — | — | — | — | `app-transitions/launch_icon.mp4`, `home/flux_resize.mp4` | TODO: Referenz nötig |
| `fluxResize` | Icon/Ordner wechselt die Form | §8 Flux-Home-Formen (Icon-Resize); §8 Ordner-Icon | ja | — | — | — | — | — | — | `home/flux_resize.mp4` | TODO: Referenz nötig |
| `fluxReflow` | Übrige Elemente weichen live aus | §8 Flux-Home-Formen (Icon-Resize) | nein | — | — | — | — | — | — | `home/flux_resize.mp4` | TODO: Referenz nötig |
| `folderExpand` | Ordner öffnen aus Ordner-Icon | §8 Ordner offen | nein | — | — | — | — | — | — | `home/folder_open_close.mp4` | TODO: Referenz nötig |
| `folderCollapse` | Ordner schließen ins Ordner-Icon | §8 Ordner offen | nein | — | — | — | — | — | — | `home/folder_open_close.mp4` | TODO: Referenz nötig |
| `folderPageSettle` | Seitenwechsel im Ordner | §8 Ordner offen | ja | — | — | — | — | — | — | `home/folder_pages.mp4` | TODO: Referenz nötig |
| `editModeEnter` | Wackelmodus betreten | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `jiggle` | Wackeln (Amplitude, Periode) | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `iconDragLift` | Icon anheben | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `iconDrop` | Icon ablegen | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `folderCreate` | Icon über Icon → Ordner-Vorschau | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `dragPageEdge` | Seitenwechsel beim Ziehen an den Rand | §8 Wackelmodus und Drag-and-Drop | nein | — | — | — | — | — | — | `home/jiggle_drag.mp4` | TODO: Referenz nötig |
| `widgetAdd` | Widget platzieren | §8 Widget | nein | — | — | — | — | — | — | `home/widget_add_resize.mp4` | TODO: Referenz nötig |
| `widgetResize` | Widget skalieren | §8 Widget | ja | — | — | — | — | — | — | `home/widget_add_resize.mp4` | TODO: Referenz nötig |
| `searchOpen` | Suche öffnen | §8 Suchleiste; §9 Suche | nein | — | — | — | — | — | — | `drawer-search/search.mp4` | TODO: Referenz nötig |
| `contourGlowLoop` | Contour-Glow-Schimmer (Periode, Verlauf) | §8 Contour-Glow | nein | — | — | — | — | — | — | `home/contour_glow.mp4` | TODO: Referenz nötig |
| `drawerOpen` | Schublade öffnen | §9 App-Schublade | ja | — | — | — | — | — | — | `drawer-search/drawer_open.mp4` | TODO: Referenz nötig |
| `drawerClose` | Schublade schließen | §9 App-Schublade | ja | — | — | — | — | — | — | `drawer-search/drawer_open.mp4` | TODO: Referenz nötig |
| `alphabetScrub` | Alphabetleiste überstreichen | §9 Alphabetleiste und Kategorien | ja | — | — | — | — | — | — | `drawer-search/drawer_alphabet.mp4` | TODO: Referenz nötig |
| `searchResultsEnter` | Suchergebnisse erscheinen | §9 Suche | nein | — | — | — | — | — | — | `drawer-search/search.mp4` | TODO: Referenz nötig |
| `recentsEnter` | App → Recents | §10 Recents-Übersicht | ja | — | — | — | — | — | — | `recents/recents_enter.mp4` | TODO: Referenz nötig |
| `recentsExit` | Recents → App/Home | §10 Recents-Übersicht | ja | — | — | — | — | — | — | `recents/recents_enter.mp4` | TODO: Referenz nötig |
| `recentsCardDismiss` | Recents-Karte wegwischen | §10 Recents-Karte | ja | — | — | — | — | — | — | `recents/recents_dismiss.mp4` | TODO: Referenz nötig |
| `recentsCardSnapBack` | Recents-Karte nach halbem Wisch zurück | §10 Recents-Karte | nein | — | — | — | — | — | — | `recents/recents_dismiss.mp4` | TODO: Referenz nötig |
| `quickSwitch` | Schnellwechsel entlang der unteren Kante | §10 Recents-Karte | ja | — | — | — | — | — | — | `recents/recents_quick_switch.mp4` | TODO: Referenz nötig |
| `recentsClearAll` | „Alle löschen“, inkl. Stagger | §10 „Alle löschen“ (Recents) | nein | — | — | — | — | — | — | `recents/recents_clear_all.mp4` | TODO: Referenz nötig |
| `homeGesture` | Home-Geste aus einer App | §11 Gesten-Navigationsleiste | ja | — | — | — | — | — | — | `nav/home_gesture.mp4` | TODO: Referenz nötig |
| `navCapsuleTransition` | Wechsel zwischen Leistenvarianten | §11 Schwebende Kapsel-Navigation | nein | — | — | — | — | — | — | `nav/capsule_transition.mp4` | TODO: Referenz nötig |
| `predictiveBackDrag` | Zurück-Vorschau während der Geste | §11 Predictive Back | ja | — | — | — | — | — | — | `nav/back_preview.mp4` | TODO: Referenz nötig |
| `backCommit` | Zurück ausgeführt | §11 Predictive Back | nein | — | — | — | — | — | — | `nav/back_preview.mp4` | TODO: Referenz nötig |
| `backCancel` | Zurück abgebrochen | §11 Predictive Back | nein | — | — | — | — | — | — | `nav/back_preview.mp4` | TODO: Referenz nötig |
| `appLaunch` | App öffnen aus Icon | §12 App-Start aus Icon | nein | — | — | — | — | — | — | `app-transitions/launch_icon.mp4`, `app-transitions/launch_interrupt.mp4` | TODO: Referenz nötig |
| `appClose` | App schließen ins Icon | §12 App-Rückkehr ins Icon | ja | — | — | — | — | — | — | `app-transitions/close_icon.mp4`, `app-transitions/launch_interrupt.mp4` | TODO: Referenz nötig |
| `appLaunchFromFolder` | App öffnen aus Ordner | §12 App-Start und -Rückkehr aus Ordner | nein | — | — | — | — | — | — | `app-transitions/launch_folder.mp4` | TODO: Referenz nötig |
| `appCloseToFolder` | App schließen in den Ordner | §12 App-Start und -Rückkehr aus Ordner | ja | — | — | — | — | — | — | `app-transitions/launch_folder.mp4` | TODO: Referenz nötig |
| `appLaunchFromRecents` | App öffnen aus Recents | §12 App-Start aus Recents | nein | — | — | — | — | — | — | `app-transitions/launch_recents.mp4` | TODO: Referenz nötig |
| `volumeEnter` | Lautstärke-Panel erscheint | §13 Lautstärke-Panel | nein | — | — | — | — | — | — | `system-panels/volume.mp4` | TODO: Referenz nötig |
| `volumeExpand` | Lautstärke-Panel ausklappen | §13 Lautstärke-Panel | nein | — | — | — | — | — | — | `system-panels/volume.mp4` | TODO: Referenz nötig |
| `volumeExit` | Lautstärke-Panel verschwindet | §13 Lautstärke-Panel | nein | — | — | — | — | — | — | `system-panels/volume.mp4` | TODO: Referenz nötig |
| `powerMenuEnter` | Ausschalt-Menü erscheint | §13 Ausschalt-Menü | nein | — | — | — | — | — | — | `system-panels/power_menu.mp4` | TODO: Referenz nötig |
| `powerMenuExit` | Ausschalt-Menü verschwindet | §13 Ausschalt-Menü | nein | — | — | — | — | — | — | `system-panels/power_menu.mp4` | TODO: Referenz nötig |
| `dialogEnter` | Dialog erscheint | §13 Dialog | nein | — | — | — | — | — | — | `system-panels/dialog.mp4` | TODO: Referenz nötig |
| `dialogExit` | Dialog verschwindet | §13 Dialog | nein | — | — | — | — | — | — | `system-panels/dialog.mp4` | TODO: Referenz nötig |
| `sheetEnter` | Bottom-Sheet erscheint | §13 Bottom-Sheet | nein | — | — | — | — | — | — | `system-panels/sheet.mp4` | TODO: Referenz nötig |
| `sheetDrag` | Bottom-Sheet ziehen, inkl. Nested Scroll | §13 Bottom-Sheet | ja | — | — | — | — | — | — | `system-panels/sheet.mp4` | TODO: Referenz nötig |
| `sheetExit` | Bottom-Sheet verschwindet | §13 Bottom-Sheet | ja | — | — | — | — | — | — | `system-panels/sheet.mp4` | TODO: Referenz nötig |
| `switchToggle` | Switch an/aus | §13 Switch | ja | — | — | — | — | — | — | `system-panels/switch.mp4` | TODO: Referenz nötig |
| `togglePress` | Toggle-Button gedrückt/gewählt | §13 Toggle-Button | nein | — | — | — | — | — | — | — | TODO: Referenz nötig |
| `listOverscroll` | Überscrollen in Listen | §13 Einstellungsliste | ja | — | — | — | — | — | — | `system-panels/settings_scroll.mp4` | TODO: Referenz nötig |
| `largeTitleCollapse` | Großer Titel klappt beim Scrollen ein | §13 Einstellungsliste | ja | — | — | — | — | — | — | `system-panels/settings_scroll.mp4` | TODO: Referenz nötig |

## Gesten-Parameter

| Parameter | Wert | Quelle | Status |
|---|---|---|---|
| `unlockDistanceThreshold` (Anteil Höhe) | — | `lockscreen/unlock_slow.mp4` | TODO: Referenz nötig |
| `unlockFlingVelocity` (dp/s) | — | `lockscreen/unlock_fling.mp4` | TODO: Referenz nötig |
| `rubberBandLock` (Widerstand über Strecke) | — | `lockscreen/unlock_slow.mp4` | TODO: Referenz nötig |
| `rubberBandHomeEdge` | — | `home/home_page_swipe.mp4` | TODO: Referenz nötig |
| `rubberBandOverscroll` (Listen, Sheets) | — | `system-panels/settings_scroll.mp4`, `system-panels/sheet.mp4` | TODO: Referenz nötig |
| `shadeZoneSplit` (x-Grenze Benachrichtigungen / CC) | — | `statusbar-shade/shade_zones.mp4` | TODO: Referenz nötig |
| `pullDownFollowFactor` (Finger → Inhalt) | — | `statusbar-shade/shade_pull_slow.mp4`, `control-center/cc_open_slow.mp4` | TODO: Referenz nötig |
| `pageSwipeThreshold` (Distanz / Fling) | — | `home/home_page_swipe.mp4` | TODO: Referenz nötig |
| `drawerSwipeThreshold` | — | `drawer-search/drawer_open.mp4` | TODO: Referenz nötig |
| `recentsTrigger` (Pause / Geschwindigkeit) | — | `recents/recents_enter.mp4` | TODO: Referenz nötig |
| `backEdgeWidth`, `backPreviewScale` | — | `nav/back_preview.mp4` | TODO: Referenz nötig |
| `islandExpandDrag` (Strecke bis Vollbild) | — | `lockscreen/island_expand.mp4` | TODO: Referenz nötig |
| `longPressTimeout` (Shortcuts, Kacheln, Icons) | — | `lockscreen/shortcuts.mp4`, `control-center/cc_press_hold.mp4`, `home/flux_resize.mp4` | TODO: Referenz nötig |

## Reduce Motion

Das Verhalten bei „Animationen entfernen“ bzw. Animator-Skala 0 ist noch offen: TODO: Referenz nötig (`system-panels/reduce_motion.mp4`). Kommt dazu keine Referenz, schlage ich ein Verhalten vor und kennzeichne es als Entwurf, nicht als gemessen.
