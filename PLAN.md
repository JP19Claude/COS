# PLAN

Ziel: ein eigenes Android-Projekt (Kotlin, Jetpack Compose), das Gestaltung und Bewegung des Originals so genau wie möglich nachbildet. Es nutzt keine Original-Assets und trägt keine Marken. Alle Maße kommen aus [`DESIGN_SPEC.md`](DESIGN_SPEC.md), das Referenzmaterial liegt in [`reference/`](reference/README.md). Was technisch nicht geht, steht in [`LIMITATIONS.md`](LIMITATIONS.md).

## Stand und Blocker (2026-10-23)

| # | Blocker | Folge | Was hilft |
|---|---|---|---|
| 1 | `reference/` ist leer | Keine Messwerte. `DESIGN_SPEC.md` ist ein Skelett, alle Werte `TODO: Referenz nötig` | Material laut [`reference/README.md`](reference/README.md) |
| 2 | Gerät und OS-Version unbekannt | Layout und Animationen unterscheiden sich zwischen Versionen stark | Modell, Version, `adb shell wm size`, `adb shell wm density` |
| 3 | Netzwerk-Richtlinie dieser Cloud-Umgebung sperrt `dl.google.com` | Android SDK nicht installierbar, hier kein APK-Build. `maven.google.com` und Maven Central sind erreichbar | `dl.google.com` in den Umgebungs-Einstellungen unter *Network access → Allowed domains* freigeben. Alternative: Builds nur über GitHub Actions (langsamer) |
| 4 | Kein KVM im Container | Kein Emulator hier | Screenshots per Roborazzi (Robolectric, läuft auf der JVM). Performance, Haptik und echte Gesten misst du auf dem Gerät mit Skripten, die ich liefere |

## Architektur

```
:app              Einstieg: HOME-Launcher-Activity und Demo-Shell (Fake-Lockscreen, Fake-Statusleiste, Overlays)
:design-system    Tokens (Farben, Typo, Radien, Schatten, Blur, Motion, Haptik), Theme, Basis-Komponenten, Icons
:common           Gesten-Physik (Rubber-Band, Snap/Fling, Velocity-Übergabe), Systemdaten (Zeit, Akku, Netz), Utilities
:lockscreen       Lockscreen, Entsperr-Ablauf, PIN/Muster, AOD-Optik
:control-center   Kontrollzentrum, Benachrichtigungsleiste, Statusleiste
:launcher         Homescreen, Ordner, Schublade, Dock, Widgets, Drag-and-Drop
:recents          Multitasking-Ansicht und App-Übergänge
:benchmark        Macrobenchmark (läuft nur auf einem Gerät)
tools/            Python: Frame-Extraktion, Kurven-Fit, Screenshot-Diff, Kalibrier-Wallpaper
```

Abhängigkeiten: Feature-Module → `:design-system`, `:common`. `:app` → alle. Feature-Module kennen einander nicht und kommunizieren über Interfaces in `:common`.

### Technische Leitlinien

- **Tokens nur aus der Spec.** `design-system/tokens/*` wird aus `DESIGN_SPEC.md` befüllt. Es gibt keine Zahlenliterale für Maße oder Dauern in Feature-Code.
- **Ein Gestenmodell für alles.** Beim Ziehen wird `Animatable.snapTo()` im Pointer-Loop aufgerufen, gelesen wird nur in `graphicsLayer {}`. So gibt es keine Recomposition pro Frame. Beim Loslassen läuft `animateTo()` mit `initialVelocity` aus dem `VelocityTracker`. Ein neuer Finger stoppt die laufende Animation und übernimmt die aktuelle Position. Damit ist jede Animation unterbrechbar.
- **Gesten-Entscheidungen als reines Kotlin.** Schwellen, Rubber-Band und Snap-Ziel sind Funktionen ohne Android-Abhängigkeit und werden per JVM-Unit-Test geprüft.
- **Blur:** Ab API 31 `RenderEffect` über `graphicsLayer`. Im In-App-Nachbau gehört das Wallpaper uns. Deshalb sind für API 29–30 neben der vorgeschriebenen halbtransparenten Fläche auch vorberechnete, herunterskalierte Blur-Stufen möglich, die überblendet werden. Ob das gewünscht ist, entscheidest du.
- **Screenshot-Vergleich:** Roborazzi rendert jeden Screen in fester Gerätegröße und Dichte (aus der Meta-Tabelle der Spec). `tools/diff.py` legt App und Referenz übereinander (Overlay, Differenzbild). Für Abweichungen in dp nutzt es Element-Rahmen: App-seitig exakt aus Compose-`testTag`-Bounds, referenzseitig einmal pro Screen in einer JSON-Datei annotiert. Zielwert ist < 2 dp auf den Hauptscreens.
- **Performance:** Macrobenchmark mit `FrameTimingMetric` je Geste plus Perfetto-Trace-Konfiguration. Beides führst du auf dem Gerät aus, die Auswertung mache ich.

## Phasen

Aufwand in Personentagen (PT) für eine erfahrene Person. Das sind grobe Bandbreiten, die genauer werden, sobald die Referenz vorliegt. Jede Phase endet mit einer lauffähigen Debug-APK, Screenshots, einem Diff-Report, der Liste offener `TODO: Referenz nötig`-Punkte und einem Commit.

### Phase 0 – Projekt und Mess-Werkzeuge (1–2 PT)
- Gradle-Multi-Modul-Projekt, Version Catalog, Min SDK 29, Target aktuelles SDK, GitHub-Actions-Build mit APK als Artefakt.
- `tools/`: Frame-Extraktion, Element-Tracking, Bezier-/Feder-Fit, Diff-Skript, Kalibrier-Wallpaper (schon da).
- **Abnahme:** Leere App baut, ein Roborazzi-Screenshot läuft durch, CI liefert eine APK.
- **Risiko:** Ohne Blocker 3 sind Builds nur über CI möglich.

### Phase A – Design-System und Motion-Tokens (5–8 PT)
- Farben hell/dunkel, dynamische Akzentfarbe, Typo-Skala, Radien (inkl. Superellipse-Shape, falls gemessen), Schatten, Blur-Modifier mit Fallback, Haptik-Profile, Motion-Tokens.
- Komponenten: Switch, Slider, Button, Karte, Listeneintrag, Dialog, Bottom-Sheet, Overscroll.
- Selbst gezeichnete System-Icons (Statusleiste, CC, Navigation; ca. 40–60 Stück), App-Icon-Maske für fremde Adaptive Icons.
- Gesten-Physik in `:common` mit Unit-Tests. Katalog-Screen mit allen Komponenten und Previews.
- **Abnahme:** Katalog-Screen, Diff von Switch, Slider und Einstellungsliste gegen die Referenz.
- **Risiko:** Die Platzhalter-Schrift hat andere Metriken als die Original-Schrift. Bei Text ist < 2 dp deshalb ggf. nicht erreichbar. Gegenmaßnahme: Schrift nach x-Höhe und Laufweite auswählen. Du kannst eine selbst lizenzierte Schrift in die Theme-Datei legen.

### Phase B – Lockscreen, In-App (8–12 PT)
- Uhr-Varianten, Datum, Infozeile, Hoch-/Querformat.
- Wallpaper-Parallax. Tiefeneffekt „Uhr hinter Motiv“ per ML Kit Subject Segmentation (braucht Google Play-Dienste auf dem Gerät), Fallback: manuell erstellte Maske.
- Benachrichtigungen (zunächst Demo-Daten): Gruppierung, Karten, Ein-/Ausblenden, Inhalte verbergen.
- Entsperren: Wisch mit Fingerfolge, Rubber-Band, Wallpaper-Zoom/Blur zum Home (bis Phase D ein Platzhalter-Home). PIN- und Muster-Ansicht, Biometrie-Optik.
- Shortcuts unten mit Long-Press-Animation, AOD-Optik.
- **Risiko:** Qualität der Segmentierung. Echte Biometrie zeigt immer die System-Oberfläche (siehe LIMITATIONS).

### Phase C – Kontrollzentrum, Benachrichtigungsleiste, Statusleiste (10–15 PT)
- Fake-Statusleiste mit echten Daten (Zeit, Akku, Netz), Animationen bei Änderungen.
- Pull-Down links/rechts mit Fingerfolge und Blur-Aufbau.
- Benachrichtigungen: Gruppen, Aufklappen, Wisch-zum-Schließen. Echte Daten per `NotificationListenerService`, wenn erlaubt.
- CC: Kachelraster, An/Aus-Animation, Stagger, Slider mit Icon-Morphing, Medienkarte (echt per `MediaSessionManager`), Verbindungskarte mit Long-Press, Bearbeiten-Modus mit Drag-and-Drop und Reflow.
- Echte Schalter, wo die API es erlaubt (Taschenlampe, Nicht stören, Helligkeit, Lautstärke, Rotation). Alles andere öffnet das System-Panel (siehe LIMITATIONS).
- **Risiko:** Compose hat kein fertiges sortierbares Raster, Reflow wird selbst gebaut. Mehrere Blur-Ebenen gleichzeitig kosten Leistung bei 120 Hz.

### Phase D – Launcher, Ordner, Schublade (20–30 PT)
- Echter HOME-Launcher: `LauncherApps`, Icon-Rendering mit Maske, Seitenraster, Seitenwechsel, Indikator, Layout-Speicherung.
- Drag-and-Drop über Seiten, Dock und Ordner, Wackel-Modus, App-Shortcuts-Menü, Benachrichtigungspunkte.
- Ordner: Öffnen/Schließen als gemeinsamer Bounds-Übergang, Vorschau-Icons, Umbenennen, Seiten.
- Schublade: Wisch-nach-oben, Suche, Kategorien, Buchstaben-Leiste.
- Dock mit Blur, Widgets (`AppWidgetHost`, Binden, Konfigurieren, Skalieren).
- App-Start/-Rückkehr für fremde Apps nur mit den Mitteln, die ein Drittanbieter-Launcher hat (siehe LIMITATIONS).
- **Risiko:** Die größte Phase. Widgets sind klassische Views innerhalb von Compose und müssen trotzdem flüssig ziehbar sein. Das Gerät schränkt Drittanbieter-Launcher bei Gestennavigation eventuell ein, das muss auf dem Gerät geprüft werden.

### Phase E – Recents und Übergänge (8–12 PT)
- Kartenansicht, Wisch-zum-Schließen, „Alle löschen“, Kartenskalierung von/zur App, Schnellwechsel.
- Damit die Übergänge 1:1 vorführbar sind, enthält die Demo-Shell 3–4 einfache Demo-Apps. Bei echten fremden Apps sind die Übergänge nicht steuerbar.
- Zurück-Geste mit Vorschau (Predictive Back) innerhalb der Shell.

### Phase F – Weitere Oberflächen, Feinschliff, Performance, Pixelvergleich (8–12 PT, Faltbar/Tablet optional +3–5 PT)
- Lautstärke-Panel, Ausschalt-Menü, Einstellungs-Optik. Dazu ein Durchgang für Dark Mode, Schriftgrößen-Skalierung und Rotation.
- Macrobenchmark und Perfetto auf dem Gerät, Überzeichnung beheben.
- Abschließender Diff-Report über alle Hauptscreens. `LIMITATIONS.md` final.

### Phase G (optional) – Echte Systemintegration (10–20 PT, hohes Risiko)
- Prototypen laut LIMITATIONS: Overlay-Lockscreen, Pull-Down per Accessibility-Overlay, Lautstärke-Panel per Accessibility, `TileService`.
- LSPosed oder eigenes ROM nur auf ausdrücklichen Wunsch.

**Summe A–F: ca. 60–90 PT.** „1:1“ ist für den In-App-Nachbau realistisch. Für eine echte Systemintegration ohne Root ist es das nicht.

## Übergreifende Risiken

| Risiko | Wirkung | Gegenmaßnahme |
|---|---|---|
| Lückenhafte oder komprimierte Referenz | Werte bleiben TODO oder werden ungenau | Checkliste und Aufnahme-Regeln in `reference/README.md`, nachfragen statt raten |
| Versionswechsel des Originals | Spec veraltet | Version in der Meta-Tabelle festhalten, Referenz nur von einer Version |
| Herstellerspezifischer Blur (Sättigung, Rauschen, Tönung) | Material wirkt anders | Mit Kalibrier-Wallpaper messen, `RenderEffect`-Kette aus Blur und Farbmatrix |
| Leistung mehrerer Blur-Ebenen | Ruckeln bei 120 Hz | Einmal blurren und teilen, herunterskalierte Ebenen, Messung per Macrobenchmark |
| Kein Gerät im Container | Performance und Haptik hier nicht prüfbar | Skripte zum Ausführen auf deinem Gerät, du schickst die Ergebnisse |
| Rechtliches | Original-UI ist geschützt | Keine Assets, keine Marken. Referenzmaterial nur privat (Repo privat halten), nie in die APK |

## Was ich als Nächstes brauche

1. **Referenzmaterial** nach [`reference/README.md`](reference/README.md). Für Phase A genügt das „Minimal-Set A“.
2. **Gerät und Version** plus `adb shell wm size` / `adb shell wm density` (falls adb vorhanden, sonst Auflösung aus den Geräteinfos).
3. **Netzwerk:** `dl.google.com` freigeben oder bestätigen, dass Builds nur über GitHub Actions laufen sollen.
4. **Arbeitstitel und Paketname** der App, neutral. Vorschlag: Arbeitstitel „Shell“, Paket `app.shell.cos`. Beides ist später änderbar.
5. **Entscheidung zu Phase G / LSPosed:** Jetzt nicht nötig, spätestens vor Phase F.
