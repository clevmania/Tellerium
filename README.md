# Tellerium

The app fetches a list of farmers from the network using the Retrofit library and displays the list using a RecyclerView. The app's architecture is a clean architecture, Single Activity and follows a typical MVVM pattern where all the complexity of the View is deferred to a ViewModel. The app uses ViewModel and LiveData to hold the data and update the UI.

The app is an online application, so the user needs a network connection to use it. However, i've implement offline caching to display results from the local database, instead of from the network. This way you will be able to use the app while your device is offline, or if you have a slow network connection.

To implement the offline cache, Room database was used to make fetched data persistent in the device's local storage. You access and manage the Room database using a repository pattern, which is a design pattern that isolates data sources from the rest of the app. This technique provides a clean API for the rest of the app to use for accessing the data.

## Project Setup

1. Download the project by cloning this repository or downloading an archived snapshot. (See Installation)

2. In Android Studio, click the "Open an existing Android Studio project" or "Import Project" option.

3. Select the `Tellerium` directory that you downloaded with this repository.

## Installation
Clone this repository and import into **Android Studio**
```bash
https://github.com/clevmania/Tellerium.git
```

## Getting Started
The app consists of 7 flows
- Register: A user registers by providing some required personal information

- Login: A user can login using credentials provided during registration

- Farmer: Displays a list of all farmers

- Farmer Detail: Is provides a comprehensive breakdown of a particular farmer, categorised as Personal, Identity and Farm

- Dashboard: Gives analytics on captured farms and farmers

- Add Farm: Capture a farm

## Disclaimer

If an application gets larger, there would be lots of boilerplate code which can be error-prone.

The app uses a manual dependency injection, on a norm dagger would be used. Also sensitive key should not be in source control but i've added a key for the purposes of this assessment.