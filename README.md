![nasa logo](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/NASA_logo.svg/1200px-NASA_logo.svg.png =300x200)

# NasaDummyApp
NasaDummyApp is, guess what, a dummy Android app in **Kotlin** that I developed in order to learn something new about the Droid world and keep myself updated with the lastest trends in the native universe. 

## Software Architecture Pattern
I tried to use MVVM for the first time in order to make the code more readable, scalable and easily testable in the future.

## Libraries
I tried to use the most important libraries for handling the whole app's stack and testing:
 1. Android Jetpack ( ViewModels, LiveData, Navigation )
 2. RXJava
 3. Retrofit
 4. Kotlin Coroutines
 5. Moshi
 6. JUnit and Mockk

## What does it do? 
1 - It fetches Mars Rover photos in a list and displays them on the device. 

| Retrofit | Moshi | Coroutines | RecyclerView + Glide |
|--|--| -- | -- |
| GET request to NASA APIs | Converts JSON to Kotlin Object | Handles the async request | Display the result |



## Sooo...what's its purpose? 

As for now, it's only a showcase that sums up how I learned to use all these important libraries that are necessary to develop a state of the art app.
