An Android application to get data from a json object while filtering and sorting. This is an application built to satisfy a coding exercise given by Fetch Rewards. The UI is built using Compose and retrofit is used to fetch data from an api. The application requires an internet connection to fetch the data.

## Android Studio setup
- The project was built using default Android Studio "Jellyfish" settings.
- Gradle JDK is set to GRADLE_LOCAL_JAVA_HOME which is JetBrains Runtime 21.0.4 - aarch64
- Gradle Version is 8.10.2
- Project was built to target SDK 35 with a minimum of 33

## Build Steps
- Clone this github repository
- In Android Studio go to File -> New -> Import Project
- Select the github project
- In SDK manager ensure that API 33 "Android 13.0" is downloaded
- Make sure to sync gradle before trying to build

## Build Options
- This project works for any Android device
- If using a Virtual Device in Android Studio ensure that the device has API 33 downloaded
- If using a physical device ensure that it is running on Android 13 "Tiramisu"
- Select the running device you want to target from the dropdown menu near the play button at the top
- Hit the play button to run and build the project

## Application Function
- The application will launch to a single activity that has a scrollable list of items with the List ID and name saved in Compose cards
- The data is sorted by List ID and then by name
- The ID is the same as the item name number so I chose not to display redundant information
