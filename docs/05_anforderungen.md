# 5. Anforderungen

## 5.1 Funktionale Anforderungen

| ID | Anforderung | Beschreibung | Verbindlichkeit | Priorität | Stabilität |
|---|---|---|---|---|---|
| **FA-01** | Kurs erstellen und verwalten | Ein Dozierender kann Kurse anlegen, bearbeiten und archivieren. Zu einem Kurs gehören Name, Beschreibung, Zeitraum und zugeordnete Studierende. | Pflicht | Hoch | Stabil |
| **FA-02** | Lektion erstellen und verwalten | Ein Dozierender kann innerhalb eines Kurses Lektionen anlegen, ordnen und bearbeiten. Jede Lektion enthält Metadaten (Lernziele, Termin, Voraussetzungen). | Pflicht | Hoch | Stabil |
| **FA-03** | Kursmaterial hochladen | Ein Dozierender kann Dateien (PDF, PPTX, Skripte, Arbeitsblätter) zu einer Lektion hochladen. Beim Upload werden Metadaten (Dateiname, Typ, Grösse) erfasst. | Pflicht | Hoch | Stabil |
| **FA-04** | Kursmaterial einsehen und herunterladen | Ein Studierender kann die Materialien seiner eingeschriebenen Kurse einsehen und herunterladen. | Pflicht | Hoch | Stabil |
| **FA-05** | Rollenbasierte Kurszuordnung | Studierende werden Kursen zugeordnet und sehen ausschliesslich die Inhalte ihrer zugewiesenen Kurse. Dozierende sehen und verwalten nur ihre eigenen Kurse. | Pflicht | Hoch | Stabil |
| **FA-06** | Volltextsuche über Kursmaterialien | Studierende und Dozierende können über alle indexierten Materialien hinweg nach Begriffen suchen. Die Suche berücksichtigt den Kurskontext des Nutzers. | Pflicht | Hoch | Instabil |
| **FA-07** | AI-Zusammenfassung generieren | Beim Hochladen von Kursmaterial wird automatisch eine kompakte Zusammenfassung pro Lektion durch die AI-Komponente erzeugt. | Pflicht | Hoch | Instabil |
| **FA-08** | AI-Zusammenfassung prüfen und freigeben | Ein Dozierender kann generierte AI-Zusammenfassungen einsehen, ergänzen, freigeben oder sperren. Erst nach Freigabe sind Zusammenfassungen für Studierende sichtbar. | Pflicht | Hoch | Stabil |
| **FA-09** | AI-Lernhilfen generieren | Die AI-Komponente erzeugt pro Lektion ergänzende Lernhilfen (z.B. Schlüsselbegriffe, Übungsfragen) auf Basis des Kursmaterials. | Wunsch | Mittel | Instabil |
| **FA-10** | AI-gestützte kontextbezogene Q&A | Ein Studierender kann Fragen an die AI-Komponente stellen. Die Antwort basiert ausschliesslich auf dem Material des jeweiligen Kurses (Kurskontext). | Pflicht | Hoch | Instabil |
| **FA-11** | Persönliche Notizen anlegen | Ein Studierender kann zu Lektionen oder Kursmaterialien persönliche Notizen erstellen, bearbeiten und löschen. Notizen sind nur für den Ersteller sichtbar. | Pflicht | Mittel | Stabil |
| **FA-12** | Benutzerverwaltung | Der Systemadministrator kann Benutzerkonten anlegen, bearbeiten, deaktivieren und Rollen (Studierender, Dozierender, Administrator) zuweisen. | Pflicht | Hoch | Stabil |
| **FA-13** | Authentifizierung über Identitätsprovider | Benutzer melden sich über den bestehenden Identitätsprovider der Schule (LDAP/OAuth) an. Eine eigene Passwortverwaltung ist nicht vorgesehen. | Pflicht | Hoch | Stabil |
| **FA-14** | Kontextindexierung von Materialien | Hochgeladene Dateien werden automatisch durch die AI-Komponente analysiert, in Textfragmente zerlegt und in einem Suchindex abgelegt. | Pflicht | Hoch | Instabil |
| **FA-15** | Benachrichtigungen versenden | Das System versendet Benachrichtigungen per E-Mail bei definierten Ereignissen (z.B. neues Material verfügbar, Zusammenfassung freigegeben). | Wunsch | Niedrig | Instabil |

## 5.2 Nicht-funktionale Anforderungen (Qualitätsanforderungen)

| ID | Kategorie | Anforderung | Beschreibung | Verbindlichkeit | Priorität |
|---|---|---|---|---|---|
| **NFA-01** | Datenschutz | Zugriffskontrollen | Jeder Benutzer darf ausschliesslich auf die Daten zugreifen, die seiner Rolle und Kurszuordnung entsprechen. | Pflicht | Hoch |
| **NFA-02** | Datenschutz | Logging und Auditierbarkeit | Alle sicherheitsrelevanten Aktionen (Login, Dateizugriff, Rollenänderung, AI-Nutzung) werden in einem Audit-Log protokolliert. | Pflicht | Hoch |
| **NFA-03** | Datenschutz | Datenminimierung | Es werden nur die für den Betrieb notwendigen personenbezogenen Daten erhoben und gespeichert (DSG/DSGVO-konform). | Pflicht | Hoch |
| **NFA-04** | Nachvollziehbarkeit | Transparenz der AI-Ergebnisse | Jede AI-Zusammenfassung und AI-Antwort zeigt die Quellverweise (Kursmaterial, Seitenzahl oder Textabschnitt) an. | Pflicht | Hoch |
| **NFA-05** | Suchqualität | Relevanz der Suchergebnisse | Die Volltextsuche liefert relevante Ergebnisse, sortiert nach Übereinstimmung. Irrelevante Treffer aus nicht zugeordneten Kursen werden nicht angezeigt. | Pflicht | Mittel |
| **NFA-06** | Benutzbarkeit | Intuitive Bedienung | Die Plattform soll ohne Schulung bedienbar sein. Kernfunktionen sind in maximal 3 Klicks erreichbar. | Pflicht | Mittel |
| **NFA-07** | Performance | Antwortzeit | Seitenaufrufe und Suchanfragen innerhalb von 2 Sekunden. AI-Antworten innerhalb von 10 Sekunden. | Wunsch | Mittel |
| **NFA-08** | Verfügbarkeit | Betriebszeiten | Während des Semesters an Werktagen 07:00–22:00 Uhr (Verfügbarkeit ≥ 98%). | Wunsch | Mittel |
| **NFA-09** | Skalierbarkeit | Gleichzeitige Benutzer | Das MVP soll mindestens 50 gleichzeitige Benutzer unterstützen. | Wunsch | Niedrig |
| **NFA-10** | Wartbarkeit | Modularer Aufbau | Die AI-Komponente ist als austauschbarer Service entkoppelt. | Pflicht | Mittel |
| **NFA-11** | Sicherheit | Verschlüsselte Übertragung | Alle Kommunikation zwischen Client und Server erfolgt über HTTPS. | Pflicht | Hoch |

## 5.3 Rahmenbedingungen

| ID | Rahmenbedingung | Beschreibung |
|---|---|---|
| **RB-01** | Webapplikation | Das System wird als Webapplikation realisiert, zugänglich über gängige Browser (Chrome, Firefox, Safari, Edge). |
| **RB-02** | Lokal betriebene AI | Die AI-Komponente wird lokal betrieben (kein externer Cloud-AI-Dienst), um Datenschutzanforderungen zu erfüllen. |
| **RB-03** | MVP-Scope | Der Umfang der ersten Version beschränkt sich auf Benutzer-/Kursverwaltung, Datei-Upload, Volltextindexierung und AI-Q&A/Summarization. |
| **RB-04** | Bestehende Infrastruktur | Die Integration in den bestehenden Identitätsprovider der Schule ist vorausgesetzt. |

---

[← Systemkontext](04_systemkontext.md) | [Zurück zur Übersicht](README.md) | [Weiter: Geschäftsanwendungsfälle →](06_business_use_cases.md)
