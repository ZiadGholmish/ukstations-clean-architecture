# ukstations-clean-architecture
A proof of concept using the api for TFL to get the available stations to the user location also will update the expected arrival times for each stations every 30 seconds.

## Getting Started
You have to clone the app and import in android studio then sync and you will be able to run it.

## Project structure 

**1. data Layer**
- Contains all data whether come from the backend or from local database.
- Repository pattern to access these data and provide interface between the domain layer and the data layer.
- helpers methods to format the retrofit response to sealed classes so there is a way to unified the error handling from the network.

**2. Domain Layer**
- Contains all business cases away from the dependencies of android paltform.
- All the use cases running in background process till they finish their work.

**3. Presentation Layer**
- The presentation layer specific for android and it brings the data from the domain layer.
- MVP with MVVM and live data to manage the view logic and the state of the data.
- ViewModels to handle the presentations logic and dealing with the domain layer.
- MVP pattern to handle the logic of views from presneter not the activity.

## Libraries

1. Kotlin
2. RXjava2
3. Dagger2
5. Retrofit
