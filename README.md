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
