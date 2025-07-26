import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

class FileWriterHelper {
    // This tracks which output files have already been touched
    // Meaning if the file does not exist, it does not exist for everybody
    private static final Set<Path> knownMissingFiles = new HashSet<>();
    private static boolean isWritable(Path outputFile) {
        if (Files.notExists(outputFile)) {
            if (!knownMissingFiles.contains(outputFile)) {
                System.out.println("\u001B[31mOutput file does not exist: " + outputFile + ". Skipping.\u001B[0m");
                knownMissingFiles.add(outputFile);
            }
            return false;
        }
        return true;
    }

    public static void appendLine(Path outputFile, String line) {
        if (!isWritable(outputFile)) return;

        try {
            Files.writeString(
                    outputFile,
                    line + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.out.println("\u001B[31mFailed to write to file: " + outputFile + "\u001B[0m");
        }
    }

    public static void createEmptyFile(Path path) {
        try {
            // This will overwrite the file with empty content (truncate to 0 length)
            Files.writeString(path, "");
        } catch (NoSuchFileException e) {
            System.out.println("\u001B[31mCannot truncate non-existent file: " + path + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mFailed to truncate file: " + path + "\u001B[0m");
        }
    }

    public static Path ensureOutputDirectory(Path dir) {
        try {
            Files.createDirectories(dir);
            return dir.normalize();
        } catch (IOException e) {
            System.out.println("\u001B[31mCould not create output directory: " + dir + "\u001B[0m");
            System.out.println("\u001B[33mFalling back to current working directory.\u001B[0m");
            return PathUtils.CURRENT.normalize();
        }
    }

    // I put this method here instead of PathUtils cuz it eventually helps write to file
    public static Path resolveOutputFilePath(Path outputDir, String prefix, String type) {
        try {
            return outputDir.resolve(prefix + type + ".txt").normalize();
        } catch (InvalidPathException | NullPointerException e) {
            System.out.println("\u001B[31mInvalid output path or prefix\u001B[0m");
            System.out.println("\u001B[33mFalling back to current directory with default name.\u001B[0m");
            return PathUtils.CURRENT.resolve(type + ".txt").normalize();
        }
    }
}
