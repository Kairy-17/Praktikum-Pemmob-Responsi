# Eksplorasi Anime

Proyek ini adalah aplikasi Android **Anime Explorer** yang dibuat menggunakan **Jetpack Compose** dan **Kotlin**. Aplikasi ini mengambil daftar dan detail anime dari [Tenrai API](https://api.tenrai.org/v1) dan menyajikannya dalam tampilan antarmuka berbahasa Indonesia yang modern dan interaktif.

## Fitur Utama

*   **100% Jetpack Compose:** Tidak ada XML layout, seluruh UI dibangun secara deklaratif dengan Jetpack Compose dan Material Design 3.
*   **Arsitektur MVVM Terstruktur:** Proyek dipisahkan secara rapi ke dalam tiga lapisan (Data, Domain/State, dan UI/Presentation) dengan *single source of truth*.
*   **StateFlow & Coroutines:** Pembaruan UI dilakukan secara asinkron dengan Kotlin Coroutines, serta dikelola secara mulus (Loading, Sukses, Error) melalui StateFlow.
*   **Navigation Compose:** Perpindahan navigasi yang mudah antara layar `HomeScreen` (Daftar) ke `AnimeDetailScreen` (Detail) menggunakan argumen string ID.
*   **Fitur Spesifik Kotlin:** Implementasi keamanan *null* (*null-safety* seperti Elvis operator `?:`), Lambda expressions, dan Operasi Koleksi (`sortedByDescending`).
*   **Retrofit Network Layer:** Proses HTTP *request* ditangani secara *clean* dengan Retrofit2 dan konversi Gson.

## Batasan (Sesuai Syarat Proyek Khusus)
1.  **Tanpa Fitur Image/Gambar:** Aplikasi difokuskan pada manipulasi teks, sehingga tidak memakai *library loader* seperti Coil atau Glide.
2.  **Tanpa Database (Room):** Data semata-mata bergantung dari pemanggilan API (RESTful).
3.  **Tanpa Authentikasi (Login/Register).**

## Cara Menjalankan (Getting Started)

1. Pastikan Anda telah memasang **Android Studio** versi terbaru (dengan dukungan SDK 34+).
2. Lakukan clone pada repository ini:
   ```bash
   git clone https://github.com/Kairy-17/Praktikum-Pemmob-Responsi.git
   ```
3. Buka folder `AnimeExplorer` di Android Studio.
4. Tunggu hingga proses Sinkronisasi Gradle selesai.
5. Klik **Run** (Gigi berwarna hijau) untuk mencoba aplikasi pada *Emulator* ataupun perangkat fisik Android (minimal API 31).

## Struktur Direktori

```
app/src/main/java/com/example/animeexplorer/
├── data/
│   ├── model/           # Data Class (Anime, API Responses)
│   ├── remote/          # Retrofit Interfaces (AnimeApiService)
│   └── repository/      # Repository Data (AnimeRepository)
├── di/
│   └── AppContainer     # Kustom Dependency Injection (Retrofit Factory)
├── ui/
│   ├── navigation/      # Rute Navigasi Compose (AppNavigation)
│   ├── screens/         # Tampilan Komponen Compose (HomeScreen, DetailScreen)
│   ├── state/           # Sealed Class untuk UI State (Loading, Success, Error)
│   ├── theme/           # Konfigurasi Tema (Color, Typography Material 3)
│   └── viewmodel/       # File ViewModel & Provider-nya
└── MainActivity.kt      # Titik Awal (Entry point) Aplikasi
```

## Teknologi & Dependensi
*   **Kotlin** (Versi 2.2.10)
*   **Jetpack Compose BOM** (2026.02.01)
*   **Retrofit 2** (2.11.0)
*   **Navigation Compose** (2.8.5)
*   **ViewModel Compose** (2.8.7)
*   **Material Design 3**
