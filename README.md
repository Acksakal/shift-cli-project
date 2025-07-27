# 🧹 Java CLI File Processor

This is a Command-Line Interface (CLI) tool for processing input files line-by-line.

Each line is categorized as an **integer**, **float** (real **ℝ** numbers), or **text**, and written to corresponding output files.  
The tool also supports customizable output options and generates statistics per data type.

**🛠 Developed with:**
- Java **24.0.2**
- IntelliJ IDEA **2025.1.3 (Community Edition)**

---

## ✅ Features

- 🚀 Processes multiple files passed via command line
- 📂 Categorizes data into integers, floats, and strings
- 🗂 Output written to separate files:
  - `*.integers.txt`
  - `*.floats.txt` => does not include NaN and +-Infinity cuz those are not real (R)
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
java -jar ShiftProject.jar [options] <input-file1> <input-file2> ...
```

##### Example:

```sh
java -jar ShiftProject.jar -s -a -p sample- in1.txt in2.txt
```

---

#### 🛠️ Option 2: Compile and run from source (for developers)

If you're working with source code, compile and run like this and yes, it was written and tested on Windows:

```sh
javac src\*.java
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

### 3. 📂 Path Resolution

- **Relative paths** (no leading slash):  
  Interpreted relative to your current directory in the terminal.  
  Example:
  ```sh
  java -jar ShiftProject.jar input.txt
  ```

- **Absolute paths** (start with `/` on Unix or `C:/` on Windows):  
  On Windows, a path like `/desktop/in1.txt` will be resolved relative to your **home directory**,  
  typically `C:/Users/<your-name>/`, so the full path becomes `C:/Users/<your-name>/desktop/in1.txt`.  
  Example:
  ```sh
  java -jar ShiftProject.jar /desktop/in1.txt

> ⚠️ **Note for Git Bash Users on Windows**  
> Keep in mind that Git Bash uses a Unix-like emulated environment,  
> so absolute paths may resolve differently than in regular Windows CMD or PowerShell.  
> The home directory, in particular, does not map directly to `C:/Users/<your-name>`.

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

## 📝 Write vs Append Mode

1. **By default**, output files are **created** (if they don't exist) and **overwritten** each time the tool runs.

2. The `-a` flag enables **append mode**, which changes this behavior:
   - Lines will be **added** to the end of existing output files instead of replacing them.
   
   - If a file **does not exist**, you’ll be notified — but the program will continue running normally.

   - If a file **does exist**, new lines will be appended.

   - File existence is checked **after applying the `-p` prefix** (if provided).

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
    
