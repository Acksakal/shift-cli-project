# 🧹 Java CLI File Processor

This is a **Command-Line Interface (CLI) tool written in Java (24.0.2) for processing input files line-by-line.  
Each line is categorized as an **integer**, **float**, or **text**, and written to separate output files.  
The tool also supports customizable output options and generates statistics per data type.

---

## ✅ Features

- 🚀 Processes multiple files passed via command line
- 📂 Categorizes data into integers, floats, and strings
- 🗂 Output written to separate files:
  - `*.integers.txt`
  - `*.floats.txt`
  - `*.text.txt`
- ⚙️ Configurable output directory and file prefix
- ➕ Optional **append mode**
- 📊 Prints short or full statistics:
  - Short: count of written lines
  - Full: min, max, sum, average for numbers; shortest and longest string lengths for text
- 🔐 Robust error handling — partial processing continues even if some files fail

---

## 🔧 How to Use

### 1. Clone the repository and cd into it

### 2. Compile the source code

```sh
javac src/*.java
```

### 3. Run the tool

```sh
java -cp src Main [options] <input-file1> <input-file2> ... <input-fileN>
```

#### Example:

```sh
java -cp src Main data/input1.txt data/input2.txt
java -cp src Main -o output/ -p result_ -s data/input.txt
java -cp src Main -a -f /absolute/path/to/in1.txt
```

---

## ⚙️ Options

| Flag  | Description |
|-------|-------------|
| `-o <dir>` | Output directory (default: current directory) |
| `-p <prefix>` | Prefix for output filenames (default: none) |
| `-a` | Append to existing output files instead of overwriting |
| `-s` | Print short statistics |
| `-f` | Print full statistics (implies `-s`) |

---

## 📁 Output Behavior

- Output files are named like:
  - `prefix_integers.txt`
  - `prefix_floats.txt`
  - `prefix_text.txt`
- Files are only created if corresponding data exists.
- Default output is to the current directory with names like `integers.txt`, `floats.txt`, etc.
- Files are overwritten unless `-a` (append mode) is specified.

---

## 📊 Statistics

- **Short statistics (`-s`)**:
  - Number of lines written per data type
- **Full statistics (`-f`)**:
  - For integers/floats: count, min, max, sum, average
  - For text: count, shortest, and longest line length
