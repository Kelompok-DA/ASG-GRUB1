# 📚 Sistem Data Mahasiswa - Hash Table & Graph

## 👨‍💻 Deskripsi Project

Sistem Data Mahasiswa adalah aplikasi berbasis Java yang digunakan untuk mengelola data mahasiswa dengan memanfaatkan **Hash Table** dan **Graph**.

Project ini dibuat untuk memenuhi tugas praktikum struktur data dengan tujuan mengimplementasikan konsep:

- Object-Oriented Programming (OOP)
- Hash Table
- Graph
- Analisis kompleksitas algoritma
- Manajemen data secara efisien

Hash Table dipilih karena memiliki performa sangat cepat dalam proses pencarian dan penyimpanan data. Graph digunakan untuk memodelkan relasi antar mahasiswa.

---

## 🎯 Tujuan Project

- Mengimplementasikan struktur data Hash Table dan Graph dalam Java
- Memahami cara kerja operasi insert, search, dan delete
- Membangun aplikasi modular berbasis OOP
- Menganalisis efisiensi struktur data

---

## 🧠 Struktur Data yang Digunakan

### ✅ Hash Table (HashMap)

Digunakan untuk menyimpan data mahasiswa dengan **NIM sebagai key**.

**Alasan penggunaan:**

- Pencarian sangat cepat -> rata-rata **O(1)**
- Insert cepat
- Cocok untuk sistem database sederhana

### ✅ Graph (Adjacency List)

Digunakan untuk menyimpan relasi antar mahasiswa berdasarkan NIM.

Contoh use case: memodelkan hubungan kolaborasi (pernah satu tim proyek) agar bisa melihat koneksi antar mahasiswa.

**Alasan penggunaan:**

- Mudah memodelkan koneksi antar data
- Operasi relasi sederhana dengan adjacency list

**Graph search (BFS):**

BFS dipilih karena relasi tidak berbobot dan kita hanya butuh mengecek keterhubungan, jadi BFS lebih sederhana tanpa logika bobot.

---

## ⚙️ Fitur Aplikasi

### ✅ Fitur yang Sudah Diimplementasikan

#### 1. Insert Data Mahasiswa

Menambahkan mahasiswa baru ke dalam sistem.

Data yang disimpan:

- NIM
- Nama
- IPK

**Cara kerja:**

- Sistem mengecek NIM agar tidak duplikat
- Jika valid, object `Mahasiswa` dibuat dan disimpan ke `HashMap`
- NIM juga ditambahkan sebagai vertex di graph untuk relasi

---

#### 2. Search Data Mahasiswa

Mencari mahasiswa berdasarkan NIM dan menampilkan relasi pada graph.

**Cara kerja:**

- Sistem mengambil data dari HashMap menggunakan method `.get()`
- Jika data ditemukan -> tampilkan informasi mahasiswa
- Sistem mengecek vertex di graph lalu tampilkan relasi jika ada
- Jika data tidak ditemukan -> tampilkan pesan "Mahasiswa tidak ditemukan"

---

#### 3. Delete Data Mahasiswa

Menghapus data mahasiswa berdasarkan NIM.

#### 4. Tambah Relasi Mahasiswa

Menambahkan relasi antar mahasiswa menggunakan graph.

#### 5. Hapus Relasi Mahasiswa

Menghapus relasi antar mahasiswa menggunakan graph.

#### 6. Tampilkan Graph

Menampilkan seluruh relasi mahasiswa.

---

## 🏗️ Struktur Project

```bash
SistemDataMahasiswa/
│
├── Mahasiswa.java
├── StudentGraph.java
├── StudentHashTable.java
└── Main.java
```

**Penjelasan:**

✅ `Mahasiswa.java`  
Menyimpan atribut dan method untuk object mahasiswa.

✅ `StudentGraph.java`  
Mengelola relasi mahasiswa menggunakan adjacency list.

✅ `StudentHashTable.java`  
Mengelola operasi Hash Table seperti insert, search, dan delete.

✅ `Main.java`  
Menjalankan program dan menyediakan menu interaktif.

---

## ▶️ Cara Menjalankan Program

1. Compile semua file Java dan jalankan:

```bash
javac *.java && java Main
```

---

## 📊 Analisis Kompleksitas

| Operasi Hash Table | Kompleksitas                  |
| ------------------ | ----------------------------- |
| Insert             | O(1)                          |
| Search             | O(1)                          |
| Worst Case         | O(n) (jika terjadi collision) |

Operasi graph seperti add/delete relasi rata-rata O(1) hingga O(k), tergantung jumlah koneksi pada NIM terkait.

---

## ✅ Kelebihan Sistem

- Pencarian data cepat melalui Hash Table
- Relasi mahasiswa mudah dikelola lewat graph
- Struktur kode modular
- Mudah dikembangkan

---

## ❌ Kekurangan Sistem

- Tidak menyimpan data secara terurut
- Bergantung pada hashing
- Collision dapat menurunkan performa
- Relasi graph bisa ramai jika banyak koneksi

---

## 📌 Kesimpulan

Implementasi Hash Table pada Sistem Data Mahasiswa terbukti efektif untuk meningkatkan kecepatan pencarian dan pengelolaan data. Dengan kompleksitas rata-rata O(1), struktur data ini sangat cocok digunakan dalam aplikasi yang membutuhkan akses data secara cepat.
