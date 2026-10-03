# Buku Tamu Digital (Clean Architecture)

Kompilasi & jalankan:

    mkdir -p out
    javac -d out $(find src -name "*.java")
    java -cp out App

Menu: 1 = Daftarkan tamu, 2 = Cari (nama, case-insensitive), 3 = Hapus, x = Keluar
(mengetik x pada prompt input lain akan membatalkan operasi tersebut).
