# New Movies App 🎬

New Movies is an Android application for browsing, searching, and watching movies with subtitles, featuring a modern UI and smooth animations.

---

## 📌 Features

- Browse and search movies
- View movie details and categories
- Watch videos with subtitles and full playback controls
- Fullscreen and landscape support
- Internet connection check with proper messages
- Smooth animations with Lottie
- Navigation management using Cokoin

---

## 🛠 Technologies

- **Kotlin** with **Jetpack Compose**
- **ExoPlayer (Media3)** for video playback
- **Cokoin** for dependency injection and navigation
- **Lottie Compose** for animations
- **Kotlin Coroutines** for asynchronous operations
- **OkHttp & Retrofit** for networking
- **MVVM Architecture** (ViewModel, Repository)
- **Coil** for image loading
- **Android Gradle Plugin 8.x**

---

## 🏗 Architecture

- **UI:** Jetpack Compose
- **ViewModel:** Manages state and interacts with repositories
- **Repository:** Handles data from network and local cache
- **Navigation:** Page routing with KoinNavHost
- **Network Checker:** Detects internet connectivity
- **Animations:** Shows entry/loading animations with Lottie
- **ExoPlayer:** Full-featured video player with subtitle support

---

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/8009018c-1b48-49de-a893-a075d1a365c3" width="280" alt="Home Screen"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/70deb030-7de3-4830-94cf-22a1d24cb6c5" width="280" alt="Detail Screen"/>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/f3a233db-f2dc-4832-8fc6-9b2dcdee7917" width="280" alt="Search Screen"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/e5d8317c-0f49-48a4-9107-32fccb4fd7d3" width="280" alt="Video Player"/>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/571e6e15-5d9e-4274-b0ec-0e0f3c729f18" width="280" alt="Category Screen"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/8f39b423-51b6-431a-8c6f-3eb4b43ab9ef" width="280" alt="Profile Screen"/>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/f1bda6bc-e988-4a82-a5e4-7e54a640ec98" width="280" alt="Loading Animation"/>
</p>

---

## ⚠️ Notes

- Internet connection is required for search and video playback.
- Videos are loaded dynamically via URLs.
---

## 📝 License

MIT License © 2025
