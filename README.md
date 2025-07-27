# Java CLI File Processor

This is a simple **Command-Line Interface (CLI) tool written in Java** for processing input files line-by-line.  
Each line is categorized as either an **integer**, **float**, or **text**, and written to separate output files.  
Optional flags allow you to collect summary statistics.

---

## 🔧 How to Use

### 1. Clone the repository

```sh
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

### 2. Compile the source files

```sh
javac src/*.java
```

### 3. Run the tool

```sh
java -cp src Main <input-file1> <input-file2> ... <input-fileN>
```

Example:

```sh
java -cp src Main data/input1.txt data/input2.txt
```

---

## ⚙️ Optional Flags

You can add flags after input files to enable statistics collection:

- `-s` → Print short statistics (counts and averages)
- `-f` → Print full statistics (including min/max values)

Example with statistics:

```sh
java -cp src Main data/input.txt -s
```

---

## 📁 Output

- Output files will be created in the specified output directory.
- Lines are categorized into:
  - `*.integers.txt`
  - `*.floats.txt`
  - `*.text.txt`
  
---

## 🛠 Requirements

- Java 8 or higher
- Git (to clone the repo)

---

## 📄 License

MIT (or replace with your license)
