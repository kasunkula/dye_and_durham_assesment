# Name Sorter

This is a console application that reads a list of names from a text file, validates and sanitize them, sorts them, and outputs the sorted list to both the console and to an output file named `sorted-names-list.txt` in the working directory. 
This output file will overwrite any existing file with the same name.

This project is an implementation for screening assignment described in [Dye & Durham Coding Assessment](https://track.pstmrk.it/3s/app.pinpointhq.com%2Frails%2Factive_storage%2Fblobs%2FeyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBCTW5odlFJPSIsImV4cCI6bnVsbCwicHVyIjoiYmxvYl9pZCJ9fQ%3D%3D--a9f6d2d43c6383f5497a95666a63972821f6f1a3%2Fc-sharp-assessment.pdf/h0sH/36C-AQ/AQ/6666c7d0-6f5d-4b57-bb89-6156ef9b8a67/2/WgDf1Zc6_x).

---

## 📖 High level Problem Statement

Build a name sorter. Given a set of names, order that set first by **last name**, then by any given names the person may have.
- A name must contain **at least 1 given name** and **a last name**.
- A name may have up to **3 given names** and **a last name**.

Sorted names should be displayed on screen, and should be written to a file in the working directory called sorted-names-list.txt containing the sorted names.

## 📖 Assumptions
- As per the problem statement, the file is not expected be large enough to not fit into memory. Therefore, the application reads the entire file into memory, processes it, and outputs the results.
- Only a simple sanitization is needed on the input names.
- The input file is expected to be a plain text file with one name per line.
- The characters in the names are expected to be alphabetic characters and spaces only.

## 📖 Future Enhancements
- In case of a large input file, the application should be altered to sort chunks of records from the file, then writing them to temp files and finally running a merge sort on the sorted chunks instead of loading everything into memory at once.
- If the names sorting needs to consider locale-specific rules, the sorting logic should be updated to use locale-aware comparison methods.

### Input
- A .txt file with a name per line as below.

`unsorted-names-list.txt`

```txt
Janet Parsons
Vaughn Lewis
Adonis Julius Archer
Shelby Nathan Yoder
Marin Alvarez
London Lindsey
Beau Tristan Bentley
Leo Gardner
Hunter Uriah Mathew Clarke
Mikayla Lopez
Frankie Conner Ritter
```

### Example Usage

#### Build
```bash
mvn clean install
```

#### Run
```bash
java -jar ./target/name-sorter.jar ./unsorted-names-list.txt;
```