# 6. Geschäftsanwendungsfälle (Business Use Cases)

Geschäftsanwendungsfälle beschreiben die geschäftlichen Abläufe unabhängig von einer konkreten systemtechnischen Umsetzung. Sie werden aus Sicht des Geschäftsbetriebes (hier: der Höheren Fachschule) formuliert.

## 6.1 Übersicht

| BUC-ID | Name | Kurzbeschreibung | Auslöser | Ergebnis | Akteure |
|---|---|---|---|---|---|
| **BUC-01** | Kursmaterialien bereitstellen | Ein Dozierender strukturiert einen Kurs mit Lektionen, lädt Materialien hoch und pflegt Metadaten. | Dozierender bereitet eine Lehrveranstaltung vor. | Kursmaterialien sind für die eingeschriebenen Studierenden zugänglich. | Dozierender, Studierender |
| **BUC-02** | Kursinhalte erarbeiten | Ein Studierender nutzt die bereitgestellten Materialien, Zusammenfassungen und Lernhilfen. | Studierender möchte sich vorbereiten oder Stoff repetieren. | Studierender hat Inhalte eingesehen und ggf. Notizen angelegt. | Studierender |
| **BUC-03** | AI-generierte Inhalte qualitätssichern | Ein Dozierender prüft AI-Zusammenfassungen und entscheidet über Freigabe oder Sperrung. | AI-Komponente hat eine Zusammenfassung generiert. | Zusammenfassung ist freigegeben oder gesperrt. | Dozierender |
| **BUC-04** | Benutzer und Kurszuordnungen verwalten | Der Systemadministrator richtet Konten ein, weist Rollen zu und ordnet Studierende Kursen zu. | Semesterbeginn oder Änderung im Teilnehmerkreis. | Alle Benutzer haben korrekte Konten und Kurszuordnungen. | Systemadministrator, Dozierender |

## 6.2 Business Use Case Diagramm

![Business Use Case Diagramm](diagrams/buc_diagramm.png)

<details>
<summary>PlantUML-Quellcode</summary>

```plantuml
@startuml buc_diagramm
skinparam actorStyle awesome
skinparam usecase {
  BackgroundColor<<business>> #LightYellow
  BorderColor<<business>> #DarkGoldenRod
}

left to right direction

actor "Dozierender" as doz
actor "Studierender" as stud
actor "System-\nadministrator" as admin

rectangle "Geschäftskontext: Höhere Fachschule" {

  usecase "Kursmaterialien\nbereitstellen" as BUC01 <<business>>
  usecase "Kursinhalte\nerarbeiten" as BUC02 <<business>>
  usecase "AI-generierte Inhalte\nqualitätssichern" as BUC03 <<business>>
  usecase "Benutzer und\nKurszuordnungen\nverwalten" as BUC04 <<business>>
}

doz -- BUC01
stud -- BUC01
stud -- BUC02
doz -- BUC03
admin -- BUC04
doz -- BUC04

@enduml
```

</details>

## 6.3 Essenzbeschreibungen

### BUC-01: Kursmaterialien bereitstellen

| Feld | Beschreibung |
|---|---|
| **Name** | Kursmaterialien bereitstellen |
| **Art** | Geschäftsanwendungsfall |
| **Kurzbeschreibung** | Ein Dozierender strukturiert einen Kurs mit Lektionen, lädt die zugehörigen Materialien hoch und pflegt die Metadaten. Die AI-Komponente indexiert die Materialien und generiert Zusammenfassungen. |
| **Auslöser** | Dozierender bereitet eine Lehrveranstaltung vor oder aktualisiert bestehende Inhalte. |
| **Ergebnis** | Kursmaterialien sind strukturiert abgelegt, indexiert und für eingeschriebene Studierende zugänglich. AI-Zusammenfassungen wurden generiert. |
| **Akteure** | Dozierender, Studierender (als Empfänger) |
| **Essenzschritte** | Der Dozierende legt den Kurs und die Lektionen an. Der Dozierende lädt Materialien zu den Lektionen hoch und pflegt Metadaten. Die AI-Komponente indexiert die Materialien und generiert eine Zusammenfassung. Der Dozierende prüft die Zusammenfassung und gibt sie frei. Die Studierenden können die Materialien und freigegebenen Zusammenfassungen einsehen. |

### BUC-02: Kursinhalte erarbeiten

| Feld | Beschreibung |
|---|---|
| **Name** | Kursinhalte erarbeiten |
| **Art** | Geschäftsanwendungsfall |
| **Kurzbeschreibung** | Ein Studierender erarbeitet sich den Lernstoff, indem er Materialien einsieht, Zusammenfassungen liest, Fragen an die AI stellt und persönliche Notizen anlegt. |
| **Auslöser** | Studierender möchte sich auf eine Lektion vorbereiten, Stoff repetieren oder eine Frage klären. |
| **Ergebnis** | Studierender hat den gewünschten Stoff eingesehen, Antworten erhalten und ggf. Notizen angelegt. |
| **Akteure** | Studierender |
| **Essenzschritte** | Der Studierende wählt einen Kurs und eine Lektion aus. Der Studierende sieht Materialien und freigegebene Zusammenfassungen ein. Bei Bedarf stellt der Studierende eine Frage an die AI-Komponente. Der Studierende legt bei Bedarf persönliche Notizen an. |

## 6.4 Aktivitätsdiagramm: BUC-01 «Kursmaterialien bereitstellen»

![Aktivitätsdiagramm BUC-01](diagrams/aktivitaet_buc01.png)

<details>
<summary>PlantUML-Quellcode</summary>

```plantuml
@startuml aktivitaet_buc01
|Dozierender|
start
:Kurs anlegen oder\nauswählen;
:Lektion(en) anlegen\nund Metadaten pflegen;
:Kursmaterial(ien)\nhochladen;

|AI-Komponente|
:Material indexieren\nund Textfragmente extrahieren;
:Zusammenfassung\nund Lernhilfen generieren;

|Dozierender|
:Generierte Zusammenfassung\neinsehen und prüfen;

if (Zusammenfassung akzeptabel?) then (ja)
  :Zusammenfassung\nfreigeben;
else (nein)
  if (Nachbesserung möglich?) then (ja)
    :Zusammenfassung\nergänzen / korrigieren;
    :Korrigierte Zusammenfassung\nfreigeben;
  else (nein)
    :Zusammenfassung\nsperren;
    stop
  endif
endif

|Studierender|
:Materialien und freigegebene\nZusammenfassungen einsehen;

stop
@enduml
```

</details>

---

[← Anforderungen](05_anforderungen.md) | [Zurück zur Übersicht](README.md) | [Weiter: Systemanwendungsfälle →](07_system_use_cases.md)
