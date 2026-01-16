# My Movie Application (TMDB Client)

A modern Android movie browsing application built using Kotlin, Fragments, and TMDB API. The app allows users to explore trending and popular movies, search titles, view detailed movie information, and manage favorites and watchlist — all inside a smooth Material UI experience.
This project was built as part of an assignment to demonstrate API integration, UI/UX handling, fragment navigation, and state management.

## Features

- Browse Trending, Popular, Latest Releases, and Top Rated movies
- Search movies in real time using TMDB API
- Collapsing toolbar with dynamic movie splash banner
- Detailed movie screen with:
  - Movie Banner
  - Title
  - Overview
  - Release Date
  - Rating Progress Indicator
  - Play Now button with in-app notification
- Add movies to:
  - Favorites
  - Watchlist
- Bottom Navigation with persistent state
- Smooth scrolling with CollapsingToolbarLayout
- Fully responsive UI for different screen sizes

## Tech Stack

- Language: Kotlin
- Architecture: Fragment-based Navigation
- Networking: Retrofit + Gson
- Image Loading: Glide
- UI Components:
  - Material Design
  - CoordinatorLayout
  - CollapsingToolbarLayout
  - BottomNavigationView
- API Provider: TMDB (The Movie Database)

## Project Structure
```
app
│
├── manifests
│   └── AndroidManifest.xml
│
├── kotlin+java
│   └── com.example.my_movie_application
│       ├── api
│       │   ├── ApiService.kt
│       │   ├── RetrofitClient.kt
│       │   ├── Movie.kt
│       │   ├── MovieResponse.kt
│       │   └── Genre.kt
│       │
│       ├── MainActivity.kt
│       ├── MoviesFragment.kt
│       ├── MovieDetailsFragment.kt
│       ├── FavoritesFragment.kt
│       ├── WatchlistFragment.kt
│       ├── MovieAdapter.kt
│       ├── FavoritesManager.kt
│       └── WatchlistManager.kt
│
├── res
│   ├── layout
│   │   ├── activity_main.xml
│   │   ├── fragment_movies.xml
│   │   ├── fragment_movie_details.xml
│   │   ├── fragment_favorites.xml
│   │   ├── fragment_watchlist.xml
│   │   └── movie_item.xml
│   │
│   ├── drawable
│   ├── menu
│   │   └── bottom_nav_menu.xml
│   │
│   ├── values
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   └── themes.xml
│
└── Gradle Scripts
```

## Setup Instructions
1. Clone Repository
``` git clone https://github.com/your-username/my_movie_application.git ```

2️. Open Project
  - Open Android Studio
  - Select Open Existing Project
  - Choose the cloned folder

3️. Add TMDB API Key
  - Go to: `app/build.gradle.kts`
  - Inside defaultConfig, add:
    ` buildConfigField(
        "String",
        "TMDB_API_KEY",
        "\"YOUR_API_KEY_HERE\""
    )`
You can generate your API key from: ` https://www.themoviedb.org/settings/api`

4️. Sync Project
Click: File → Sync Project with Gradle Files

## Steps To Run Locally

1. Connect Android device OR start Emulator
2. Click Run button in Android Studio
3. Select device/emulator
4. App will launch automatically

## App Flow
- Home Screen
  - Displays:
    - Splash Banner (Trending Movie)
    - Horizontal movie rows
    - Search Bar
    - Bottom Navigation
  - Movie Details Screen - When clicking on any movie shows:
      - Movie Banner
      - Title
      - Release Date
      - Overview
      - Rating Progress
      - Play Button
      - Favorite Button
      - Watchlist Button
  - Play Button Behavior
      - Clicking PLAY NOW triggers an in-app notification: Movie is Playing
      - Favorites & Watchlist
  - Movies added are persisted using local memory manager classes
    - Accessible via Bottom Navigation tabs

## Dependencies Used

From libs.versions.toml and Gradle:

- Retrofit
- Gson Converter
- Glide
- Material Components
- RecyclerView
- Kotlin Coroutines

## Assumptions Made

- Internet connection is available for API requests
- TMDB API limits are respected
- Data persistence is session-based (local memory managers)
- No authentication required
- App targets Android SDK 34+

## Possible Improvements

If extended further:

- Room Database for offline storage
- Paging 3 for infinite scrolling
- ViewModel + LiveData architecture
- Dark/Light theme toggle
- User authentication
- Trailer playback
