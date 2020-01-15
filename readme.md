# Music on the Way
Application Android faite avec Kotlin

## Liste des features
[* Map Google API](### Map Google API)
* [Firebase - Lecture] (### Firebase - Lecture)
* [Firebase - Ecriture] (### Firebase - Ecriture)
* [Player musique] (### Player musique)
* [Call API Navitia] (### Call API Navitia)

## Expication du fonctionnement
### Map Google API
Il y a 3 fichiers importants.
* activity_maps.xml (layout) 
Ce fichier contient le fragment pour la map

* google_maps_api.xml (values)
Ce fichier contient la clé API et il est appelé par le fichier AndroidManifest.xml avec le code:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="@string/google_maps_key" />
```

* MapsActivity.kt
C'est avec ce fichier qu'on initialise la carte, que l'on place des marqueurs et qu'on rêgle la position de départ.
[Voire documentation](https://developers.google.com/maps/documentation/android-sdk)

### Firebase - Lecture
Les événements sont visible grâce à un Recycler View. Les informations sont extraites de Firebase dés qu'il y a une modification.
Pour ajouter des champs à prendre, il faut modifier le ficher Event.kt (recyclerView/model) et le fichier EventActivity.kt (recyclerView).

Event.kt
```kotlin
@Parcelize
class Event(
    val eventID : String? = "" ,
    val name : String = "",
    val text : String =""

): Parcelable
```

EventActivity.kt
```kotlin
for (element in listEvent) {
    val eventID = element.eventID
    val name = element.name
    val text = element.text

    listEvents.add(Event(eventID, name, text))
}
```

EventItem.kt permet de selectionner les informations qui seront gardées si on clic sur un événements.

### Firebase - Ecriture
Les événements sont programmés grâce au fichier activity_newevent.xml. Les informations sont ajoutées à  Firebase grâce au ficher NewEvent.kt (FireBase/Add) et la classe Event.kt (FireBase).

Event.kt
```kotlin
@IgnoreExtraProperties
data class Event(
    val eventID : String? = "" ,
    val name : String = "",
    val text : String =""
)
```

NewEvent.kt
```kotlin
lateinit var name: String
lateinit var text: String

// Du code

name = NameNewEvent.text.toString()
text = InfosNewEvent.text.toString()

// Dans la function addEvent()
val eventID = reference.push().key
val event = Event(eventID, name, text)
```

### Player musique
...

### Call API Navitia
...