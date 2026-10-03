Cara menjalankan (dari folder finance-app):

  mkdir out
  javac -d out $(find src -name "*.java")     # Linux/Mac
  java -cp out App

Windows (PowerShell):
  mkdir out
  javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
  java -cp out App

Test case: java -cp out App < test_input*.txt
