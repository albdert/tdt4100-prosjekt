# Skjelettprosjekt for TDT4100 prosjekt V2026

Generell prosjektstruktur opprettet fra [prosjekt-base](https://git.ntnu.no/tdt4100/prosjekt-base/tree/main)

## Model-View-Controller prinsippet
[wikipedia.com](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

Prosjektet bygger på Model-View-Controller prinsippet. 

Hensikten er å dele opp programmet
slik at `modellen` representerer intern informasjon om appens tilstand, `view` representerer
brukergrensesnittet, og `controller` håndterer koblingen mellom bruker, `model` og `view`.
```
┌─────────┐   updates   ┌───────────┐   reads/draws    ┌──────┐
│  Model  │ ◄────────── │Controller │ ───────────────► │ View │
│ (Java)  │             │  (Java)   │                  │(FXML)│
└─────────┘             └───────────┘                  └──────┘
                              ▲
                        user interaction
```
## JavaFX
[JavaFX docs](https://docs.oracle.com/javase/8/javase-clienttechnologies.htm)

[JavaFX Architecture](https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-architecture.htm#CHDFDAFF)

[JavaFX Fundamentals](https://dev.java/learn/javafx/)

##### Generell struktur for et JavaFX prosjekt:

"JavaFX uses a theater metaphor: the top-level container is the _Stage_ and is constructed by the platform for you. In desktop applications, the Stage is the window. Its appearance depends on the host system and varies among Mac OS X, Windows, and Linux platforms. 

The _Stage_ holds a _scene_. The _scene_ consists of JavaFX elements such as the root, which is the top _scene_ element and contains what is called the scene graph.

The scene graph is a strictly hierarchical structure of elements that visualize your application. These elements are called Nodes. A Node has exactly one parent (except the root node) and may contain other Nodes. Or, a Node can be a leaf node with no children. Nodes must be added to the scene graph in order to participate in the rendering of that scene. Furthermore, a Node may be added only once to a scene, unless it is first removed and then added somewhere else.

Parent nodes in general manage their children by arranging them within the scene according to layout rules and any constraints you configure. JavaFX uses a two-dimensional coordinate system for 2D graphics with the origin at the upper-left corner of the scene, as shown in the figure below. Coordinate values on the x-axis increase to the right, and y-axis values increase as you move down the scene."

##### JavaFX FXML
 "An alternative approach is to declare scene graph nodes with FXML, a markup notation based on XML. FXML lets you describe and configure your scene graph in a declarative format. This approach has several advantages:

- FXML markup structure is hierarchical, so it reflects the structure of your scene graph.
- FXML describes your view and supports a Model-View-Controller (MVC) architecture, providing better structure for larger applications.
- FXML reduces the JavaFX code you have to write to create and configure scene graph nodes.
- You can design your UI with Scene Builder. This drag-and-drop tool is a stand-alone application that provides a visual rendering of your scene. And Scene Builder generates the FXML markup for you.
- You can also edit your FXML markup with text and IDE editors.

FXML affects the structure of your program. The main application class now invokes an FXMLLoader. This loader parses your FXML markup, creates JavaFX objects, and inserts the scene graph into the scene at the root node. You can have multiple FXML files, and typically each one has a corresponding JavaFX controller class. This controller class may include event handlers or other statements that dynamically update the scene. The controller also includes business logic that manages a specific view."

## Reminder av nøkkelpunkter

| Nøkkelpunkt                              | Beskrivelse                             |
| ---------------------------------------- | --------------------------------------- |
| Innleveringsfrist                        | 10. april                               |
| Demonstrasjonsfrist hos læringsassistent | 17. april                               |
| Gruppestørrelse                          | 1 eller 2 personer                      |

### Anbefalte perioder å jobbe med prosjektet

| Uke   | Fra  | Til  | Beskrivelse                                 |
| ----- | ---- | ---- | ------------------------------------------- |
| 12    | 16/3 | 20/3 | Grunnklasser og brukergrensesnitt           |
| 13    | 23/3 |  27/3 | Lagring og filhåndtering                   |
| 14    | 30/3 |  3/4 | Chille og grille på påskeferie!            |
| 15    |  6/4 | 10/4 | Fullføre appen med tilhørende dokumentasjon |

**_LYKKE TIL_**
