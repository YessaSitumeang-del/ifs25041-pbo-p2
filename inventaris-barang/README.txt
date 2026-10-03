Cara menjalankan (dari folder inventaris-barang):

  mkdir out
  javac -d out $(find src -name "*.java")     # Linux/Mac
  java -cp out App

Windows (PowerShell):
  mkdir out
  javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
  java -cp out App

Uji test case:
  java -cp out App < test-cases/TC-01.tc
