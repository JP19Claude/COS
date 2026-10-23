# LIMITATIONS

Was eine normale App auf einem nicht gerooteten Gerät **nicht** 1:1 nachbauen kann, welche Alternativen es gibt und was Wege mit mehr Rechten kosten.

> **Status: Entwurf (Phase 0, 2026-10-23).** Die Aussagen beruhen auf dokumentiertem Android-Plattformverhalten bis Android 16. Punkte mit ⚠ hängen vom Hersteller, vom Gerät oder von Änderungen in Android 17 ab und werden geprüft. API-Annahmen in Phase A gegen das aktuelle SDK, die praktischen Wege in Phase G.

## Grundsatz

Der **In-App-Nachbau** ist eine Demo-Shell mit simulierter Statusleiste, simulierten Systemdaten, Overlays und Demo-Apps. Dort gehört jedes Pixel der App, deshalb können Gestaltung und Bewegung dort vollständig nachgebildet werden. Grenzen gibt es, sobald die Oberflächen **das echte System ersetzen** sollen. Nirgends wird „1:1“ behauptet, wo es nur ähnlich ist.

## Übersicht

| Bereich | Ohne Root möglich | Nicht möglich | Alternative |
|---|---|---|---|
| **Homescreen** | Vollwertiger Launcher (HOME-Intent, `LauncherApps`, Shortcuts, `AppWidgetHost`, Icon-Packs) | — | — |
| **App-Start/-Rückkehr fremder Apps** | `ActivityOptions.makeScaleUpAnimation` / `makeClipRevealAnimation` plus Overlay-Animation im Launcher | Echte Shared-Element-Übergänge in fremde App-Fenster und die Animation „Fenster schrumpft ins Icon“. Dafür braucht es Remote-Animation-APIs, die nur der System-Launcher hat | 1:1 nur mit Demo-Apps in der Shell. Bei fremden Apps eine Annäherung |
| **Gestennavigation mit Drittanbieter-Launcher** ⚠ | Home-Geste führt zum eigenen Launcher | Home- und Recents-Animation steuert der System-Launcher. Manche Hersteller schränken Gesten bei Drittanbieter-Launchern ein | Auf dem Gerät prüfen |
| **Navigationsleiste / Kapsel** | Eigene Kapsel und Gesten-Leiste **in eigenen Fenstern** (Edge-to-Edge) | Die System-Navigationsleiste ersetzen | Kapsel in Shell und Demo-Apps |
| **Predictive Back** | Vollständig in eigenen Activities (`PredictiveBackHandler`) | Zurück-Animationen fremder Apps | — |
| **Recents** | Liste zuletzt genutzter Apps per `UsageStatsManager` (Berechtigung durch dich) | Echte Recents ersetzen, Fenster-Vorschaubilder fremder Apps, Apps zuverlässig beenden | Recents in der Shell. Echte App-Liste mit Icons statt Vorschaubildern |
| **Lockscreen** ⚠ | Activity über dem Sperrbildschirm (`setShowWhenLocked`). Start beim Einschalten des Bildschirms über einen Vordergrund-Dienst (braucht `SYSTEM_ALERT_WINDOW` wegen der Hintergrund-Start-Sperre) | Den System-Sperrbildschirm ersetzen. Die Sicherheit bleibt beim System, die eigene Oberfläche liegt nur darüber | Nach dem eigenen Wisch ruft `KeyguardManager.requestDismissKeyguard` die System-Entsperrung auf |
| **Durchgehender Übergang AOD → Lock → Home** | In der Shell vollständig | Auf echtem AOD und echtem Keyguard | Eigener Live-Wallpaper-Dienst (`WallpaperService`) ist systemweit möglich. Wie stark er auf Entsperren und Zoom reagieren kann, ist eingeschränkt ⚠ |
| **Always-on-Display** | `DreamService` (Bildschirmschoner) beim Laden oder Docken | Zeichnen bei ausgeschaltetem Bildschirm | Optik in der Shell |
| **PIN / Muster / Passwort / Biometrie** | Eigene Optik für eine reine App-Sperre | Eigene Oberfläche für Geräte-PIN oder Biometrie. `BiometricPrompt` zeigt immer die System-Oberfläche | Optik und Animationen in der Shell |
| **Statusleiste** | Eigene Leiste in eigenen Activities (System-Leiste ausgeblendet) mit echten Daten: Zeit, Akku, Netz, Benachrichtigungs-Icons per `NotificationListenerService` | Die System-Statusleiste ersetzen. Normale Overlays (`TYPE_APPLICATION_OVERLAY`) liegen unter ihr | Accessibility-Overlay (unten) |
| **Island / Live-Inhalte systemweit** ⚠ | Ab Android 16 „Live Updates“ (`Notification.ProgressStyle`, hervorgehobene laufende Benachrichtigungen). Die App liefert den Inhalt, das System zeichnet ihn in seiner eigenen Form | Eine eigene Island über der echten Statusleiste zeichnen | Island in der Shell vollständig. Wie ColorOS Live Updates darstellt, wird auf dem Gerät geprüft |
| **Pull-Down (Benachrichtigungen / CC)** ⚠ | Per Accessibility-Dienst: Ein Overlay-Streifen über der Statusleiste fängt den Wisch ab und öffnet die eigene Oberfläche. So arbeiten bekannte Drittanbieter-„Notification-Shade“-Apps | Die System-Leiste deaktivieren (außer mit Device Owner, unten). Konflikte mit der System-Geste sind möglich | — |
| **Benachrichtigungen** | Vollständige Liste, Schließen, Aktionen, Direktantwort, Gruppen per `NotificationListenerService` | — | — |
| **Kontrollzentrum: Kacheln** | Taschenlampe, Helligkeit (`WRITE_SETTINGS`), Lautstärke, Nicht stören, Auto-Rotation, Medien-Steuerung (`MediaSessionManager`), eigene `TileService`-Kacheln im System-Menü | WLAN, Bluetooth, mobile Daten, Flugmodus, Standort, Hotspot und NFC direkt umschalten (seit Android 10 bzw. 13 für Apps gesperrt) | Antippen öffnet das System-Panel (`Settings.Panel` bzw. Einstellungs-Intent). Einige Schalter gehen nach einmaligem `adb shell pm grant … WRITE_SECURE_SETTINGS` ⚠ |
| **Geräte-Panel** ⚠ | Eigene Liste gekoppelter Geräte (Bluetooth-Berechtigungen) | Herstellereigene Geräteverbund-Funktionen | Optik in der Shell |
| **Lautstärke-Panel** ⚠ | Per Accessibility-Dienst Lautstärketasten abfangen und eigenes Panel zeigen | — | — |
| **Ausschalt-Menü** | System-Menü per Accessibility öffnen | Power-Taste abfangen und ein eigenes Menü statt des Systems zeigen | Optik in der Shell |
| **Glas über fremden Inhalten** ⚠ | Ab Android 12 fensterübergreifender Blur (`setBackgroundBlurRadius`), sofern das Gerät ihn aktiviert hat (`isCrossWindowBlurEnabled`). Innerhalb eigener Fenster immer: `RenderEffect` und AGSL | Blur über anderen Apps auf Geräten ohne diese Funktion | Halbtransparente Fläche plus Kantenlinie |
| **System-Wallpaper auslesen** ⚠ | Wallpaper setzen (`SET_WALLPAPER`) | Lesen ist ab Android 13 stark eingeschränkt | Wallpaper in der App auswählen. Die App setzt es und behält eine Kopie für Glas, Live Clock und Tiefeneffekt |
| **Tiefeneffekt** | ML Kit Subject Segmentation mit Google Play-Diensten | — | Manuelle Maske ohne Play-Dienste |
| **Haptik** | `HapticFeedbackConstants`, `VibrationEffect`, Kompositionen ab API 30 (sofern das Gerät sie unterstützt) | Herstellereigene, fein abgestimmte Vibrationsmuster | Annäherung pro Stelle, Abgleich nach deinem Eindruck |
| **Schrift und Icons** | Freie Schrift, eigene Icons, Icon-Maske für fremde Adaptive Icons | Original-Schrift und Original-Icons (proprietär, nicht verwendet) | Austausch über eine zentrale Theme-Datei |

## Hinweise zu Accessibility und Overlays

- Die Accessibility-API ist für Bedienungshilfen gedacht. Für eine App, die nur privat per Sideload genutzt wird, ist das unkritisch. Bei einer Veröffentlichung im Play Store wäre diese Nutzung voraussichtlich ein Richtlinien-Problem.
- Ab Android 13 sperrt das System „eingeschränkte Einstellungen“ (Accessibility, Benachrichtigungszugriff) für per Sideload installierte Apps. Du entsperrst sie einmal manuell in den App-Infos.
- Die Akku-Optimierung des Herstellers kann Hintergrund-Dienste beenden. Die App muss davon ausgenommen werden ⚠.

## `DevicePolicyManager`-Grenzen

| Rolle | Was sie hier bringt | Kosten / Risiken |
|---|---|---|
| **Device Owner** (per `adb shell dpm set-device-owner`) ⚠ | `setStatusBarDisabled` sperrt die System-Leiste. `setKeyguardDisabled` schaltet den Sperrbildschirm ab, aber nur ohne sichere Sperre. Der Lock-Task-Modus blendet System-UI aus. Zusammen ergibt das eine eigene Vollbild-„System-Shell“ | Setzen geht nur, solange kein Konto auf dem Gerät ist, praktisch also nach einem Zurücksetzen. Ohne sichere Sperre ist das Gerät ungeschützt. Lock-Task schränkt andere Apps stark ein. Für ein Alltagsgerät nicht empfohlen |
| **Profile Owner** (Arbeitsprofil) | Nichts für diese Oberflächen | — |

## Wege mit mehr Rechten (nur auf ausdrücklichen Wunsch)

| Weg | Was er ermöglicht | Kosten / Risiken |
|---|---|---|
| **LSPosed-Modul** (Root per Magisk/KernelSU) | Hooks in System-UI und System-Launcher. Echte Statusleiste, Kontrollzentrum, Lockscreen, Recents und Navigation lassen sich umgestalten oder ersetzen | Entsperrbarer Bootloader nötig (bei vielen OPPO-Modellen offiziell nicht möglich, bei OnePlus meist schon ⚠). Bricht bei OS-Updates. Banking- und Integritätsprüfungen schlagen fehl. Garantie |
| **Systemapp** | Zugriff auf Signatur-Berechtigungen (z. B. Remote-Animationen) | Nur mit Root bzw. eigenem ROM installierbar. Plattform-Signatur unerreichbar |
| **Eigenes ROM** (AOSP/LineageOS mit eigener SystemUI und eigenem Launcher3-Fork) | Volle Kontrolle über alle Oberflächen und Animationen | Sehr großer Aufwand, nur für Geräte mit verfügbarem ROM-Unterbau, gleiche Bootloader-Frage |
