# 8. Dynamische Modellierung

## 8.1 Systemsequenzdiagramm (SSD): SUC-06 «AI-Frage stellen»

Das Systemsequenzdiagramm zeigt die Interaktion zwischen dem Akteur und dem System als Blackbox. Interne Abläufe sind nicht sichtbar.

![SSD AI-Frage stellen](diagrams/ssd_ai_frage.png)

<details>
<summary>PlantUML-Quellcode</summary>

```plantuml
@startuml ssd_ai_frage
title SSD: AI-Frage stellen (SUC-06)

actor "Student" as stud
participant "«system»\nWinslow" as sys

stud -> sys : selectCourse(courseId)
sys --> stud : displayCourseOverview\nwith AI Q&A option

stud -> sys : openAIQueryView()
sys --> stud : displayQueryInputField

stud -> sys : submitQuestion(questionText)

alt valid question
  sys --> stud : displayLoadingIndicator

  sys --> stud : displayAnswer\nwith sourceReferences\n(material, page/section)

else invalid question (empty / too long)
  sys --> stud : displayValidationError
end

alt no indexed materials
  sys --> stud : displayNotice:\n«No material indexed»
end

alt AI service unavailable
  sys --> stud : displayError:\n«Service unavailable,\nplease try again later»
end

@enduml
```

</details>

## 8.2 Zustandsdiagramm: Lebenszyklus einer «AI-Zusammenfassung»

Die AI-Zusammenfassung ist ein zentrales Geschäftsobjekt mit einem komplexen Lebenszyklus.

![Zustandsdiagramm AI-Zusammenfassung](diagrams/zustand_zusammenfassung.png)

<details>
<summary>PlantUML-Quellcode</summary>

```plantuml
@startuml zustand_zusammenfassung
title State Machine: AISummary (SummaryStatus)

[*] --> GENERATED : material uploaded /\nAI generates summary

state GENERATED {
}

GENERATED --> IN_REVIEW : Lecturer opens\nsummary for review

state "IN_REVIEW" as IN_REVIEW {
}

IN_REVIEW --> APPROVED : Lecturer approves\n[content acceptable]
IN_REVIEW --> REVISED : Lecturer edits /\ncorrects content
IN_REVIEW --> BLOCKED : Lecturer blocks\n[content not acceptable]

state REVISED {
}

REVISED --> APPROVED : Lecturer approves\nrevised version

state APPROVED {
}
note right of APPROVED
  Summary is visible
  to enrolled Students.
end note

state BLOCKED {
}
note right of BLOCKED
  Summary is hidden
  from Students.
end note

APPROVED --> BLOCKED : Lecturer blocks\nretroactively

BLOCKED --> IN_REVIEW : Lecturer reopens\nfor review

APPROVED --> OUTDATED : material updated\n(new upload to Lesson)
BLOCKED --> OUTDATED : material updated\n(new upload to Lesson)

state OUTDATED {
}
note right of OUTDATED
  Old summary is archived /
  no longer displayed.
end note

OUTDATED --> GENERATED : AI generates new\nsummary from\nupdated material

APPROVED --> [*] : Lesson deleted
BLOCKED --> [*] : Lesson deleted
OUTDATED --> [*] : Lesson deleted

@enduml
```

</details>

## Erläuterung der Zustände

| State (SummaryStatus) | Zustand (DE) | Beschreibung |
|---|---|---|
| **GENERATED** | Generiert | Die AI-Komponente hat eine Zusammenfassung erzeugt. Sie ist noch nicht geprüft und für Studierende nicht sichtbar. |
| **IN_REVIEW** | In Prüfung | Ein Dozierender hat die Zusammenfassung geöffnet und prüft den Inhalt. |
| **REVISED** | Überarbeitet | Der Dozierende hat die Zusammenfassung manuell ergänzt oder korrigiert. Sie wartet auf die finale Freigabe. |
| **APPROVED** | Freigegeben | Die Zusammenfassung ist geprüft und für alle eingeschriebenen Studierenden sichtbar. |
| **BLOCKED** | Gesperrt | Die Zusammenfassung wurde als ungeeignet markiert und ist für Studierende nicht sichtbar. |
| **OUTDATED** | Veraltet | Das zugrundeliegende Kursmaterial wurde aktualisiert. Die alte Zusammenfassung wird archiviert und eine Neugenerierung angestossen. |

---

[← Systemanwendungsfälle](07_systemanwendungsfaelle.md) | [Zurück zur Übersicht](README.md) | [Weiter: Fachklassendiagramm →](09_fachklassendiagramm.md)
