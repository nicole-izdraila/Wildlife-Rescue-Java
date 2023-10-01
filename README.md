# Wildlife Rescue Centre

![](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![](https://img.shields.io/badge/JUnit5-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white) ![](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=MySQL&logoColor=white) ![](https://img.shields.io/badge/GitHub%20Actions-2088FF.svg?style=for-the-badge&logo=GitHub-Actions&logoColor=white) ![](https://img.shields.io/badge/Git-F05032.svg?style=for-the-badge&logo=Git&logoColor=white) ![](https://img.shields.io/badge/macOS-000000.svg?style=for-the-badge&logo=macOS&logoColor=white) ![](https://camo.githubusercontent.com/41281b9a32f13ac5b9d41ed9bae12c0de662f948f9bf59fd19df354fe49af146/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f57696e646f77732d3030373844363f7374796c653d666f722d7468652d6261646765266c6f676f3d77696e646f7773266c6f676f436f6c6f723d7768697465)

### Colaborators

- Nicole Izdraila (30147366)
- Mariia Podgaietska (30151330)
- Afrah Mohammad (30144844)
- Axel Sanchez (30145429)

### How to run the program

- Compile program

```bash
javac -cp "lib/mysql-connector-java-8.0.23.jar" -d bin src/edu/ucalgary/oop/*.java
```

- Run program in MacOS

```bash
java -cp "lib/mysql-connector-java-8.0.23.jar:bin" edu.ucalgary.oop.Main
```

- Run program in Windows

```bash
java -cp "lib/mysql-connector-java-8.0.23.jar;bin" edu.ucalgary.oop.Main
```

### How to run the tests

- Compile tests

```bash
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin src/edu/ucalgary/oop/*.java test/edu/ucalgary/oop/*.java
```

- Run tests in MacOS

```bash
java -cp "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore edu.ucalgary.oop.AnimalTest edu.ucalgary.oop.CleaningCageTest edu.ucalgary.oop.FeedingScheduleTest edu.ucalgary.oop.TaskTest edu.ucalgary.oop.ToDoTest edu.ucalgary.oop.TreatmentTest
```
