# Aplikasi Eksplorasi Anime

Aplikasi mobile sederhana yang dibangun untuk memenuhi tugas responsi. Aplikasi ini berfungsi untuk mencari dan menampilkan informasi anime secara dinamis dari REST API.

## 📸 Screenshots
*(Ganti URL gambar di bawah ini dengan link screenshot aplikasi kamu yang sudah diupload)*
| Home Screen | Anime Detail Screen |
|:---:|:---:|
| ![Home](link-screenshot-home.png) | ![Detail](link-screenshot-detail.png) |

## 🛠️ Penjelasan Teknis

Aplikasi ini dibangun sepenuhnya menggunakan **Kotlin** dan **Jetpack Compose** untuk antarmuka pengguna, menghindari penggunaan XML Layout.

### Arsitektur (MVVM)
Aplikasi menerapkan arsitektur **MVVM (Model - View - ViewModel)** yang dibantu dengan pola **Repository**:
*   **Model**: Menggunakan *Data Class* Kotlin untuk merepresentasikan struktur data dari API (Judul, Rating, Tahun, Episode) dengan *Null safety*.
*   **Repository**: Bertanggung jawab sebagai sumber data tunggal (*Single Source of Truth*) yang melakukan pemanggilan jaringan (*networking*) ke API.
*   **ViewModel**: Mengelola logika bisnis dan menjaga *State* (Loading, Success/Data, Error) agar tetap stabil saat terjadi perubahan konfigurasi. 
*   **View**: UI dibangun dengan Compose (Material Design 3, Custom Theme & Typography) yang merespons perubahan *State* dari ViewModel. 

### Fitur Utama & Library
*   **Networking**: Menggunakan Retrofit untuk melakukan HTTP GET request ke endpoint `https://api.tenrai.org/v1`.
*   **Navigation**: Menggunakan Jetpack Navigation Compose untuk berpindah antara `HomeScreen` (menampilkan daftar menggunakan `LazyColumn`) dan `AnimeDetailScreen`.
*   **Kotlin Features**: Memanfaatkan fungsionalitas modern Kotlin seperti Lambda expressions untuk navigasi dan *Collection* untuk memanipulasi list data.
*   **No Image Loading**: Sesuai dengan instruksi, aplikasi ini hanya berfokus pada data teks, sehingga tidak membebani aplikasi dengan library pemuatan gambar tambahan.
