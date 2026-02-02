# 📚 Sistem Data Mahasiswa - Java Hash Table

## 👨‍💻 Deskripsi Project

Sistem Data Mahasiswa adalah aplikasi berbasis Java yang digunakan untuk mengelola data mahasiswa dengan memanfaatkan **struktur data Hash Table** melalui `HashMap`.

Project ini dibuat untuk memenuhi tugas praktikum struktur data dengan tujuan mengimplementasikan konsep:

- Object-Oriented Programming (OOP)
- Hash Table
- Analisis kompleksitas algoritma
- Manajemen data secara efisien

Hash Table dipilih karena memiliki performa sangat cepat dalam proses pencarian dan penyimpanan data.

---

## 🎯 Tujuan Project

- Mengimplementasikan struktur data Hash Table dalam Java
- Memahami cara kerja operasi insert dan search
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

- Sistem membuat object `Mahasiswa`
- Data dimasukkan ke dalam `HashMap`
- NIM digunakan sebagai key unik

---

#### 2. Search Data Mahasiswa

Mencari mahasiswa berdasarkan NIM.

**Cara kerja:**

- Sistem mengambil data dari HashMap menggunakan method `.get()`
- Jika data ditemukan -> tampilkan informasi mahasiswa
- Jika tidak -> tampilkan pesan "Mahasiswa tidak ditemukan"

---

## 🚧 Fitur yang Belum Diimplementasikan (Future Improvement)

Fitur berikut direncanakan untuk pengembangan selanjutnya:

### 🔜 Delete Mahasiswa

Menghapus data mahasiswa berdasarkan NIM.

### 🔜 Update Data Mahasiswa

Mengubah informasi mahasiswa tanpa perlu menghapus data.

### 🔜 Tampilkan Semua Mahasiswa

Menampilkan seluruh data yang tersimpan dalam Hash Table.

### 🔜 Validasi Input

Mencegah input tidak valid seperti:

- IPK di luar rentang 0–4
- NIM duplikat

### 🔜 Analisis Performa

Membandingkan kecepatan Hash Table dengan struktur data lain seperti Binary Search Tree.

---

## 🏗️ Struktur Project

```bash
SistemDataMahasiswa/
│
├── Mahasiswa.java
├── StudentHashTable.java
└── Main.java
```

**Penjelasan:**

✅ `Mahasiswa.java`  
Menyimpan atribut dan method untuk object mahasiswa.

✅ `StudentHashTable.java`  
Mengelola operasi Hash Table seperti insert dan search.

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

| Operasi    | Kompleksitas                  |
| ---------- | ----------------------------- |
| Insert     | O(1)                          |
| Search     | O(1)                          |
| Worst Case | O(n) (jika terjadi collision) |

---

## ✅ Kelebihan Sistem

- Pencarian data sangat cepat
- Struktur kode modular
- Mudah dikembangkan
- Efisien untuk dataset besar

---

## ❌ Kekurangan Sistem

- Tidak menyimpan data secara terurut
- Bergantung pada hashing
- Collision dapat menurunkan performa

---

## 📌 Kesimpulan

Implementasi Hash Table pada Sistem Data Mahasiswa terbukti efektif untuk meningkatkan kecepatan pencarian dan pengelolaan data. Dengan kompleksitas rata-rata O(1), struktur data ini sangat cocok digunakan dalam aplikasi yang membutuhkan akses data secara cepat.
