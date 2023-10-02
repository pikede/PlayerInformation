Player Search App by PIKEDE

Tools:
Android, Dependency Injection(Koin), Kotlin, Coroutines, MutableLiveData, LiveData, MVVM Architecture, Retrofit, Junit

Features:
- User can search a player/athlete name
- App gets players if available in backend, and displays result in RecycleView for displaying players
- recyclerview persists state when user selects a players previous team
- Unit test to for handling loading players error (PlayerViewModelTest)

Improvements:
- testing network calls and PlayerHistoryViewmodel
- cache for offline data persistence and repository pattern
- user tracking and analytics
- handling for unknown athletes

Instructions:
- Clone the repository
- Make sure you have Android Studio v3.6 or later installed on your computer
- Import the project into Android Studio
- Select Trust project
- Select File > Sync project with gradle files
- Ensure that you have an emulator running Android 9 (API level 28) set up: https://developer.android.com/studio/run/emulator
- Run the app target on your locally configured Android emulator
- Enter athlete name in searchbox and press enter
- Select athlete and view players previous teams