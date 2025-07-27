# 🧹 Java CLI File Processor

This is a **Command-Line Interface (CLI) tool written in Java (24.0.2) using IntelliJ IDEA 2025.1.3 (Community Edition) for processing input files line-by-line.  
Each line is categorized as an **integer**, **float** meaning real (R) numbers, or **text**, and written to separate output files.  
The tool also supports customizable output options and generates statistics per data type.

---

## ✅ Features

- 🚀 Processes multiple files passed via command line
- 📂 Categorizes data into integers, floats, and strings
- 🗂 Output written to separate files:
  - `*.integers.txt`
  - `*.floats.txt` => does not include NaN and +-Infinity
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

### 2. Run the Tool

You can use the tool in **two ways**:

---

#### ✅ Option 1: Using the prebuilt `.jar` (no compilation needed)

The repo contains the compiled JAR file (`ShiftProject.jar`), you can run it directly from the command line without compiling anything:

```sh
java -jar util.jar [options] <input-file1> <input-file2> ...
```

##### Example:

```sh
java -jar util.jar -s -a -p sample- in1.txt in2.txt
```

---

#### 🛠️ Option 2: Compile and run from source (for developers)

If you're working with source code, compile and run like this:

```sh
javac src\*.java => yes, it was written and tested on Windows
```

Then run:

```sh
java -cp src Main [options] <input-file1> <input-file2> ...
```

##### Example:

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
