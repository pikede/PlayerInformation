Player Search App by PIKEDE

Tools: Android, Dependency Injection(Koin), Kotlin, Coroutines, MutableLiveData, LiveData, MVVM Architecture, Retrofit, Junit

Features:
- User can search a player/athlete name
- App gets players if available in backend, and displays result in RecycleView for displaying players
- recyclerview persists state when user selects a players previous team
- Unit test to for handling loading players error (PlayerViewModelTest)

Improvements:
- more testing for network calls and PlayerHistory
- Recycler view could have more styling
- user tracking and analytics
- repository pattern with cache for offline data persistence
