# LIMITATIONS

Was eine normale App auf einem nicht gerooteten Gerät **nicht** 1:1 nachbauen kann, und was es stattdessen gibt.

> **Status: Entwurf (2026-10-23).** Die Aussagen beruhen auf dokumentiertem Android-Plattformverhalten bis Android 15/16. Punkte mit ⚠ müssen auf dem Zielgerät geprüft werden, weil Hersteller und Versionen hier abweichen. Phase G prüft die einzelnen Wege praktisch und aktualisiert diese Datei.

## Grundsatz

Der **In-App-Nachbau** (Demo-Shell mit Fake-Lockscreen, Fake-Statusleiste, Overlays und Demo-Apps) kann Gestaltung und Bewegung vollständig 1:1 zeigen, denn dort gehört jedes Pixel uns. Grenzen gibt es erst, wenn die Oberflächen **das echte System ersetzen** sollen.

## Übersicht

| Bereich | Ohne Root möglich | Nicht möglich | Alternative |
|---|---|---|---|
| **Homescreen** | Vollwertiger Launcher (HOME-Intent, `LauncherApps`, Shortcuts, Widgets per `AppWidgetHost`) | — | — |
| **App-Start/-Rückkehr fremder Apps** | `ActivityOptions.makeScaleUpAnimation` / `makeClipRevealAnimation` beim Start | Die Animation „Fenster schrumpft ins Icon“ und eigene Kurven für das fremde Fenster. Dafür braucht es Remote-Animation-APIs, die System-Launchern vorbehalten sind | 1:1 nur in der Demo-Shell mit Demo-Apps |
| **Gestennavigation mit Drittanbieter-Launcher** ⚠ | Home-Geste führt zum eigenen Launcher | Home-/Recents-Animation steuert der System-Launcher. Manche Hersteller schränken Gesten bei Drittanbieter-Launchern ein | Auf dem Gerät prüfen |
| **Recents** | Liste zuletzt genutzter Apps per `UsageStatsManager` (Berechtigung durch dich) | Echte Recents ersetzen, Fenster-Vorschaubilder fremder Apps, Apps zuverlässig beenden | Recents in der Demo-Shell. Echte App-Liste mit Icons statt Vorschaubildern |
| **Lockscreen** ⚠ | Activity über dem Sperrbildschirm (`setShowWhenLocked`), Start bei Bildschirm an über einen Vordergrund-Dienst (braucht `SYSTEM_ALERT_WINDOW` wegen der Hintergrund-Start-Sperre) | Den System-Sperrbildschirm ersetzen. Die Sicherheit bleibt beim System, die eigene Oberfläche liegt nur darüber | Nach dem eigenen Wisch ruft `KeyguardManager.requestDismissKeyguard` die System-Entsperrung auf |
| **PIN / Muster / Biometrie** | Eigene Optik für eine reine App-PIN | Eigene Oberfläche für die Geräte-PIN oder Biometrie. `BiometricPrompt` zeigt immer die System-Oberfläche | Optik im In-App-Nachbau |
| **Always-on-Display** | `DreamService` (Bildschirmschoner) beim Laden/Docken | Zeichnen bei ausgeschaltetem Bildschirm | Optik im In-App-Nachbau |
| **Statusleiste** | Eigene Statusleiste in eigenen Activities (System-Leiste ausgeblendet) mit echten Daten: Zeit, Akku, Netz, Benachrichtigungs-Icons per `NotificationListenerService` | Die System-Statusleiste ersetzen. Normale Overlays (`TYPE_APPLICATION_OVERLAY`) liegen unter der Statusleiste | Accessibility-Overlay (siehe unten) |
| **Pull-Down (Benachrichtigungen / CC)** ⚠ | Per Accessibility-Dienst: Overlay-Streifen über der Statusleiste fängt den Wisch ab und öffnet die eigene Oberfläche. So arbeiten bekannte Drittanbieter-„Notification-Shade“-Apps | Die System-Leiste deaktivieren. Konflikte mit der System-Geste sind möglich | — |
| **Benachrichtigungen** | Vollständige Liste, Schließen, Aktionen, Direktantwort, Gruppen per `NotificationListenerService` | — | — |
| **Kontrollzentrum: Kacheln** | Taschenlampe, Helligkeit (`WRITE_SETTINGS`), Lautstärke, Nicht stören, Auto-Rotation, Medien-Steuerung, eigene `TileService`-Kacheln im System-QS | WLAN, Bluetooth, mobile Daten, Flugmodus, Standort, Hotspot und NFC direkt umschalten (seit Android 10/13 für Apps gesperrt) | Antippen öffnet das System-Panel (`Settings.Panel` bzw. Einstellungs-Intent). Einige Schalter gehen nach einmaligem `adb shell pm grant … WRITE_SECURE_SETTINGS` ⚠ |
| **Lautstärke-Panel** ⚠ | Per Accessibility-Dienst Lautstärketasten abfangen und eigenes Panel zeigen | — | — |
| **Ausschalt-Menü** | System-Menü per Accessibility öffnen | Die Power-Taste abfangen und ein eigenes Menü statt des Systems zeigen | Optik im In-App-Nachbau |
| **Blur hinter eigenen Fenstern** ⚠ | Ab Android 12 fensterübergreifender Blur (`setBackgroundBlurRadius`), sofern das Gerät ihn aktiviert hat (`isCrossWindowBlurEnabled`) | Blur auf Geräten ohne diese Funktion | Halbtransparente Fläche |
| **System-Wallpaper auslesen** ⚠ | Wallpaper setzen (`SET_WALLPAPER`) | Lesen ist ab Android 13 stark eingeschränkt | Wallpaper in der App auswählen, die App setzt es und behält eine Kopie für Blur und Tiefeneffekt |
| **Tiefeneffekt (Uhr hinter Motiv)** | ML Kit Subject Segmentation mit Google Play-Diensten | — | Manuelle Maske ohne Play-Dienste |
| **Haptik** | `HapticFeedbackConstants`, `VibrationEffect`, Kompositionen (ab API 30, falls das Gerät sie unterstützt) | Herstellereigene, fein abgestimmte Vibrationsmuster | Annäherung pro Stelle, Abgleich nach deinem Eindruck |
| **Schrift und Icons** | Freie Schrift, eigene Icons, Icon-Maske für fremde Adaptive Icons | Original-Schrift und Original-Icons (proprietär, nicht verwendet) | Austausch über eine zentrale Theme-Datei |

## Hinweise zu Accessibility und Overlays

- Die Accessibility-API ist für Bedienungshilfen gedacht. Für eine App, die nur privat per Sideload genutzt wird, ist das unkritisch. Bei einer Veröffentlichung im Play Store wäre diese Nutzung voraussichtlich ein Richtlinien-Problem.
- Ab Android 13 sperrt das System „eingeschränkte Einstellungen“ (Accessibility, Benachrichtigungszugriff) für per Sideload installierte Apps. Du entsperrst sie einmal manuell in den App-Infos.
- Hersteller-Akku-Optimierung kann Hintergrund-Dienste beenden. Die App muss von der Optimierung ausgenommen werden ⚠.

## Weg über Root (nur auf Wunsch)

| Weg | Was er ermöglicht | Kosten / Risiken |
|---|---|---|
| **LSPosed-Modul** (Root per Magisk/KernelSU) | Hooks in System-UI und System-Launcher: echte Statusleiste, Kontrollzentrum, Lockscreen und Recents umgestalten oder ersetzen | Entsperrbarer Bootloader nötig (bei vielen OPPO-Modellen offiziell nicht möglich, bei OnePlus meist schon ⚠). Bricht bei OS-Updates. Banking- und Integritätsprüfungen schlagen fehl. Garantie |
| **Eigenes ROM** (AOSP/LineageOS mit eigener SystemUI und eigenem Launcher3-Fork) | Volle Kontrolle über alle Oberflächen und Animationen | Sehr großer Aufwand, nur für Geräte mit verfügbarem ROM-Unterbau, gleiche Bootloader-Frage |
