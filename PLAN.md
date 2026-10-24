# PLAN

Ziel: Ein eigenes Android-Projekt (Kotlin, Jetpack Compose), das Aussehen, Layout, Bewegung und Haptik von ColorOS 17 so genau wie möglich nachbildet. Es nutzt keine Original-Assets und trägt keine Marken.

Alle Werte stehen in [`DESIGN_SPEC.md`](DESIGN_SPEC.md) und [`MOTION_TOKENS.md`](MOTION_TOKENS.md), das Referenzmaterial in [`reference/`](reference/INDEX.md). Was ohne Systemrechte nicht geht, steht in [`LIMITATIONS.md`](LIMITATIONS.md).

## Stand und Blocker (2026-10-23, Ende Phase 0)

| # | Blocker | Folge | Was hilft |
|---|---|---|---|
| 1 | `reference/` ist leer (0 von 109 Einträgen, gut 120 Dateien) | Keine Messwerte. Alle Gerüste stehen auf `TODO: Referenz nötig` | Öffentliches Material laut [`CHECKLISTE.md`](CHECKLISTE.md) und [`reference/QUELLEN.md`](reference/QUELLEN.md), zuerst das ★-Set (25 Dateien) |
| 2 | Kein ColorOS-17-Gerät vorhanden (Entscheidung 2026-10-23: öffentliches Material) | Unbekannte Displaydichte und Kompression, Gesten ohne sichtbaren Finger, keine Haptik, keine Kalibrier-Aufnahmen | Proportionale Messung mit einer geschätzten Bezugsbreite (`DESIGN_SPEC.md` §1a). Jeder Wert wird als `[öffentlich ±u]` oder `[geschätzt]` markiert |
| 3 | Die Netzwerk-Richtlinie dieser Cloud-Umgebung sperrt `dl.google.com`. Erreichbar sind `maven.google.com` und Maven Central | Android SDK nicht installierbar, hier kein APK-Build | `dl.google.com` unter *Network access → Allowed domains* freigeben. Sonst Builds nur über GitHub Actions |
| 4 | Kein KVM im Container | Kein Emulator hier | Screenshots und Frame-Strips per Roborazzi (JVM). Emulator-Läufe eventuell in GitHub Actions ⚠. Performance und Haptik misst du auf den Testgeräten |

**Stand der App (2026-10-24): v0.1 Grundgerüst** auf Wunsch vorgezogen.
- **Module:** `:app`, `:design-system`, `:glass` und `:common`.
- **Galerie:** Glas-Panel mit Backdrop-Blur, runde Kacheln, Slider mit Rubber-Band, Schalter und Wallpaper-Auswahl.
- **Werte:** Alle Werte sind Platzhalter (`@Unmeasured`), es gibt noch keine Referenz.
- **Build:** über GitHub Actions (`.github/workflows/android.yml`), weil das SDK hier nicht installierbar ist.
- **SDK-Version:** `compileSdk`/`targetSdk` 35 mit AGP 8.7.3. Hier lassen sich keine neueren Versionen prüfen. Hochziehen auf das aktuelle SDK folgt, sobald ein lokaler Build möglich ist.

**Testgeräte:** Samsung Galaxy S22 Ultra und Galaxy S25 FE. Beide liegen über Min SDK 29. Mit Android 12 und neuer läuft dort echter Blur, mit Android 13 und neuer auch AGSL. Sie dienen für Installation, Performance, Gesten und Haptik. Als Design-Referenz dienen sie nicht. Herstellerabhängige ⚠-Punkte aus `LIMITATIONS.md` werden auf One UI geprüft, nicht auf ColorOS.

## Architektur

```
:glass            Backdrop-Blur, Tint, Sättigung, Rauschen, Kantenlicht, Contour-Glow; Presets aus DESIGN_SPEC §4.6
:design-system    Theme, Farbrollen, Typo, Radien, Spacing, Schatten, Icons, Haptik, MotionTokens.kt;
                  Komponenten: Switch, Toggle, Slider, Dialog, Bottom-Sheet, Liste, Island-Grundform
:common           Gestenphysik (Rubber-Band, Snap/Fling, Velocity-Übergabe), gemeinsame Übergangszustände
                  (z. B. Wallpaper-Fortschritt AOD→Lock→Home), Systemdaten (Zeit, Akku, Netz)
:lockscreen       AOD, Lockscreen, Uhr-Stile, Live Clock, Widgets, Lockscreen Island, Entsperren, Authentifizierung
:shade            Statusleiste, Island-Benachrichtigungen, Benachrichtigungsleiste, Lautstärke-Panel, Ausschalt-Menü
:control-center   Kachelraster, Slider, Medien-, Verbindungs-, Geräte-Panel, Bearbeiten-Modus
:launcher         Homescreen, Flux Home, Ordner, Widgets, Dock, Suchleiste, Schublade, Suche, App-Start
:recents          Multitasking, Karten, Übergänge von/zur App
:nav              Gesten-Leiste, Kapsel-Navigation, Predictive Back
:app              Einstieg: HOME-Activity, Demo-Shell (simulierte Statusleiste und Systemdaten), Demo-Galerie, Demo-Apps
:benchmark        Macrobenchmark (läuft auf dem Gerät)
tools/            Python: Frame-Extraktion, Frame-Strips, Kurven-Fit, Pixel-/ΔE-Diff, Motion-Vergleich,
                  Token-Generator (MOTION_TOKENS.md → MotionTokens.kt)
```

`:app` und `:benchmark` kommen zu den Modulen aus dem Prompt hinzu. Ohne `:app` gibt es keine APK, und ohne `:benchmark` lässt sich die geforderte Macrobenchmark-Messung nicht durchführen.

**Abhängigkeiten:** `:glass` ← `:design-system` ← Feature-Module. `:common` steht allen offen. Feature-Module kennen einander nicht. Gemeinsame Zustände wie der durchgehende Wallpaper-Übergang liegen in `:common`. Die Island-Grundform liegt in `:design-system`, weil Lockscreen und Benachrichtigungsleiste sie beide brauchen.

## Technische Leitlinien

- **Keine Magic Numbers.** `tools/` erzeugt `MotionTokens.kt` und die Design-Tokens aus den Markdown-Tabellen. Jedes Token trägt Quelle und Fit-Fehler als KDoc. Ungemessene Tokens heißen im Code sichtbar `Unmeasured` und stehen im Phasenbericht, damit nichts als gemessen durchgeht.
- **Glas in drei Stufen:**
  - **API 33+:** Blur plus AGSL-`RuntimeShader` für Kantenlicht, Refraktion, Contour-Glow und Reflexe.
  - **API 31–32:** Blur per `RenderEffect`, Kantenlicht als statische Linie.
  - **API 29–30:** halbtransparente Fläche plus feine Kantenlinie, jeweils mit gleicher Geometrie.
  - In Phase A wird **Haze** (Version 2.0.1, Apache-2.0, auf Maven Central) gegen eine eigene `RenderEffect`-Kette verglichen. Kriterien: Kosten pro Frame bei mehreren Ebenen, Kontrolle über Sättigung, Rauschen und Tint, sowie Zusammenspiel mit AGSL. Die Entscheidung landet in diesem Plan, die Lizenz in `LICENSES.md`.
- **Ein Gestenmodell für alles:**
  - Beim Ziehen wird `Animatable.snapTo()` im Pointer-Loop aufgerufen, gelesen wird nur in `graphicsLayer {}`. So gibt es keine Recomposition pro Frame.
  - Beim Loslassen läuft `animateTo()` mit der Geschwindigkeit aus dem `VelocityTracker`. Ein neuer Finger stoppt die laufende Animation und übernimmt sie ohne Sprung.
  - Schwellen, Rubber-Band und Snap-Ziele sind reines Kotlin mit JVM-Unit-Tests.
- **Durchgehender Wallpaper-Übergang:** Eine gemeinsame Fortschrittsvariable steuert AOD → Lockscreen → Homescreen (Skalierung, Blur, Helligkeit, Position). Lockscreen und Launcher lesen sie beide, so bleibt es eine kontinuierliche Animation.
- **Wallpaper (Entscheidung 2026-10-24):**
  - Gewünscht ist das ColorOS-17-Wallpaper. Es ist proprietär und kommt deshalb nicht ins Projekt und nicht in die APK (Regel 1).
  - Die App bringt einen eigenen, neutralen Platzhalter mit.
  - In der App lässt sich jedes Bild aus der Galerie als Wallpaper wählen (Photo Picker, ohne Berechtigung). Das ColorOS-17-Wallpaper speicherst du selbst aufs Handy und wählst es dort aus.
  - Die App behält eine Kopie, damit Glas, Live Clock und Tiefeneffekt damit funktionieren. Auf Wunsch setzt sie es auch als System-Wallpaper.
- **Pixelvergleich (`DIFF_REPORT.md`):**
  - Roborazzi rendert jeden Zustand in einer Größe, die zur Bildbreite des Referenzbilds und zur Bezugsbreite `W_dp` passt.
  - App-seitig kommen die Element-Rahmen exakt aus Compose-`testTag`-Bounds, referenzseitig werden sie einmal pro Screen in JSON annotiert.
  - Der Report nennt Abweichungen in dp und ΔE (CIEDE2000) außerhalb von Blur-Flächen. Ziele: < 2 dp, ΔE < 3.
  - Gemessen wird gegen öffentliches Material. Jede Abweichung steht deshalb neben der Mess-Unsicherheit der Quelle. Liegt die Unsicherheit über dem Ziel, heißt das Ergebnis „nicht prüfbar“ und nicht „bestanden“.
- **Animationsvergleich (`MOTION_REPORT.md`):**
  - Die Compose-Testuhr wird in Schritten von 1/fps weitergeschaltet. Das liefert deterministische Frame-Strips der App.
  - Gesten werden mit einer Fingerbahn nachgespielt. Weil öffentliche Videos den Finger nicht zeigen, ist sie meist `[geschätzt]`. Verglichen wird dann nur die Bewegung nach dem Loslassen.
  - Ziele: Fehler < 3 % des Wegs, Dauer < 1 Frame Abweichung (Frame des Quellvideos).
  - ⚠ Robolectric rendert Blur und AGSL eventuell nicht wie ein Gerät. Glasflächen werden deshalb zusätzlich mit Aufnahmen von den Testgeräten verglichen.
- **Performance (`PERF.md`):** Macrobenchmark mit `FrameTimingMetric` je Geste plus Perfetto-Konfiguration. Ausgeführt wird auf dem S22 Ultra und dem S25 FE, die Auswertung mache ich. Blur-Flächen werden minimiert und gecacht.
- **Barrierefreiheit:** TalkBack-Beschriftungen, Touch-Ziele von mindestens 48 dp, Kontrastprüfung im Diff-Skript. Reduce Motion folgt der System-Animator-Skala, das Verhalten laut `MOTION_TOKENS.md` (offen).

## Phasen

Aufwand in Personentagen (PT) für eine erfahrene Person. Das sind grobe Bandbreiten, die genauer werden, sobald Referenz vorliegt. Jede Phase endet mit:
- lauffähiger Debug-APK und Screenshots,
- `DIFF_REPORT.md` und `MOTION_REPORT.md`,
- Liste offener `TODO: Referenz nötig`,
- Commit und kurzem Bericht.

### Phase 0 – Gerüst (erledigt)
`reference/INDEX.md` mit Lückenliste pro Ordner, Gerüste für `DESIGN_SPEC.md` (70 Komponenten, je Hell/Dunkel) und `MOTION_TOKENS.md` (104 Tokens, 13 Gesten-Parameter), `PLAN.md`, Entwurf von `LIMITATIONS.md`, Kalibrier-Wallpaper. **Stopp und Rückfrage.**

### Phase A – `:glass` und `:design-system` (10–15 PT)
- Gradle-Multi-Modul-Projekt mit Version Catalog, GitHub-Actions-Build mit APK als Artefakt.
- `tools/`: Frame-Strips, Fit, Diff, Token-Generator.
- Theme, Farbrollen, dynamischer Akzent aus dem Wallpaper, Typo-Skala inkl. Skalierung, Radien und Squircle-Shape, Spacing, Schatten/Glow, Haptik-Tabelle.
- Glas-Presets (Panel, Kachel, Ordner, Dock, Nav-Kapsel, Island) in drei API-Stufen, Haze-Bewertung.
- Selbst gezeichnete System-Icons, App-Icon-Maske. Komponenten: Switch, Toggle, Slider, Dialog, Bottom-Sheet mit Nested Scroll, Einstellungsliste.
- Demo-Galerie aller Komponenten und Zustände, Previews, Unit-Tests der Gestenphysik. `LICENSES.md` ab der ersten fremden Ressource (Schrift, Haze).
- **Risiken:**
  - Die Platzhalter-Schrift hat andere Metriken als das Original, < 2 dp ist bei Text eventuell nicht erreichbar. Gegenmaßnahme: Auswahl nach x-Höhe. Eine eigene, lizenzierte Schrift ist austauschbar.
  - Kantenlicht und Refraktion lassen sich aus Screenshots nur begrenzt messen. Reagiert das Licht auf Neigung, braucht es ein Video mit geneigtem Gerät. Findet sich keins, ist das Verhalten `[geschätzt]`.

### Phase B – Lockscreen und AOD (12–18 PT)
- Uhr-Stile, Live Clock, transparentes Uhr-Widget, Kontrastlogik, Hoch-/Quer-/Faltformat.
- Tiefeneffekt per ML Kit Subject Segmentation (braucht Google Play-Dienste), Fallback eigene Maske. Video-Wallpaper.
- Durchgehender Übergang AOD → Lockscreen → Homescreen (bis Phase D mit Platzhalter-Home). Widgets, Shortcuts.
- Lockscreen Island: Pille, Ziehen bis Vollbild-Player, Lyrics.
- Gestapelte Benachrichtigungen, Entsperren mit Rubber-Band, PIN/Muster/Passwort, Fingerabdruck-, Gesichts- und Lade-Animation.
- **Risiken:** Qualität der Segmentierung. Die Licht-und-Schatten-Animationen sind shader-lastig.

### Phase C – Statusleiste, Island-Benachrichtigungen, Benachrichtigungsleiste, Kontrollzentrum (15–22 PT)
- Simulierte Statusleiste mit echten Daten. Island-Benachrichtigungen: erscheinen, zusammenziehen, expandieren, wegwischen, mehrere gleichzeitig.
- Pull-Down-Zonen, fingerfolgender Blur-Aufbau, Karten-Stapel, „Alle löschen“.
- Kontrollzentrum: rundes Raster, Zustandswechsel mit Icon-Morphing, Slider, Medien-, Verbindungs- und Geräte-Panel, Bearbeiten-Modus mit Drag, Resize und Reflow, Stagger, Multicolor.
- Echte Daten und Schalter, wo die API es erlaubt (siehe LIMITATIONS).
- **Risiken:** Compose hat kein sortier- und skalierbares Raster, es wird selbst gebaut. Mehrere Glas-Ebenen bei 120 Hz kosten Leistung.

### Phase D – Launcher, Flux Home, Ordner, Schublade, Suche (25–35 PT)
- Echter HOME-Launcher (`LauncherApps`, Shortcuts, `AppWidgetHost`, optional Icon-Packs). Seitenraster und Indikator.
- Flux-Home-Resize mit Live-Reflow. Die Regeln werden aus den Aufnahmen abgeleitet.
- Glas-Ordner (Öffnen/Schließen, Seiten, Umbenennen, große Formen), Drag-and-Drop mit Wackelmodus, Widgets, Dock, Suchleiste, Contour-Glow.
- Schublade mit Alphabetleiste und Kategorien, Suche mit Ergebnisanimation.
- App-Start über `ActivityOptions` plus Overlay-Animation im Launcher.
- **Risiken:**
  - Die größte Phase. Der Reflow-Algorithmus von Flux Home ist unbekannt und hat viele Sonderfälle.
  - Widgets sind klassische Views innerhalb von Compose.
  - Das Gerät schränkt Drittanbieter-Launcher eventuell ein ⚠.

### Phase E – Recents, Navigation, App-Übergänge, Predictive Back (10–15 PT)
- Recents mit Kartenskalierung und Blur, Wegwischen, „Alle löschen“, Schnellwechsel.
- Gesten-Leiste und Kapsel-Navigation mit Wechsel zwischen den Varianten, `PredictiveBackHandler`.
- App-Übergänge aus Icon, Ordner und Recents: 1:1 mit Demo-Apps in der Shell, bei fremden Apps nur angenähert (siehe LIMITATIONS).

### Phase F – Restliche Oberflächen, Feinschliff, Performance, Gesamtvergleich (12–18 PT)
- Lautstärke-Panel, Ausschalt-Menü, Tastatur-Übergänge, Rotation, optional Faltbar/Tablet (+3–5 PT).
- `PERF.md` vom Gerät, Überzeichnung beheben, vollständiger `DIFF_REPORT.md` und `MOTION_REPORT.md`, `LIMITATIONS.md` final.

### Phase G – Reale Systemintegration (nur nach deiner Freigabe, 10–25 PT, hohes Risiko)
Prototypen laut `LIMITATIONS.md`: Accessibility-Overlay, `NotificationListenerService`, `TileService`, Overlay-Lockscreen, Device-Owner-Variante. LSPosed, eigenes ROM oder Systemapp nur auf ausdrücklichen Wunsch.

**Summe A–F: ca. 85–125 PT.** Ohne eigenes ColorOS-17-Gerät lässt sich „1:1“ nicht nachweisen. Das Ziel ist die größtmögliche Nähe, die das öffentliche Material erlaubt. Jeder Wert ist als gemessen oder geschätzt gekennzeichnet. Für die echte Systemintegration ohne Root ist 1:1 ohnehin nicht möglich, und das wird nirgends anders behauptet.

## Übergreifende Risiken

| Risiko | Wirkung | Gegenmaßnahme |
|---|---|---|
| Lückenhafte oder komprimierte Referenz | Werte bleiben TODO oder werden ungenau | ★-Set zuerst, Regeln in `reference/README.md`, nachfragen statt raten |
| Kein ColorOS-17-Gerät | Nur öffentliches Material, also unbekannter Maßstab, Kompression, kein Finger, keine Haptik | Proportionale Messung, Unsicherheit je Wert, `[geschätzt]`-Marken. Ein später verfügbares Gerät ersetzt die Werte |
| ColorOS 17 und Android 17 sind neu | Wenig öffentliche Information. Meine Kenntnis der Android-17-API-Änderungen ist begrenzt | Hypothesen nur nach Referenz übernehmen. API-Annahmen in Phase A gegen das aktuelle SDK prüfen |
| Herstellerspezifisches Glas (Sättigung, Rauschen, Licht) | Material wirkt anders | Messung an Textkanten hinter dem Glas, AGSL-Kette mit Parametern aus der Spec |
| Leistung vieler Glas-Ebenen | Ruckeln bei 120 Hz | Einmal blurren und teilen, herunterskalierte Ebenen, Messung per Macrobenchmark |
| Robolectric rendert Glas anders als ein Gerät | Diff für Glas unzuverlässig | Geometrie per Robolectric, Glas per Geräteaufnahme |
| Kein Gerät im Container | Performance und Haptik hier nicht prüfbar | Skripte für S22 Ultra und S25 FE, du schickst die Ergebnisse |
| Umfang des Referenzmaterials (109 Einträge, gut 120 Dateien) | Hoher Aufwand für dich | Nach Phasen liefern, jeweils nur das nächste Set |
| Rechtliches | Original-UI ist geschützt | Keine Assets, keine Marken, nie in der APK. Das Repo ist öffentlich, deshalb werden Referenzdateien nicht committed, nur Messwerte mit Quellenlink |
| Öffentlicher Debug-Schlüssel (`app/debug.keystore`) | Jeder kann eine APK mit derselben Signatur bauen, die sich über die installierte App legen ließe | Nur für Testbuilds. Für eine echte Veröffentlichung gibt es einen eigenen, geheimen Release-Schlüssel |

## Was ich als Nächstes brauche

1. **Das ★-Set** (25 Dateien) aus öffentlichen Quellen, siehe [`CHECKLISTE.md`](CHECKLISTE.md) und [`reference/QUELLEN.md`](reference/QUELLEN.md). Den Rest phasenweise.
2. **Optional:** Android- und One-UI-Version der Testgeräte.
3. **Netzwerk:** `dl.google.com` freigeben oder entscheiden, dass Builds nur über GitHub Actions laufen.
4. **Neutraler Arbeitstitel und Paketname.** Vorschlag: „Shell“, Paket `app.shell.fluid`.
5. **Externe Bibliothek:** Ist Haze (Apache-2.0) als Abhängigkeit in Ordnung, falls es den Vergleich in Phase A gewinnt?
